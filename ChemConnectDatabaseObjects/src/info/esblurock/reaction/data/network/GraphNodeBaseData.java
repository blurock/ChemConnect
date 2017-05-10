package info.esblurock.reaction.data.network;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import info.esblurock.reaction.data.DatabaseObject;


@SuppressWarnings("serial")
@PersistenceCapable
public class GraphNodeBaseData  extends DatabaseObject {

	@Persistent
	String nodeName;
	@Persistent
	String dataset;
	@Persistent
	String fileCode;
	
	public GraphNodeBaseData() {
		super();
	}
	public GraphNodeBaseData(String nodeName, String dataset, String fileCode) {
		super();
		this.nodeName = nodeName;
		this.dataset = dataset;
		this.fileCode = fileCode;
	}
	public String getNodeName() {
		return nodeName;
	}
	public String getDataset() {
		return dataset;
	}
	public String getFileCode() {
		return fileCode;
	}
	
	

}
