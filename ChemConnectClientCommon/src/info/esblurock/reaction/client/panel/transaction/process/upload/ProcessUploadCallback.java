package info.esblurock.reaction.client.panel.transaction.process.upload;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import gwt.material.design.client.constants.ModalType;
import gwt.material.design.client.ui.MaterialToast;

public class ProcessUploadCallback  implements AsyncCallback<String> {

	@Override
	public void onFailure(Throwable caught) {
		Window.alert(caught.toString());
	}

	@Override
	public void onSuccess(String result) {
		MaterialToast.fireToast("Success: " + result);
		/*
		TextMessagePopup popup = new TextMessagePopup("Success", result);
		popup.openModal(ModalType.FIXED_FOOTER);
		*/
	}


}
