package info.esblurock.reaction.client.panel.transaction.process;

import gwt.material.design.client.ui.MaterialToast;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class RemoveTransactionCallback  implements AsyncCallback<String>{

	
	@Override
	public void onFailure(Throwable caught) {
		Window.alert(caught.toString());
	}

	@Override
	public void onSuccess(String result) {
		MaterialToast.fireToast("Success: " + result);
	}

}
