package info.esblurock.reaction.data.description;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import info.esblurock.reaction.data.DatabaseObject;

@PersistenceCapable
public class DataSetReferenceSimpleString extends DatabaseObject {

	@Persistent
	String referenceString;

	public DataSetReferenceSimpleString() {
	}

	public DataSetReferenceSimpleString(String referenceString) {
		this.referenceString = referenceString;
	}

	public String getReferenceString() {
		return referenceString;
	}

	public void setReferenceString(String referenceString) {
		this.referenceString = referenceString;
	}

	
}
