package info.esblurock.reaction.experiment.client.project.specification.images;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import gwt.material.design.client.ui.MaterialToast;
import info.esblurock.reaction.client.async.UserImageServiceAsync;

public class DeleteImageCallback implements AsyncCallback<String> {

	ImageColumn image;
	
	public DeleteImageCallback(ImageColumn image) {
		this.image = image;
	}
	@Override
	public void onFailure(Throwable caught) {
		Window.alert(caught.toString());
	}

	@Override
	public void onSuccess(String result) {
		MaterialToast.fireToast(result);
		image.removeFromParent();
	
	}

}
