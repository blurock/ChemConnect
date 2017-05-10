package info.esblurock.reaction.data.transaction.network;

import javax.jdo.annotations.Persistent;

import info.esblurock.reaction.data.DatabaseObject;

public class MechanismToNetworkTransaction  extends DatabaseObject {
    @Persistent
    String user;
  
    @Persistent
    String fileCode;
    
    @Persistent
    String keyWord;
    public MechanismToNetworkTransaction() {
    }
    
	public MechanismToNetworkTransaction(String user, String fileCode, String keyWord) {
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
