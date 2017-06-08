package info.esblurock.reaction.experiment.client.project.items;

import com.google.gwt.user.client.Window;

import gwt.material.design.client.ui.MaterialToast;
import info.esblurock.reaction.experiment.client.project.specification.images.UploadPhoto;
import info.esblurock.reaction.experiment.client.ui.ExperimentalDataImpl;

public class ExperimentalApparatusCollapsibleItem extends ChooseExperimentalCollapsibleItem {

	
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
		ExperimentalAttributeProperties props = new ExperimentalAttributeProperties("Properties", subtype, experimentalData);
		itemsList.add(props);
		UploadPhoto photo = new UploadPhoto();
		itemsList.add(photo);
	}

}
