package info.esblurock.reaction.server.process.respect;

import java.util.ArrayList;

import info.esblurock.reaction.data.chemical.respect.ReSpecTHXMLFileBase;
import info.esblurock.reaction.server.process.ProcessInputSpecificationsBase;

public class ReSpecThDataBaseInputSpecification extends ProcessInputSpecificationsBase {

	ArrayList<ReSpecTHXMLFileBase> experimentdata;
	ArrayList<String> fileNames;
	public ReSpecThDataBaseInputSpecification(String userName, String keyword, String sourceCode,
			ArrayList<String> fileNames,
			ArrayList<ReSpecTHXMLFileBase> experimentdata) {
		super(userName, keyword, sourceCode);
		this.experimentdata = experimentdata;
		this.fileNames = fileNames;
	}

	public ArrayList<ReSpecTHXMLFileBase> getExperimentdata() {
		return experimentdata;
	}

	public ArrayList<String> getFileNames() {
		return fileNames;
	}
	
}
