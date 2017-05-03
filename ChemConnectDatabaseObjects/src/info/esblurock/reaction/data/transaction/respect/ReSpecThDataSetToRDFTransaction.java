package info.esblurock.reaction.data.transaction.respect;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import info.esblurock.reaction.data.DatabaseObject;

@PersistenceCapable
public class ReSpecThDataSetToRDFTransaction extends DatabaseObject {
	
    @Persistent
    String user;
  
    @Persistent
    String fileCode;
    
    @Persistent
    String keyWord;

	public ReSpecThDataSetToRDFTransaction() {
	}

	public ReSpecThDataSetToRDFTransaction(String user, String fileCode, String keyWord) {
		super();
		this.user = user;
		this.fileCode = fileCode;
		this.keyWord = keyWord;
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
}
