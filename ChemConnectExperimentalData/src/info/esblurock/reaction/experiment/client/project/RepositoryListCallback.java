package info.esblurock.reaction.experiment.client.project;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class RepositoryListCallback implements AsyncCallback<ArrayList<String>> {

	AskForItemName ask;
	public RepositoryListCallback(AskForItemName ask) {
		this.ask = ask;
	}
	
	@Override
	public void onFailure(Throwable caught) {
		Window.alert(caught.toString());
	}

	@Override
	public void onSuccess(ArrayList<String> result) {
		for(String element : result) {
			ask.addDropDownRepositoryElement(element);
		}
	}

}
