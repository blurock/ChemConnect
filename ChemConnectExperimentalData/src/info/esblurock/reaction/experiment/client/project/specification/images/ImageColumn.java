package info.esblurock.reaction.experiment.client.project.specification.images;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialTextArea;
import info.esblurock.reaction.client.async.UserImageService;
import info.esblurock.reaction.client.async.UserImageServiceAsync;
import info.esblurock.reaction.data.image.UploadedImage;

import com.google.gwt.user.client.ui.Image;


public class ImageColumn extends Composite implements HasText {

	private static ImageColumnUiBinder uiBinder = GWT.create(ImageColumnUiBinder.class);

	interface ImageColumnUiBinder extends UiBinder<Widget, ImageColumn> {
	}

	String elementName;
	

	@UiField
	MaterialLink title;
	@UiField
	MaterialLink delete;
	@UiField
	HTMLPanel imagepanel;
	@UiField
	MaterialTextArea textDescription;
	
	UploadedImage imageinfo;
	
	public ImageColumn() {
		initWidget(uiBinder.createAndBindUi(this));
		imageinfo = null;
	}

	public ImageColumn(UploadedImage imageinfo) {
		initWidget(uiBinder.createAndBindUi(this));
		this.imageinfo = imageinfo;
		Image image = new Image();
		image.setUrl(imageinfo.getImageUrl());
		this.title.setText(imageinfo.getFilename());
		imagepanel.add(image);
		textDescription.setText(imageinfo.getDescription());
	}
	
	public UploadedImage getUpdatedImageInfo() {
		imageinfo.setDescription(textDescription.getText());
		return imageinfo;
	}
	
	@UiHandler("delete")
	public void onDelete(ClickEvent event) {
		DeleteImageCallback callback = new DeleteImageCallback(this);
		UserImageServiceAsync userImageService = GWT.create(UserImageService.class);
		userImageService.deleteFromStorage(imageinfo.getBlobKey(), callback);
	}
	

	public void setText(String title) {
		this.title.setText(title);
	}
	
	public String getText() {
		return title.getText();
	}

}