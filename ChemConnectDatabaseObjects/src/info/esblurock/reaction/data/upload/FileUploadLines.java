package info.esblurock.reaction.data.upload;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;



@PersistenceCapable
public class FileUploadLines implements Serializable {
	private static final long serialVersionUID = 1L;

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
    private String key;
	
    @Persistent
    int count;
    
    @Persistent
    String line;

    @Persistent
    String fileCode;

	public FileUploadLines() {
	}
	public FileUploadLines(int count, String line, String fileCode) {
		super();
		this.count = count;
		this.line = line;
		this.fileCode = fileCode;
	}

	public String getKey() {
		return key;
	}

	public String getLine() {
		return line;
	}
    public String getFileCode() {
    	return fileCode;
    }


}
