package info.esblurock.reaction.xmlparse.client.xmlfiles;

import java.io.IOException;
import java.util.Comparator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;


import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialTextArea;
import info.esblurock.reaction.client.async.ProcessReSpecTh;
import info.esblurock.reaction.client.async.ProcessReSpecThAsync;
import info.esblurock.reaction.client.async.TextToDatabase;
import info.esblurock.reaction.client.async.TextToDatabaseAsync;
import info.esblurock.reaction.data.chemical.respect.ReSpecTHXMLFileBase;
import info.esblurock.reaction.xmlparse.client.ProcessReSpecThXMLCallback;
import info.esblurock.reaction.xmlparse.client.UploadXMLFileCallback;
import info.esblurock.reaction.xmlparse.client.ui.XMLParseDocumentImpl;

public class XMLDataSource extends Composite implements HasText, Comparable<XMLDataSource> {

	private static XMLDataSourceUiBinder uiBinder = GWT.create(XMLDataSourceUiBinder.class);

	interface XMLDataSourceUiBinder extends UiBinder<Widget, XMLDataSource> {
	}

	public XMLDataSource() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	MaterialLink fileName;
	@UiField
	MaterialLink reference;
	@UiField
	MaterialTextArea fileText;
	@UiField
	MaterialLink retryread;
	@UiField
	MaterialLink delete;
	
	boolean fileRead;
	boolean xmlParsed;
	ReSpecTHXMLFileBase parsedxml;
	XMLParseDocumentImpl top;
	
	public XMLDataSource(String filename,XMLParseDocumentImpl top) {
		initWidget(uiBinder.createAndBindUi(this));
		init(filename);
		reference.setText(filename);
		this.top = top;
	}

	public XMLDataSource(String filename, String text,XMLParseDocumentImpl top) {
		initWidget(uiBinder.createAndBindUi(this));
		init(filename);
		setSourceText(text);
		this.top = top;
	}

	void init(String filename) {
		fileName.setText(filename);
		fileRead = false;
		xmlParsed = false;
		retryread.setVisible(true);
		delete.setVisible(true);
	}

	public void setSourceText(String source) {
		fileText.setText(source);
		fileRead = true;
		xmlParsed = false;
		retryread.setVisible(true);
		ProcessReSpecThAsync async = ProcessReSpecTh.Util.getInstance();
		ProcessReSpecThXMLCallback callback = new ProcessReSpecThXMLCallback(this,fileName.getText());
		async.parseReSpecThXML(source, callback);
	}
	public void insertXMLInfo(ReSpecTHXMLFileBase parsedxml) {
		reference.setText(parsedxml.getBibliographyLink());
		retryread.setVisible(false);
		this.parsedxml = parsedxml;
		
	}
	
	public void setText(String text) {
		fileName.setText(text);
	}

	public String getText() {
		return fileName.getText();
	}

	@UiHandler("retryread")
	void onRetryClick(ClickEvent event) {
		if(fileRead) {
			if(xmlParsed) {
				retryread.setVisible(false);
			} else {
				ProcessReSpecThAsync async = ProcessReSpecTh.Util.getInstance();
				ProcessReSpecThXMLCallback callback = new ProcessReSpecThXMLCallback(this,fileName.getText());
				async.parseReSpecThXML(fileText.getText(), callback);
			}
		} else {
			UploadXMLFileCallback uploadCallback = new UploadXMLFileCallback(top);
			uploadCallback.setFilename(fileName.getText());
			TextToDatabaseAsync async = TextToDatabase.Util.getInstance();
			async.fileFromUploadFileTransactionSession(fileName.getText(), uploadCallback);
			
		}
	}

	@Override
	public int compareTo(XMLDataSource o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
