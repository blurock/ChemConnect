package info.esblurock.reaction.client.panel.description;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import info.esblurock.reaction.data.description.ObjectLinkDescription;

public class FillInObjectLinksCallback implements AsyncCallback<ArrayList<ObjectLinkDescription>>{

	SetOfObjectLinks links; 
	
	public FillInObjectLinksCallback(SetOfObjectLinks links) {
		this.links = links;
	}
	@Override
	public void onFailure(Throwable caught) {
		Window.alert(caught.toString());
	}

	@Override
	public void onSuccess(ArrayList<ObjectLinkDescription> result) {
		for(ObjectLinkDescription link : result) {
			ObjectLinkInformation linkitem = new ObjectLinkInformation(link);
			links.addlink(linkitem);
		}
	}

}
