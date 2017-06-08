package info.esblurock.reaction.experiment.client.project.specification;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialImage;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialTextArea;

public class ImageSpecification extends Composite implements HasText {

	private static ImageSpecificationUiBinder uiBinder = GWT.create(ImageSpecificationUiBinder.class);

	interface ImageSpecificationUiBinder extends UiBinder<Widget, ImageSpecification> {
	}

	public ImageSpecification() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	MaterialLink specification;
	@UiField
	MaterialLink delete;
	@UiField
	MaterialImage imageObject;
	@UiField
	MaterialTextArea imageDescription;

	public ImageSpecification(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		specification.setText(firstName);
	}

	@UiHandler("delete")
	void onClick(ClickEvent e) {
		Window.alert("Hello!");
	}

	public void setText(String text) {
		specification.setText(text);
	}

	public String getText() {
		return specification.getText();
	}

}
