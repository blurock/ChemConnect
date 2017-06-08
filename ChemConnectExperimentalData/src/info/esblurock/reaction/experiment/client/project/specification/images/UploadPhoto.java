package info.esblurock.reaction.experiment.client.project.specification.images;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;

import gwt.material.design.addins.client.fileuploader.base.UploadFile;
import gwt.material.design.addins.client.fileuploader.events.SuccessEvent;

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

import info.esblurock.reaction.client.async.UserImageService;
import info.esblurock.reaction.client.async.UserImageServiceAsync;

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
	

	public UploadPhoto() {
		initWidget(uiBinder.createAndBindUi(this));
		uploadButton.setText("Loading...");
		uploadButton.setEnabled(false);
		uploadField.setName("image");
		// Now we use out GWT-RPC service and get an URL
		startNewBlobstoreSession();
		uploadForm.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {

			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				Window.alert("UploadPhoto() 4" + event.getResults());
				uploadForm.reset();
				startNewBlobstoreSession();
				// This is what gets the result back - the content-type *must*
				// be
				// text-html
				String imageUrl = event.getResults();
				
				if(imageUrl == null) 
					imageUrl = "http://0.0.0.0:8888/_ah/img/7lBwuUzlzHmYxWhfSDLNXQ";
				Image image = new Image();
				
				image.setUrl(imageUrl);

				final PopupPanel imagePopup = new PopupPanel(true);
				imagePopup.setWidget(image);

				// Add some effects
				imagePopup.setAnimationEnabled(true); // animate opening the
														// image
				imagePopup.setGlassEnabled(true); // darken everything under the
													// image
				imagePopup.setAutoHideEnabled(true); // close image when the
														// user clicks
														// outside it
				imagePopup.center(); // center the image
				
			}
		});
	}

	private void startNewBlobstoreSession() {
		userImageService.getBlobstoreUploadUrl(new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("startNewBlobstoreSession: " + caught.toString());
			}

			@Override
			public void onSuccess(String result) {
				uploadForm.setAction(result);
				uploadButton.setText("Upload");
				uploadButton.setEnabled(true);
				uploadForm.setEncoding(FormPanel.ENCODING_MULTIPART);
				uploadForm.setMethod(FormPanel.METHOD_POST);
			}

		} );
	}

	@UiHandler("uploadButton")
	void onSubmit(ClickEvent e) {
		Window.alert("Filename: " + uploadField.getFilename() + "  Name: " + uploadField.getName());
		uploadForm.submit();
	}

	public void setText(String text) {
		uploadButton.setText(text);
	}

	public String getText() {
		return uploadButton.getText();
	}

}
