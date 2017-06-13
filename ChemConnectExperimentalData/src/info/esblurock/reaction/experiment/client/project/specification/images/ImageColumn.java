package info.esblurock.reaction.experiment.client.project.specification.images;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialLink;
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
	
	public ImageColumn(UploadPhoto upload) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public ImageColumn(String title, Image image) {
		initWidget(uiBinder.createAndBindUi(this));
		this.title.setText(title);
		imagepanel.add(image);
	}
	
	public void setText(String title) {
		this.title.setText(title);
	}
	
	public String getText() {
		return title.getText();
	}

}
