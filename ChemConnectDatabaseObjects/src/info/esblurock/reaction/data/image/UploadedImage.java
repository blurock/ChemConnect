package info.esblurock.reaction.data.image;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.blobstore.BlobKey;

import info.esblurock.reaction.data.DatabaseObject;

@SuppressWarnings("serial")
@PersistenceCapable
public class UploadedImage extends DatabaseObject {
	
	@Persistent
    String user;
  
    @Persistent
    String fileCode;
    
    @Persistent
    String keyWord;
    
    @Persistent
    String blobKey;

    @Persistent
    String imageUrl;

    @Persistent
    String filename;

    @Persistent
    String description;

    public UploadedImage() {
    }
    
	public UploadedImage(String user, String fileCode, String keyWord, String blobKey, 
			String imageUrl, String filename, String description) {
		super();
		this.user = user;
		this.fileCode = fileCode;
		this.keyWord = keyWord;
		this.blobKey = blobKey;
		this.imageUrl = imageUrl;
		this.filename = filename;
		this.description = description;
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

	public String getBlobKey() {
		return blobKey;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getFilename() {
		return filename;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
    
}
