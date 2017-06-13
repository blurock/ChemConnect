package info.esblurock.reaction.client.async;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import info.esblurock.reaction.data.image.ImageServiceInformation;

@RemoteServiceRelativePath("images")
public interface UserImageService  extends RemoteService  {
	static public String uploadName = "image";
	static public String bucketName = "images";
	static public String uploadRoot = "/upload";
	static public String sourceFileParameter = "source";
	static public String keywordNameParameter = "keywordName";
	
	ImageServiceInformation getBlobstoreUploadUrl(String keywordName, boolean uploadService);
}
