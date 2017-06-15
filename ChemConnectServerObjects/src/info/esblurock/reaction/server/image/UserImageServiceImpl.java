package info.esblurock.reaction.server.image;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreFailureException;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.blobstore.UploadOptions;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Key;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import info.esblurock.reaction.client.async.UserImageService;
import info.esblurock.reaction.data.DatabaseObject;
import info.esblurock.reaction.data.PMF;
import info.esblurock.reaction.data.UserDTO;
import info.esblurock.reaction.data.image.ImageServiceInformation;
import info.esblurock.reaction.data.image.ImageUploadTransaction;
import info.esblurock.reaction.data.image.UploadedImage;
import info.esblurock.reaction.data.store.StoreObject;
import info.esblurock.reaction.server.queries.QueryBase;
import info.esblurock.reaction.server.utilities.ContextAndSessionUtilities;
import info.esblurock.reaction.server.utilities.ManageDataSourceIdentification;
import info.esblurock.reaction.server.utilities.WriteObjectTransactionToDatabase;


@SuppressWarnings("serial")
public class UserImageServiceImpl extends RemoteServiceServlet implements UserImageService {
 
	public static String fileCodeParameter = "fileCode";
	public static String userParameter = "user";
	public static String keywordParameter = "keyWord";
	public static String blobkeyParameter = "blobKey";
	
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
		ImageServiceInformation returninfo = new ImageServiceInformation(user.getName(),outputSourceCode, keywordName, 
				bucketName, uploadUrl);
		
        return returninfo;
    }
 
    @Override
    public ArrayList<UploadedImage> getUploadedImageSet(ImageServiceInformation serviceInfo) throws IOException {
    	ArrayList<UploadedImage> imagelst = new ArrayList<UploadedImage>();
    	
    	String keyword = serviceInfo.getKeyWord();
    	String user = serviceInfo.getUser();
    	
    	ArrayList<String> parameternames = new ArrayList<String>();
    	parameternames.add(fileCodeParameter);
    	parameternames.add(userParameter);
    	
    	ArrayList<String> parametervalues = new ArrayList<String>();
    	parametervalues.add(keyword);
    	parametervalues.add(user);
    	String classname = UploadedImage.class.getName();
    	
    	List<DatabaseObject> objs = QueryBase.getDatabaseObjectsFromProperties(classname, 
    			parameternames, parametervalues);
    	for(DatabaseObject obj : objs) {
    		UploadedImage uploaded = (UploadedImage) obj;
    		imagelst.add(uploaded);
    	}
    	
    	System.out.println("getUploadedImageSet 2 size: " + imagelst.size());
    	return imagelst;
    }
    @Override
    public ArrayList<UploadedImage> getUploadedImageSetFromKeywordAndUser(String keyword) throws IOException {
    	ArrayList<UploadedImage> imagelst = new ArrayList<UploadedImage>();
    	    	
        ContextAndSessionUtilities util = new ContextAndSessionUtilities(getServletContext(), null);
		UserDTO user = util.getUserInfo();
		System.out.println("User: " + user.getName());
		ArrayList<String> parameternames = new ArrayList<String>();
	   	parameternames.add(keywordParameter);
    	parameternames.add(userParameter);
    	
    	ArrayList<String> parametervalues = new ArrayList<String>();
    	parametervalues.add(keyword);
    	parametervalues.add(user.getName());
    	String classname = UploadedImage.class.getName();
    	
    	List<DatabaseObject> objs = QueryBase.getDatabaseObjectsFromProperties(classname, 
    			parameternames, parametervalues);
    	for(DatabaseObject obj : objs) {
    		UploadedImage uploaded = (UploadedImage) obj;
    		imagelst.add(uploaded);
    	}
    	
    	System.out.println("getUploadedImageSet Keyword: " + keyword + " size: " + imagelst.size());
    	return imagelst;
    }
    @Override
    public String deleteFromStorage(String blobkey) throws IOException {
    	String ans = "Successful";
    	
    	
    	QueryBase.deleteFromIdentificationCode(UploadedImage.class, blobkeyParameter, blobkey);
    	
        try {
        	BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
        	BlobKey key = new BlobKey(blobkey);
        	blobstoreService.delete(key);
        } catch(BlobstoreFailureException ex) {
        	throw new IOException("Error in deleting: " + blobkey);
        }
    		
    	return ans;
    }
    
    @Override
    public String updateImages(ArrayList<UploadedImage> images) throws IOException {
    	String ans = "Successfully updated " + images.size() + " images ";
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.makePersistentAll(images);
    	return ans;
    }
    
    
}