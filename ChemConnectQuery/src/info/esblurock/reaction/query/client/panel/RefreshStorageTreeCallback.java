package info.esblurock.reaction.query.client.panel;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import info.esblurock.reaction.data.store.UserStorageObjectTreeNode;

public class RefreshStorageTreeCallback implements AsyncCallback<UserStorageObjectTreeNode>  {

	UserStorageObjectWindow storageWindow;
	
	public RefreshStorageTreeCallback() {
	}
	
	public RefreshStorageTreeCallback(UserStorageObjectWindow storageWindow) {
		this.storageWindow = storageWindow;
	}
	
	@Override
	public void onFailure(Throwable caught) {
		Window.alert(caught.toString());
	}

	@Override
	public void onSuccess(UserStorageObjectTreeNode result) {
		storageWindow.insertTree(result);
	}

}
