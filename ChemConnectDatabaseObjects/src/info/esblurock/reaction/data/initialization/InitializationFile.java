package info.esblurock.reaction.data.initialization;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import info.esblurock.reaction.data.DatabaseObject;

@SuppressWarnings("serial")
@PersistenceCapable
public class InitializationFile extends DatabaseObject {

    @Persistent
	String fileName;

	public InitializationFile() {
		
	}
		public InitializationFile(String fileName) {
		super();
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

}
