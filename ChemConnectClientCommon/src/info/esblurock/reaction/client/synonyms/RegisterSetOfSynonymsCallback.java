package info.esblurock.reaction.client.synonyms;

import com.google.gwt.user.client.rpc.AsyncCallback;

import gwt.material.design.client.ui.MaterialToast;

public class RegisterSetOfSynonymsCallback implements AsyncCallback<String> {

	@Override
	public void onFailure(Throwable caught) {
		MaterialToast.fireToast(caught.toString());
	}

	@Override
	public void onSuccess(String result) {
		MaterialToast.fireToast(result);
	}


}
