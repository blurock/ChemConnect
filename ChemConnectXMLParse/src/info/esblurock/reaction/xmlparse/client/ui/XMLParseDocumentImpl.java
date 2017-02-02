package info.esblurock.reaction.xmlparse.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialCollapsible;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialTextArea;
import gwt.material.design.client.ui.MaterialTextBox;
import info.esblurock.reaction.client.ui.view.XMLParseDocumentView;

public class XMLParseDocumentImpl extends Composite implements XMLParseDocumentView {

	private static XMLParseDocumentImplUiBinder uiBinder = GWT.create(XMLParseDocumentImplUiBinder.class);

	interface XMLParseDocumentImplUiBinder extends UiBinder<Widget, XMLParseDocumentImpl> {
	}

	String name;
	Presenter listener;
	
	@UiField
	MaterialLink inputFiles;
	@UiField
	MaterialTextBox dataSetName;
	@UiField
	MaterialTextBox htmlText;
	@UiField
	MaterialTextArea textArea;
	@UiField
	MaterialLink sourceFiles;
	@UiField
	MaterialCollapsible setOfSourceFiles;
	@UiField
	MaterialLink dataSets;	
	@UiField
	MaterialCollapsible setOfDataSets;
	
	public XMLParseDocumentImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		name = "XMLParse";
		init();
	}


	public XMLParseDocumentImpl(String token) {
		initWidget(uiBinder.createAndBindUi(this));
		name = token;
		init();
	}

	void init() {
		inputFiles.setText("Input files");
		sourceFiles.setText("File Sources");
		dataSetName.setPlaceholder("Data Set Name");
		htmlText.setPlaceholder("HTML address");
	}
	public void setText(String text) {
		name = text;
	}

	public String getText() {
		return name;
	}

	@Override
	public void setName(String token) {
		name = token;
	}


	@Override
	public void setPresenter(Presenter listener) {
		this.listener = listener;
	}

}
