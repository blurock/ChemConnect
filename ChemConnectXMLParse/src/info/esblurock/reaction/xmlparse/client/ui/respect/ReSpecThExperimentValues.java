package info.esblurock.reaction.xmlparse.client.ui.respect;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialRow;
import gwt.material.design.client.ui.MaterialTextBox;
import info.esblurock.reaction.data.chemical.respect.ReSpecTHXMLFileBase;
import info.esblurock.reaction.data.chemical.respect.ReSpecThXMLExperiment;
import info.esblurock.reaction.data.chemical.respect.ReSpecThXMLKExperiment;
import info.esblurock.reaction.xmlparse.resources.XMLParseResource;

public class ReSpecThExperimentValues extends Composite implements HasText {

	private static ReSpecThExperimentValuesUiBinder uiBinder = GWT.create(ReSpecThExperimentValuesUiBinder.class);

	interface ReSpecThExperimentValuesUiBinder extends UiBinder<Widget, ReSpecThExperimentValues> {
	}

	XMLParseResource resource = GWT.create(XMLParseResource.class);

	public static String experimentS = "experiment";

	@UiField
	HTMLPanel direct;
	@UiField
	HTMLPanel indirect;
	@UiField
	MaterialTextBox reaction;
	@UiField
	MaterialTextBox bulkgas;
	@UiField
	MaterialTextBox order;
	@UiField
	MaterialTextBox experimenttype;
	@UiField
	MaterialTextBox apparatuskind;
	@UiField
	MaterialTextBox apparatusmode;
	@UiField
	MaterialTextBox target;
	@UiField
	MaterialTextBox type;
	@UiField
	MaterialTextBox amount;
	@UiField
	MaterialTextBox unit;
	@UiField
	MaterialLink description;

	@UiField
	MaterialRow experimenttyperow;
	@UiField
	MaterialRow apparatusrow;
	@UiField
	MaterialRow ignitionrow;
	@UiField
	MaterialRow amountrow;

	String text;
	boolean directExperiment;
	ReSpecTHXMLFileBase respect;

	public ReSpecThExperimentValues(ReSpecTHXMLFileBase respect) {
		initWidget(uiBinder.createAndBindUi(this));
		this.respect = respect;
		if (respect.getXmlExperimentCategory().compareToIgnoreCase(experimentS) == 0) {
			fillInDirect();
		} else {
			fillDirect();
		}
		description.setText(resource.experiment());
	}

	public void fillDirect() {
		directExperiment = true;
		ReSpecThXMLKExperiment experiment = (ReSpecThXMLKExperiment) respect;
		direct.setVisible(true);
		indirect.setVisible(false);
		text = experiment.getReaction();
		reaction.setPlaceholder(resource.reaction());
		reaction.setText(experiment.getReaction());
		order.setPlaceholder(resource.order());
		order.setText(experiment.getBulkgas());
		bulkgas.setPlaceholder(resource.bulkgas());
		bulkgas.setText(experiment.getOrder());
	}

	public void fillInDirect() {
		directExperiment = false;
		ReSpecThXMLExperiment experiment = (ReSpecThXMLExperiment) respect;
		direct.setVisible(false);
		indirect.setVisible(true);
		text = experiment.getExperimentType();

		if (experiment.getExperimentType() != null) {
			experimenttype.setPlaceholder(resource.experimenttype());
			experimenttype.setText(experiment.getExperimentType());
			experimenttyperow.setVisible(true);
		} else {
			experimenttyperow.setVisible(false);
		}

		if (experiment.getKind() == null && experiment.getMode() == null) {
			apparatusrow.setVisible(false);
		} else {
			apparatusrow.setVisible(true);
			if (experiment.getKind() != null) {
				apparatuskind.setPlaceholder(resource.apparatuskind());
				apparatuskind.setText(experiment.getKind());
			} else {
				apparatuskind.setVisible(false);
			}
			if (experiment.getMode() != null) {
				apparatusmode.setPlaceholder(resource.apparatusmode());
				apparatusmode.setText(experiment.getMode());
			} else {
				apparatusmode.setVisible(false);
			}
		}
		if (experiment.getIgnitionTarget() != null || experiment.getIgnitionType() != null) {
			if (experiment.getIgnitionTarget() != null) {
				target.setPlaceholder(resource.target());
				target.setText(experiment.getIgnitionTarget());
			} else {
				target.setVisible(false);
			}
			if (experiment.getIgnitionType() != null) {
				type.setPlaceholder(resource.type());
				type.setText(experiment.getIgnitionType());
			} else {
				type.setVisible(false);
			}

		} else {
			ignitionrow.setVisible(false);
		}
		if (experiment.getIgnitionAmount() != null || experiment.getIgnitionUnits() != null) {
			if (experiment.getIgnitionAmount() != null) {
				amount.setPlaceholder(resource.amount());
				amount.setText(experiment.getIgnitionAmount());
			} else {
				amount.setVisible(false);
			}
			if (experiment.getIgnitionUnits() != null) {
				unit.setPlaceholder(resource.unit());
				unit.setText(experiment.getIgnitionUnits());
			} else {
				unit.setVisible(false);
			}
		} else {
			amountrow.setVisible(false);
		}
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

}
