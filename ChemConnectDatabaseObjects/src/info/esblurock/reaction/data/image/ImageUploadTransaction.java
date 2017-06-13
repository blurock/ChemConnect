package info.esblurock.reaction.data.image;

import java.io.Serializable;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import info.esblurock.reaction.data.DatabaseObject;

@SuppressWarnings("serial")
@PersistenceCapable
public class ImageUploadTransaction extends DatabaseObject {

	@Persistent
    String user;

	@Persistent
	String fileCode;
	
    @Persistent
    String keyWord;
    
	@Persistent
	String bucketName;
	
	@Persistent
	String uploadUrl;
	
	public ImageUploadTransaction() {
	}

	public ImageUploadTransaction(String user, String fileCode, String keyWord, String bucketName,String uploadUrl) {
		super();
		this.user = user;
		this.fileCode = fileCode;
		this.keyWord = keyWord;
		this.bucketName = bucketName;
	}

	public String getUser() {
		return user;
	}

	public String getFileCode() {
		return fileCode;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public String getBucketName() {
		return bucketName;
	}

	public String getUploadUrl() {
		return uploadUrl;
	}

	
}
