package info.esblurock.reaction.experiment.client.project;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialCollapsible;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialToast;
import info.esblurock.reaction.client.client.info.InformationPageModal;
import info.esblurock.reaction.client.panel.description.DataDescription;
import info.esblurock.reaction.client.panel.description.SetOfReferenceDescriptions;
import info.esblurock.reaction.data.description.DescriptionDataData;
import info.esblurock.reaction.experiment.client.ui.ExperimentalDataImpl;
import info.esblurock.reaction.experiment.client.ui.resources.ChemConnectExperimentKeys;

public class ExperimentalProjectTop extends Composite implements HasText {

	private static ExperimentalProjectTopUiBinder uiBinder = GWT.create(ExperimentalProjectTopUiBinder.class);

	interface ExperimentalProjectTopUiBinder extends UiBinder<Widget, ExperimentalProjectTop> {
	}

	ChemConnectExperimentKeys resoursekeys = GWT.create(ChemConnectExperimentKeys.class);
	
	@UiField
	MaterialLink definitionTop;
	@UiField
	MaterialLink projectTop;
	@UiField
	MaterialLink projectInfo;

	@UiField
	MaterialLink addExperimentElement;
	@UiField
	MaterialLink deleteProject;
	@UiField
	MaterialLink definitionInfo;
	@UiField
	MaterialCollapsible standardItems;
	@UiField
	MaterialLink experimentTop;
	@UiField
	MaterialIcon experimentInfo;
	@UiField
	MaterialCollapsible addedItems;
	
	ExperimentalDataImpl experimentalData;
	
	public ExperimentalProjectTop(ExperimentalDataImpl experimentalData) {
		initWidget(uiBinder.createAndBindUi(this));
		this.experimentalData = experimentalData;
		init("New Project");
	}
	
	public ExperimentalProjectTop(ExperimentalDataImpl experimentalData, String projectName) {
		initWidget(uiBinder.createAndBindUi(this));
		this.experimentalData = experimentalData;		
	}
	void init(String projectName) {
		projectTop.setText(projectName);
		DataDescription description = new DataDescription();
		SetOfReferenceDescriptions references = new SetOfReferenceDescriptions();
		standardItems.add(description);
		standardItems.add(references);
		definitionTop.setText(resoursekeys.projecttop());
		experimentTop.setText(resoursekeys.projectItems());
		projectTop.setText(resoursekeys.projecttopdescription());
	}

	@UiHandler("addExperimentElement")
	public void addElement(ClickEvent event) {
	}

	@UiHandler("deleteProject")
	public void deleteProject(ClickEvent event) {
	}

	@UiHandler("definitionInfo")
	public void infoProject(ClickEvent event) {
		String title = "Defining Project Elements";
		String description = "This is a short summary of defining project elements";
		String info = "Summary";
		experimentalData.startInformationModal(title, description, info);
	}

	
	public void setText(String text) {
		projectTop.setText(text);
	}

	public String getText() {
		return projectTop.getText();
	}

}
