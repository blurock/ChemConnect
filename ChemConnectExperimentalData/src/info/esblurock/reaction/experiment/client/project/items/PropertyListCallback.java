package info.esblurock.reaction.experiment.client.project.items;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class PropertyListCallback implements AsyncCallback<ArrayList<String>> {

	ExperimentalAttributeProperties top;
	
	public PropertyListCallback(ExperimentalAttributeProperties top) {
		this.top = top;
	}
	@Override
	public void onFailure(Throwable caught) {
		Window.alert(caught.toString());
	}

	@Override
	public void onSuccess(ArrayList<String> result) {
		for(String element : result) {
			top.addAttributeValuePair(element);
		}
	}
	
}
