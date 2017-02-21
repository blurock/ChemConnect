package info.esblurock.reaction.xmlparse.client.ui.respect;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialCollapsible;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialToast;
import info.esblurock.reaction.data.chemical.respect.ReSpecTHXMLFileBase;
import info.esblurock.reaction.xmlparse.client.xmlfiles.XMLDataSource;

public class ReSpecThExperimentDisplay extends Composite implements HasText {

	private static ReSpecThExperimentDisplayUiBinder uiBinder = GWT.create(ReSpecThExperimentDisplayUiBinder.class);

	interface ReSpecThExperimentDisplayUiBinder extends UiBinder<Widget, ReSpecThExperimentDisplay> {
	}

	public ReSpecThExperimentDisplay() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	MaterialLink experiment;
	@UiField
	MaterialCollapsible information;

	XMLDataSource exp;
	ReSpecThDataMatrixDisplay pointsdisplay;
	
	public ReSpecThExperimentDisplay(XMLDataSource exp) {
		initWidget(uiBinder.createAndBindUi(this));
		this.exp = exp;
		String filename = exp.getFileName();
		ReSpecTHXMLFileBase parsed = exp.getParsedFile();
		String name = filename;
		experiment.setText(name);
		DataSubSetDescription description = new DataSubSetDescription();
		description.fill(parsed);
		information.add(description);

		XMLDataSource datasource = new XMLDataSource();
		datasource.setSourceTextOnly(exp.getFileName(),exp.getFileText());
		information.add(datasource);

		if(parsed.getCommonProperties().size() > 0) {
			ReSpecThCommonPropertiesDisplay common = new ReSpecThCommonPropertiesDisplay();
			common.fill(parsed.getCommonProperties());
			information.add(common);
		}
		ReSpecThDataPointPropertiesDisplay datapoint = new ReSpecThDataPointPropertiesDisplay();
		datapoint.fill(parsed.getDataGroupProperties());
		information.add(datapoint);
		pointsdisplay = new ReSpecThDataMatrixDisplay(parsed);
		information.add(pointsdisplay);
	}

	public ReSpecThDataMatrixDisplay getMatrixDisplay() {
		return pointsdisplay;
	}
	
	public void setText(String text) {
		experiment.setText(text);
	}

	public String getText() {
		return experiment.getText();
	}

}
