package info.esblurock.reaction.xmlparse.client.ui;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCollapsible;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialModal;
import gwt.material.design.client.ui.MaterialTextBox;
import info.esblurock.reaction.xmlparse.client.ui.respect.ReSpecThDataSet;
import info.esblurock.reaction.xmlparse.client.xmlfiles.XMLDataSource;
import info.esblurock.reaction.xmlparse.resources.XMLParseResource;

public class ReSpecThCommonPaperSet extends Composite implements HasText {

	private static ReSpecThCommonPaperSetUiBinder uiBinder = GWT.create(ReSpecThCommonPaperSetUiBinder.class);

	interface ReSpecThCommonPaperSetUiBinder extends UiBinder<Widget, ReSpecThCommonPaperSet> {
	}

	public ReSpecThCommonPaperSet() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	XMLParseResource resource = GWT.create(XMLParseResource.class);
	
	@UiField
	MaterialLink label;
	@UiField
	MaterialCollapsible experiments;
	@UiField
	MaterialLink sendtodataset;
	@UiField
	MaterialModal addToDataSet;
	@UiField
	MaterialButton addDataSetButton;
	@UiField
	MaterialButton closeButton;
	@UiField
	MaterialTextBox source;
	@UiField
	MaterialTextBox keyword;

	XMLParseDocumentImpl top;

	public ReSpecThCommonPaperSet(String firstName, XMLParseDocumentImpl top) {
		initWidget(uiBinder.createAndBindUi(this));
		label.setText(firstName);
		this.top = top;
		init();
	}

	void init() {
		source.setText(resource.source());
		keyword.setText(resource.keyword());
		addDataSetButton.setText(resource.newdataset());
		closeButton.setText(resource.close());
	}

	public double percentMatch(String name) {
		double sze = name.length();
		int index = 0;
		String l = label.getText();
		while (name.charAt(index) == l.charAt(index)) {
			index++;
		}
		double percent = (((double) index) / sze);
		return percent;
	}

	public void setText(String text) {
		label.setText(text);
	}

	public String getText() {
		return label.getText();
	}

	public void addExperiment(XMLDataSource display) {
		experiments.add(display);
	}

	@UiHandler("sendtodataset")
	void onSendToDataSet(ClickEvent e) {
		addToDataSet.openModal();
	}

	@UiHandler("closeButton")
	void onCloseAddModel(ClickEvent e) {
		addToDataSet.closeModal();
	}

	@UiHandler("addDataSetButton")
	void onAddDataSetButton(ClickEvent e) {

		if (source.getText().compareToIgnoreCase(resource.source()) != 0
				&& keyword.getText().compareToIgnoreCase(resource.keyword()) != 0) {
			ReSpecThDataSet dataset = new ReSpecThDataSet(source.getText(), keyword.getText());
			top.addSetOfExperiments(dataset);
			int sze = experiments.getWidgetCount();
			ArrayList<XMLDataSource> data = new ArrayList<XMLDataSource>();
			for (int i = 0; i < sze; i++) {
				XMLDataSource display = (XMLDataSource) experiments.getWidget(i);
				data.add(display);
			}
			dataset.addSet(data);
		}
		addToDataSet.closeModal();
	}
}
