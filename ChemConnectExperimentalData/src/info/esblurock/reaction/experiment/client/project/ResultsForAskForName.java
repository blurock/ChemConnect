package info.esblurock.reaction.experiment.client.project;

import info.esblurock.reaction.experiment.client.project.items.ChooseExperimentalCollapsibleItem;
import info.esblurock.reaction.experiment.client.ui.ExperimentalDataImpl;

public abstract class ResultsForAskForName {

	protected AskForItemName askforname;
	protected ExperimentalDataImpl experimentalData;
	protected ChooseExperimentalCollapsibleItem item;
	
	public ResultsForAskForName(ExperimentalDataImpl experimentalData, ChooseExperimentalCollapsibleItem item) {
		super();
		this.experimentalData = experimentalData;
		this.item = item;
	}
	
	public void initialize(AskForItemName askforname) {
		this.askforname = askforname;
	};
	public abstract boolean setInResults();

	public void closeModel() {
		experimentalData.getApparatusTemplates().closeModal();
	}
}
