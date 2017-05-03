package info.esblurock.reaction.experiment.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import gwt.material.design.client.ui.MaterialToast;
import info.esblurock.reaction.data.UserDTO;

public class SimpleLoginCallback implements AsyncCallback<UserDTO> {

	@Override
	public void onFailure(Throwable caught) {
		Window.alert(caught.toString());
	}

	@Override
	public void onSuccess(UserDTO result) {
		MaterialToast.fireToast("Welcome: " + result.getName() + "(" + result.getHostname() + ")");
	}

}
