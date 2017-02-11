package info.esblurock.reaction.xmlparse.client;

import java.io.IOException;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import gwt.material.design.client.ui.MaterialToast;
import info.esblurock.reaction.xmlparse.client.ui.XMLParseDocumentImpl;
import info.esblurock.reaction.xmlparse.client.xmlfiles.XMLDataSource;

public class UploadXMLFileCallback implements AsyncCallback<String> {

	XMLParseDocumentImpl top;
	String filename;
	
	public UploadXMLFileCallback(XMLParseDocumentImpl top) {
		this.top = top;
	}
	
	@Override
	public void onFailure(Throwable caught) {
		if(caught.toString().contains("NOTFOUND")) {
			Window.alert("Retry read: " + filename);
			XMLDataSource display = new XMLDataSource(filename,top);
			top.addDataSource(display);
		} else {
			Window.alert(caught.toString());
			XMLDataSource display = new XMLDataSource(filename,top);
			top.addDataSource(display);
		}
	}

	@Override
	public void onSuccess(String result) {
			XMLDataSource display = new XMLDataSource(filename, result,top);
			top.addDataSource(display);
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
