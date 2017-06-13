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
    BlobKey blobKey;

    @Persistent
    String imageUrl;

    @Persistent
    String filename;

	public UploadedImage(String user, String fileCode, String keyWord, BlobKey blobKey, String imageUrl, String filename) {
		super();
		this.user = user;
		this.fileCode = fileCode;
		this.keyWord = keyWord;
		this.blobKey = blobKey;
		this.imageUrl = imageUrl;
		this.filename = filename;
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

	public BlobKey getBlobKey() {
		return blobKey;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getFilename() {
		return filename;
	}
    
    
}
