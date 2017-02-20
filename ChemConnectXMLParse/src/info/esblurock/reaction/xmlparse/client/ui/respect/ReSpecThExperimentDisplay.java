package info.esblurock.reaction.xmlparse.client.ui.respect;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialCollapsible;
import gwt.material.design.client.ui.MaterialLink;
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
	
	public ReSpecThExperimentDisplay(XMLDataSource exp) {
		initWidget(uiBinder.createAndBindUi(this));
		
		Window.alert("ReSpecThExperimentDisplay: ");
		Window.alert("ReSpecThExperimentDisplay: " + exp.getFileName());
		this.exp = exp;
		String filename = exp.getFileName();
		ReSpecTHXMLFileBase parsed = exp.getParsedFile();
		String name = filename;
		experiment.setText(name);
		DataSubSetDescription description = new DataSubSetDescription();
		description.fill(parsed);
		information.add(description);
		if(parsed.getCommonProperties().size() > 0) {
			ReSpecThCommonPropertiesDisplay common = new ReSpecThCommonPropertiesDisplay();
			common.fill(parsed.getCommonProperties());
			information.add(common);
		}
		ReSpecThDataPointPropertiesDisplay datapoint = new ReSpecThDataPointPropertiesDisplay();
		datapoint.fill(parsed.getDataGroupProperties());
		information.add(datapoint);
		Window.alert("Add Data points: " + exp.getFileName());
		ReSpecThDataMatrixDisplay pointsdisplay = new ReSpecThDataMatrixDisplay(parsed);
		information.add(pointsdisplay);
	}

	
	
	public void setText(String text) {
		experiment.setText(text);
	}

	public String getText() {
		return experiment.getText();
	}

}
