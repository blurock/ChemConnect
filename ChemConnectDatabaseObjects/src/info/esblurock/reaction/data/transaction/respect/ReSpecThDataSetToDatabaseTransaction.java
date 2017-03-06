package info.esblurock.reaction.data.transaction.respect;

import java.util.ArrayList;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import info.esblurock.reaction.data.DatabaseObject;

@PersistenceCapable
public class ReSpecThDataSetToDatabaseTransaction extends DatabaseObject {

    @Persistent
    String user;
  
    @Persistent
    String fileCode;
    
    @Persistent
    String keyWord;
    
    @Persistent
    ArrayList<String> filenames;

    public ReSpecThDataSetToDatabaseTransaction() {
		super(); 
    }
	public ReSpecThDataSetToDatabaseTransaction(String user, String fileCode, String keyWord,
			ArrayList<String> filenames) {
		super();
		this.user = user;
		this.fileCode = fileCode;
		this.keyWord = keyWord;
		this.filenames = filenames;
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

	public ArrayList<String> getFilenames() {
		return filenames;
	}

    
}
