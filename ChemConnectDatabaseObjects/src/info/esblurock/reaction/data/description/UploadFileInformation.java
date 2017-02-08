package info.esblurock.reaction.data.description;

import java.io.Serializable;

public class UploadFileInformation implements Serializable{

	private static final long serialVersionUID = 1L;
	
	String fullText;
	String filename;
	String filetype;
	String titleReference;
	
	public UploadFileInformation() {
	}
	
	public UploadFileInformation(String fullText, String filename, String filetype, String titleReference) {
		super();
		this.fullText = fullText;
		this.filename = filename;
		this.filetype = filetype;
		this.titleReference = titleReference;
	}
	public String getFullText() {
		return fullText;
	}
	public String getFilename() {
		return filename;
	}
	public String getFiletype() {
		return filetype;
	}
	public String getTitleReference() {
		return titleReference;
	}
	
	

}
