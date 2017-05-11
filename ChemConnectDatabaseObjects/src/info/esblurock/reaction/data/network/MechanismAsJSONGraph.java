package info.esblurock.reaction.data.network;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Text;

import info.esblurock.reaction.data.DatabaseObject;

@SuppressWarnings("serial")
@PersistenceCapable
public class MechanismAsJSONGraph extends DatabaseObject {
	@Persistent
	String dataset;
	@Persistent
	String fileCode;
	@Persistent
	Text JsonText;

	public MechanismAsJSONGraph() {
		super();
	}

	public MechanismAsJSONGraph(String dataset, String fileCode, Text jsonText) {
		super();
		this.dataset = dataset;
		this.fileCode = fileCode;
		JsonText = jsonText;
	}

	public String getDataset() {
		return dataset;
	}

	public String getFileCode() {
		return fileCode;
	}

	public Text getJsonText() {
		return JsonText;
	}

	
	
}
