package info.esblurock.reaction.xmlparse.client.ui.respect.process;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import gwt.material.design.client.ui.MaterialToast;
import info.esblurock.reaction.client.async.TextToDatabase;
import info.esblurock.reaction.client.async.TextToDatabaseAsync;
import info.esblurock.reaction.data.GenerateKeywordFromDescription;
import info.esblurock.reaction.data.chemical.respect.ReSpecTHXMLFileBase;

public class RegisterReSpecThDataCallback implements AsyncCallback<String> {

	ArrayList<ReSpecTHXMLFileBase> experimentdata;
	ArrayList<String> fileNames;
	String keyword;
	
	public RegisterReSpecThDataCallback(String keyword, ArrayList<String> fileNames, ArrayList<ReSpecTHXMLFileBase> experimentdata) {
		this.experimentdata = experimentdata;
		this.keyword = keyword;
		this.fileNames = fileNames;
	}

	@Override
	public void onFailure(Throwable caught) {
		Window.alert("ReSpecTh data not registered: \n" + caught.toString());
	}

	@Override
	public void onSuccess(String result) {
		MaterialToast.fireToast("ReSpecTh data set description and references registered");
		TextToDatabaseAsync async = TextToDatabase.Util.getInstance();
		RegisterReSpecThInputFileDataCallback callback = new RegisterReSpecThInputFileDataCallback();
		async.registerReSpecThExperimentalData(keyword,fileNames, experimentdata, callback);
	}

}
