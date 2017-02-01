package info.esblurock.reaction.client.ui.login;

import com.google.gwt.user.client.ui.Composite;

import info.esblurock.reaction.data.UserDTO;

public class UiImplementationBase  extends Composite {
	
	protected UserDTO user = null;
	String name;
	
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public UserDTO getUser() {
		UserDTO user = null;
		return user;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLoggedOut() {
		
	}
	public void setLoggedIn() {
	}

}
