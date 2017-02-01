package info.esblurock.reaction.client.ui.login;

import info.esblurock.reaction.client.async.LoginService;
import info.esblurock.reaction.client.async.LoginServiceAsync;
import info.esblurock.reaction.client.ui.login.UiImplementationBase;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class ClientLogout {
	
	public void logout(UiImplementationBase top) {
		Cookies.removeCookie("user");
		Cookies.removeCookie("sid");
		LoginServiceAsync async = LoginService.Util.getInstance();
		((ServiceDefTarget) async).setServiceEntryPoint("loginservice");
		async.logout(new LogoutCallback(top));
		
	}
}
