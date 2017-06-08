package info.esblurock.reaction.experiment.client.project.items;

import com.google.gwt.core.client.GWT;

import info.esblurock.reaction.client.async.StoreDescriptionData;
import info.esblurock.reaction.client.async.StoreDescriptionDataAsync;
import info.esblurock.reaction.experiment.client.project.specification.PropertyValueSpecification;
import info.esblurock.reaction.experiment.client.ui.ExperimentalDataImpl;

public class ExperimentalAttributeProperties extends ChooseExperimentalCollapsibleItem {

	public ExperimentalAttributeProperties(String title, String subject, ExperimentalDataImpl experimentalData) {
		super(title,experimentalData);
		init(subject);
	}

	private void init(String subject) {
		StoreDescriptionDataAsync async = GWT.create(StoreDescriptionData.class);
		PropertyListCallback callback = new PropertyListCallback(this);
		async.getSubjectPropertyList(subject, callback);
	}
	public void addAttributeValuePair(String pair) {
		PropertyValueSpecification spec = new PropertyValueSpecification(pair);
		itemsList.add(spec);
	}


}
