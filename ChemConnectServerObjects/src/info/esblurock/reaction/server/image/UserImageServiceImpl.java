package info.esblurock.reaction.server.image;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.blobstore.UploadOptions;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import info.esblurock.reaction.client.async.UserImageService;
import info.esblurock.reaction.data.DatabaseObject;
import info.esblurock.reaction.data.UserDTO;
import info.esblurock.reaction.data.image.ImageServiceInformation;
import info.esblurock.reaction.data.image.ImageUploadTransaction;
import info.esblurock.reaction.data.image.UploadedImage;
import info.esblurock.reaction.server.queries.QueryBase;
import info.esblurock.reaction.server.utilities.ContextAndSessionUtilities;
import info.esblurock.reaction.server.utilities.ManageDataSourceIdentification;
import info.esblurock.reaction.server.utilities.WriteObjectTransactionToDatabase;


@SuppressWarnings("serial")
public class UserImageServiceImpl extends RemoteServiceServlet implements UserImageService {
 
	public static String fileCodeParameter = "fileCode";
	public static String userParameter = "user";
	
    @Override
    public ImageServiceInformation getBlobstoreUploadUrl(String keywordName, boolean uploadService) {
        BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

        ContextAndSessionUtilities util = new ContextAndSessionUtilities(getServletContext(), null);
		UserDTO user = util.getUserInfo();
        System.out.println("User: " + user);

        String outputSourceCode = null;
        if(uploadService) {
			outputSourceCode = ManageDataSourceIdentification.getDataSourceIdentification(user.getName()); 
        }		
        UploadOptions options = UploadOptions.Builder.withGoogleStorageBucketName(bucketName);
        String baseurl = uploadRoot + "?" + sourceFileParameter + "=" + outputSourceCode 
        		+ "&" + keywordNameParameter + "=" + keywordName;
        String uploadUrl = blobstoreService.createUploadUrl(baseurl,options);

        if(uploadService) {
        	ImageUploadTransaction imageinfo = new ImageUploadTransaction(user.getName(),outputSourceCode, keywordName, bucketName, uploadUrl);
        	WriteObjectTransactionToDatabase.writeObjectWithTransaction(user.getName(), keywordName, outputSourceCode, imageinfo);
        }
		ImageServiceInformation returninfo = new ImageServiceInformation(user.getName(),outputSourceCode, keywordName, bucketName, uploadUrl);
		
        return returninfo;
    }
 
    @Override
    public ArrayList<UploadedImage> getUploadedImageSet(ImageServiceInformation serviceInfo) throws IOException {
    	ArrayList<UploadedImage> imagelst = new ArrayList<UploadedImage>();
    	
    	String fileCode = serviceInfo.getFileCode();
    	String user = serviceInfo.getUser();
    	
    	ArrayList<String> parameternames = new ArrayList<String>();
    	parameternames.add(fileCodeParameter);
    	parameternames.add(userParameter);
    	
    	ArrayList<String> parametervalues = new ArrayList<String>();
    	parametervalues.add(fileCode);
    	parametervalues.add(user);
    	String classname = UploadedImage.class.getName();
    	
    	System.out.println("getUploadedImageSet: " + classname);
    	System.out.println("getUploadedImageSet: " + parameternames);
    	System.out.println("getUploadedImageSet: " + parametervalues);
    	List<DatabaseObject> objs1 = QueryBase.getDatabaseObjectsFromSingleProperty(classname, fileCodeParameter, fileCode);
    	System.out.println("getUploadedImageSet 1.1: " + objs1.size());
    	List<DatabaseObject> objs = QueryBase.getDatabaseObjectsFromProperties(classname, 
    			parameternames, parametervalues);
    	System.out.println("getUploadedImageSet 1 size: " + objs.size());
    	for(DatabaseObject obj : objs) {
    		UploadedImage uploaded = (UploadedImage) obj;
    		imagelst.add(uploaded);
    	}
    	
    	System.out.println("getUploadedImageSet 2 size: " + imagelst.size());
    	return imagelst;
    }
    
}