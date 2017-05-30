package info.esblurock.reaction.experiment.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import gwt.material.design.client.ui.MaterialToast;

public class DataInitializationCallback implements AsyncCallback<Void> {

	@Override
	public void onFailure(Throwable caught) {
		Window.alert(caught.toString());
	}

	@Override
	public void onSuccess(Void result) {
		MaterialToast.fireToast("Data Initialization");
	}

}
