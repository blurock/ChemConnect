package info.esblurock.reaction.experiment.client.project.items;


import com.google.gwt.user.client.Window;

import info.esblurock.reaction.experiment.client.project.AskForItemName;
import info.esblurock.reaction.experiment.client.project.ResultsForAskForName;
import info.esblurock.reaction.experiment.client.ui.ExperimentalDataImpl;

public class AskApparatusName extends ResultsForAskForName {
	
	String source;	
	
	public AskApparatusName(ExperimentalDataImpl experimentalData, ChooseExperimentalCollapsibleItem item) {
		super(experimentalData,item);
		source = null;
	}
	
	public AskApparatusName(String source, ExperimentalDataImpl experimentalData, ChooseExperimentalCollapsibleItem item) {
		super(experimentalData,item);
	}

	@Override
	public void initialize(AskForItemName askforname) {
		super.initialize(askforname);
		if(source != null) {
			askforname.setSource(source);
			askforname.freezeSource();
		} else {
		}
	}

	@Override
	public boolean setInResults() {
		boolean ans = askforname.fullNameGiven();
		if(ans) {
			String fullname = askforname.getFullName();
			item.setText(fullname);
		}
		return ans;
	}

}
