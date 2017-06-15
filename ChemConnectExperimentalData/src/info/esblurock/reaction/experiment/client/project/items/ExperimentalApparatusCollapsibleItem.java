package info.esblurock.reaction.experiment.client.project.items;

import com.google.gwt.core.client.GWT;

import gwt.material.design.client.ui.MaterialToast;
import info.esblurock.reaction.client.panel.description.DataDescription;
import info.esblurock.reaction.client.panel.description.SetOfObjectLinks;
import info.esblurock.reaction.client.panel.description.SetOfReferenceDescriptions;
import info.esblurock.reaction.client.resources.DescriptionConstants;
import info.esblurock.reaction.experiment.client.project.specification.images.UploadPhoto;
import info.esblurock.reaction.experiment.client.ui.ExperimentalDataImpl;

public class ExperimentalApparatusCollapsibleItem extends ChooseExperimentalCollapsibleItem {

	DescriptionConstants descriptionConstants = GWT.create(DescriptionConstants.class);
	
	public ExperimentalApparatusCollapsibleItem(ExperimentalDataImpl experimentalData) {
		super(experimentalData);
		init();
	}

	public ExperimentalApparatusCollapsibleItem(String title, ExperimentalDataImpl experimentalData) {
		super(title,experimentalData);
		init();
	}

	private void init() {
	}
	@Override
	protected void addItem() {
		MaterialToast.fireToast("Add item: " + listTitle);
		
	}
	public void setSubType(String subtype) {
		super.setSubType(subtype);
		DataDescription description = new DataDescription(listTitle.getText());
		description.setTextAsObjectDescription(listTitle.getText(),descriptionConstants.keywordtext());
		itemsList.add(description);
		SetOfReferenceDescriptions references = new SetOfReferenceDescriptions(listTitle.getText());
		itemsList.add(references);
		SetOfObjectLinks links = new SetOfObjectLinks(listTitle.getText());
		itemsList.add(links);
		ExperimentalAttributeProperties props = new ExperimentalAttributeProperties("Properties", subtype, experimentalData);
		itemsList.add(props);
		UploadPhoto photo = new UploadPhoto(listTitle.getText());
		itemsList.add(photo);
	}

}
