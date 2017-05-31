package info.esblurock.reaction.experiment.client.project.items;

import com.google.gwt.user.client.ui.HTMLPanel;

import info.esblurock.reaction.experiment.client.project.AskForItemName;
import info.esblurock.reaction.experiment.client.ui.ExperimentalDataImpl;

public class ExperimentalApparatusTemplatesCollapsibleItem extends ChooseExperimentalCollapsibleItem {
	static public String apparatusS = "Apparatus";
	
	public ExperimentalApparatusTemplatesCollapsibleItem(ExperimentalDataImpl experimentalData) {
		super("Apparatus",experimentalData);
	}


	protected void addItem() {
		HTMLPanel modalpanel = experimentalData.getModalPanel();
		ExperimentalApparatusCollapsibleItem apparatus = new ExperimentalApparatusCollapsibleItem(experimentalData);
		itemsList.add(apparatus);
		
		AskApparatusName ask = new AskApparatusName(experimentalData,apparatus);
		AskForItemName askforname = new AskForItemName(apparatusS, ask);
		ask.initialize(askforname);
		modalpanel.clear();
		modalpanel.add(askforname);
		askforname.openModal();
	}
	
	public void closeModal() {
		HTMLPanel modalpanel = experimentalData.getModalPanel();
		modalpanel.clear();
	}

}
