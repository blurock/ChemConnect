package info.esblurock.reaction.data.transaction;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import info.esblurock.reaction.data.DatabaseObject;

@SuppressWarnings("serial")
@PersistenceCapable
public class ProcessInProgress  extends DatabaseObject {

    /** The keyword string representing the object being stored. */
    @Persistent
    String keyword;
    
    /** The user who initiated this transaction. */
    @Persistent
    String user;
    
    @Persistent
    String processName;

	public ProcessInProgress() {
	}
	public ProcessInProgress(String keyword, String user, String processName) {
		super();
		this.keyword = keyword;
		this.user = user;
		this.processName = processName;
	}

	public String getKeyword() {
		return keyword;
	}

	public String getUser() {
		return user;
	}

	public String getProcessName() {
		return processName;
	}

    
}
