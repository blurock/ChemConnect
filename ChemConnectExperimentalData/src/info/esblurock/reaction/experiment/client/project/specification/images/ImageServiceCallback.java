package info.esblurock.reaction.experiment.client.project.specification.images;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FormPanel;

import info.esblurock.reaction.data.image.ImageServiceInformation;

public class ImageServiceCallback implements AsyncCallback<ImageServiceInformation>{

	UploadPhoto uploadPhoto;
	
	public ImageServiceCallback(UploadPhoto uploadPhoto) {
		this.uploadPhoto = uploadPhoto;
	}

	@Override
	public void onFailure(Throwable caught) {
		Window.alert("startNewBlobstoreSession: " + caught.toString());
	}

	@Override
	public void onSuccess(ImageServiceInformation result) {
		Window.alert("ImageServiceCallback:   startNewBlobstoreSession(): Success");
		uploadPhoto.fillUpload(result);
	}

}
