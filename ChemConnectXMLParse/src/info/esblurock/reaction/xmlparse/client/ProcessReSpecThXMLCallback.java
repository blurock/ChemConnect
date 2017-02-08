package info.esblurock.reaction.xmlparse.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import gwt.material.design.client.ui.MaterialToast;
import info.esblurock.reaction.data.chemical.respect.ReSpecTHXMLFileBase;
import info.esblurock.reaction.xmlparse.client.xmlfiles.XMLDataSource;

public class ProcessReSpecThXMLCallback implements AsyncCallback<ReSpecTHXMLFileBase> {

	XMLDataSource datasource;
	String filename;
	
	public ProcessReSpecThXMLCallback(XMLDataSource datasource, String filename) {
		this.datasource = datasource;
		this.filename = filename;
	}
	
	
	@Override
	public void onFailure(Throwable caught) {
		MaterialToast.fireToast(filename + ":   Error in parsing: retry");
	}

	@Override
	public void onSuccess(ReSpecTHXMLFileBase result) {
		datasource.insertXMLInfo(result);
	}

}
