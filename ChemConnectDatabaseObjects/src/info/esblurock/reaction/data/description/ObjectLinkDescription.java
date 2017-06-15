package info.esblurock.reaction.data.description;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.datanucleus.annotations.Unindexed;

import info.esblurock.reaction.data.DatabaseObject;

@SuppressWarnings("serial")
@PersistenceCapable
public class ObjectLinkDescription extends DatabaseObject {
	@Persistent
	String DatasetKeyword;

	@Persistent
	@Unindexed
	String title;
	
	@Persistent
	String linkString;
	
	@Persistent
	String linkDescription;

	public ObjectLinkDescription(String datasetKeyword, String title, String linkString, String linkDescription) {
		super();
		DatasetKeyword = datasetKeyword;
		this.title = title;
		this.linkString = linkString;
		this.linkDescription = linkDescription;
	}

	public String getDatasetKeyword() {
		return DatasetKeyword;
	}

	public String getTitle() {
		return title;
	}

	public String getLinkString() {
		return linkString;
	}

	public String getLinkDescription() {
		return linkDescription;
	}
		
		
	
}
