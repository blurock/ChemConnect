package info.esblurock.reaction.data.network;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import info.esblurock.reaction.data.DatabaseObject;

@PersistenceCapable
@SuppressWarnings("serial")
public class GraphLinkBaseData extends DatabaseObject {
	@Persistent
	String linkName;
	@Persistent
	String dataset;
	@Persistent
	String fileCode;
	@Persistent
	String nodeBegin;
	@Persistent
	String nodeEnd;
	@Persistent
	String linkType;
	
	
	
	public GraphLinkBaseData() {
	}
	public GraphLinkBaseData(String linkName, String dataset, String fileCode, 
			String nodeBegin, String nodeEnd,
			String linkType) {
		super();
		this.linkName = linkName;
		this.dataset = dataset;
		this.fileCode = fileCode;
		this.nodeBegin = nodeBegin;
		this.nodeEnd = nodeEnd;
		this.linkType = linkType;
	}


	public String getLinkName() {
		return linkName;
	}
	public String getDataset() {
		return dataset;
	}
	public String getFileCode() {
		return fileCode;
	}
	public String getNodeBegin() {
		return nodeBegin;
	}
	public String getNodeEnd() {
		return nodeEnd;
	}
	public String getLinkType() {
		return linkType;
	}
	
}
