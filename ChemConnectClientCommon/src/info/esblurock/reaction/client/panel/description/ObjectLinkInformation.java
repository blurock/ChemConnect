package info.esblurock.reaction.client.panel.description;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialTextArea;
import gwt.material.design.client.ui.MaterialTextBox;
import info.esblurock.reaction.data.description.ObjectLinkDescription;

public class ObjectLinkInformation extends Composite implements HasText {

	private static ObjectLinkInformationUiBinder uiBinder = GWT.create(ObjectLinkInformationUiBinder.class);

	interface ObjectLinkInformationUiBinder extends UiBinder<Widget, ObjectLinkInformation> {
	}


	@UiField
	MaterialLink objecttitle;
	@UiField
	MaterialTextBox link;
	@UiField
	MaterialTextArea description;
	@UiField
	MaterialIcon delete;

	public ObjectLinkInformation() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public ObjectLinkInformation(String title) {
		initWidget(uiBinder.createAndBindUi(this));
		objecttitle.setText(title);
	}
	public ObjectLinkInformation(String title, String linktext, String descriptiontext) {
		initWidget(uiBinder.createAndBindUi(this));
		objecttitle.setText(title);
		link.setText(linktext);
		description.setText(descriptiontext);
		
	}
	public ObjectLinkInformation(ObjectLinkDescription description) {
		initWidget(uiBinder.createAndBindUi(this));
		objecttitle.setText(description.getTitle());
		link.setText(description.getLinkString());
		this.description.setText(description.getLinkDescription());		
	}
	
	@UiHandler("delete")
	void onClick(ClickEvent e) {
		this.removeFromParent();
	}

	public void setText(String text) {
		objecttitle.setText(text);
	}

	public String getText() {
		return objecttitle.getText();
	}
	String getTitleString() {
		return objecttitle.getText();
	}
	
	String getLink() {
		return link.getText();
	}
	String getDescription() {
		return link.getText();
	}
}
