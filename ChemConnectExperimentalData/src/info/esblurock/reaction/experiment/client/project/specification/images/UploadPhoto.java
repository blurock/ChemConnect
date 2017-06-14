package info.esblurock.reaction.experiment.client.project.specification.images;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;


import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialCollapsible;
import info.esblurock.reaction.client.async.UserImageService;
import info.esblurock.reaction.client.async.UserImageServiceAsync;
import info.esblurock.reaction.data.image.ImageServiceInformation;
import info.esblurock.reaction.data.image.UploadedImage;

public class UploadPhoto extends Composite implements HasText {

	private static UploadPhotoUiBinder uiBinder = GWT.create(UploadPhotoUiBinder.class);

	interface UploadPhotoUiBinder extends UiBinder<Widget, UploadPhoto> {
	}

	UserImageServiceAsync userImageService = GWT.create(UserImageService.class);

	@UiField
	Button uploadButton;
	@UiField
	FormPanel uploadForm;
	@UiField
	FileUpload uploadField;
	@UiField
	MaterialCollapsible collapsible;

	String keywordName;
	ImageServiceInformation serviceInformation;
	
	public UploadPhoto(String keywordName) {
		initWidget(uiBinder.createAndBindUi(this));
		this.keywordName = keywordName;
		// Now we use out GWT-RPC service and get an URL
		startNewBlobstoreSession(true);
		uploadButton.setText("Loading...");
		uploadButton.setEnabled(true);

		uploadForm.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {

			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				uploadForm.reset();
				startNewBlobstoreSession(false);
			}
			
		});
	}

	public void startNewBlobstoreSession(boolean uploadService) {
		ImageServiceCallback callback = new ImageServiceCallback(this);
		userImageService.getBlobstoreUploadUrl(keywordName, uploadService, callback);
	}

	public void fillUpload(ImageServiceInformation result) {
		uploadField.setName("image");
		uploadButton.setText("Upload");
		uploadButton.setEnabled(true);
		uploadForm.setEncoding(FormPanel.ENCODING_MULTIPART);
		uploadForm.setMethod(FormPanel.METHOD_POST);
		if(result.getFileCode() != null) {
			serviceInformation = result;
		} else {
			Window.alert("UploadPhoto.fillUpload: " + serviceInformation.getFileCode());
			UploadPhotosCallback callback = new UploadPhotosCallback(this);
			userImageService.getUploadedImageSet(serviceInformation, callback);
		}
	}
	
	public void addImage(UploadedImage imageinfo) {
		Window.alert("UploadPhoto.addImage: " + imageinfo.getFileCode());
		
		ImageColumn imagecollapse = new ImageColumn(imageinfo);
		collapsible.add(imagecollapse);
	}
	
	@UiHandler("uploadButton")
	void onSubmit(ClickEvent e) {
		Window.alert("UploadPhoto.onSubmit: " + serviceInformation.getUploadUrl());
		uploadForm.setAction(serviceInformation.getUploadUrl());
		uploadForm.submit();
	}

	public void setText(String text) {
		uploadButton.setText(text);
	}

	public String getText() {
		return uploadButton.getText();
	}

}
