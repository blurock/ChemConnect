package info.esblurock.reaction.xmlparse.client.ui.respect.process;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import gwt.material.design.client.ui.MaterialToast;

public class RegisterReSpecThInputFileDataCallback implements AsyncCallback<String> {

	@Override
	public void onFailure(Throwable caught) {
		Window.alert("ReSpecTh data not registered: \n" + caught.toString());
	}

	@Override
	public void onSuccess(String result) {
		MaterialToast.fireToast(result);
	}

}
