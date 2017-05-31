package info.esblurock.reaction.experiment.client.project;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ApparatusListCallback implements AsyncCallback<ArrayList<String>> {

	AskForItemName ask;
	
	ApparatusListCallback(AskForItemName ask) {
		this.ask = ask;
	}
	
	@Override
	public void onFailure(Throwable caught) {
		Window.alert(caught.toString());
	}

	@Override
	public void onSuccess(ArrayList<String> result) {
		for(String element : result) {
			ask.addDropDownElement(element);
		}
	}

}
