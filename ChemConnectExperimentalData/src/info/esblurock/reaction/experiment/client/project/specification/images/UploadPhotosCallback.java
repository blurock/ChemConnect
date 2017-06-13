package info.esblurock.reaction.experiment.client.project.specification.images;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;

import info.esblurock.reaction.data.image.UploadedImage;

public class UploadPhotosCallback implements AsyncCallback<ArrayList<UploadedImage>>{

	UploadPhoto upload;
	
	
	public UploadPhotosCallback(UploadPhoto upload) {
		this.upload = upload;
	}

	@Override
	public void onFailure(Throwable caught) {
		Window.alert(caught.toString());
	}

	@Override
	public void onSuccess(ArrayList<UploadedImage> result) {
		Window.alert("UploadPhotosCallback: " + result.size());
		for(UploadedImage imageinfo : result) {
			Window.alert("UploadPhotosCallback: " + imageinfo.getFilename() + ":     " + imageinfo.getImageUrl());
			Image image = new Image();
			image.setUrl(imageinfo.getImageUrl());
			upload.addImage(imageinfo.getFilename(), image);
		}
	}

}
