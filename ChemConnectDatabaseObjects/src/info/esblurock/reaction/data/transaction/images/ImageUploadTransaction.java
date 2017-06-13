package info.esblurock.reaction.data.transaction.images;

import javax.jdo.annotations.Persistent;

import info.esblurock.reaction.data.DatabaseObject;

@SuppressWarnings("serial")
public class ImageUploadTransaction extends DatabaseObject {

	@Persistent
    String user;
  
    @Persistent
    String fileCode;
    
    @Persistent
    String keyWord;

	public ImageUploadTransaction(String user, String fileCode, String keyWord) {
		super();
		this.user = user;
		this.fileCode = fileCode;
		this.keyWord = keyWord;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getFileCode() {
		return fileCode;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

    
}
