package info.esblurock.reaction.server.image;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.blobstore.UploadOptions;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import info.esblurock.reaction.client.async.UserImageService;
import info.esblurock.reaction.data.UserDTO;
import info.esblurock.reaction.data.image.ImageServiceInformation;
import info.esblurock.reaction.data.image.ImageUploadTransaction;
import info.esblurock.reaction.data.image.UploadedImage;
import info.esblurock.reaction.server.utilities.ContextAndSessionUtilities;
import info.esblurock.reaction.server.utilities.ManageDataSourceIdentification;
import info.esblurock.reaction.server.utilities.WriteObjectTransactionToDatabase;


@SuppressWarnings("serial")
public class UserImageServiceImpl extends RemoteServiceServlet implements UserImageService {
 
	
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
 
    public ArrayList<UploadedImage> getUploadedImageSet(ImageServiceInformation serviceInfo) {
    	ArrayList<UploadedImage> imagelst = new ArrayList<UploadedImage>();
    	
    	
    	
    	return imagelst;
    	
    	
    }
    
}