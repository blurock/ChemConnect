package info.esblurock.reaction.experiment.client;

import java.util.Date;

import com.google.gwt.user.client.Cookies;
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
		if (result.getLoggedIn()) {
			String sessionID = result.getSessionId();
			final long DURATION = 1000 * 60 * 60;
			Date expires = new Date(System.currentTimeMillis()
					+ DURATION);
			Cookies.setCookie("sid", sessionID, expires, null,
					"/", false);
			Cookies.setCookie("user", result.getName(),
					expires, null, "/", false);
			Cookies.setCookie("level", result.getUserLevel(),
					expires, null, "/", false);
		} else {
			Window.alert("Access Denied. Check your user-name and password.");
		}
	}

}
