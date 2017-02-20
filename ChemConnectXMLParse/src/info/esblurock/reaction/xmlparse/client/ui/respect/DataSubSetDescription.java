package info.esblurock.reaction.xmlparse.client.ui.respect;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialTextBox;
import info.esblurock.reaction.data.chemical.respect.ReSpecTHXMLFileBase;
import info.esblurock.reaction.xmlparse.resources.XMLParseResource;

public class DataSubSetDescription extends Composite implements HasText {

	private static DataSubSetDescriptionUiBinder uiBinder = GWT.create(DataSubSetDescriptionUiBinder.class);

	interface DataSubSetDescriptionUiBinder extends UiBinder<Widget, DataSubSetDescription> {
	}

	public DataSubSetDescription() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	XMLParseResource resource = GWT.create(XMLParseResource.class);
	@UiField
	MaterialLink description;
	@UiField
	MaterialTextBox version;
	@UiField
	MaterialTextBox respect;
	@UiField
	MaterialTextBox reference;
	@UiField
	MaterialTextBox author;

	public DataSubSetDescription(String desc) {
		initWidget(uiBinder.createAndBindUi(this));
		description.setText(desc);
		reference.setText(desc);
		init();
	}
	
	void init() {
		version.setText(resource.version());
		respect.setText(resource.respect());
		reference.setText(resource.reference());
		author.setText(resource.author());
	}

	public void fill(ReSpecTHXMLFileBase respectbase) {
		description.setText(respectbase.getBibliographyLink());
		version.setText(respectbase.getFileVersion());
		respect.setText(respectbase.getReSpecThVersion());
		reference.setText(respectbase.getBibliographyLink());
		author.setText(respectbase.getFileAuthor());
	}
	@UiHandler("reference")
	void onReference(KeyUpEvent e) {
		description.setText(reference.getText());
	}
	public void setText(String text) {
		description.setText(text);
	}

	public String getText() {
		return description.getText();
	}

}
