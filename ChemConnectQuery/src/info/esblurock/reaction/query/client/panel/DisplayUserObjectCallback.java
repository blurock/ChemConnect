package info.esblurock.reaction.query.client.panel;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialToast;
import info.esblurock.reaction.client.panel.data.BaseDataPresentation;
import info.esblurock.reaction.client.panel.data.DataPresentation;
import info.esblurock.reaction.data.DatabaseObject;
import info.esblurock.reaction.data.store.UserObjectStorage;

public class DisplayUserObjectCallback  implements AsyncCallback<DatabaseObject> {

	QueryAndResultPanel toppanel;
	
	public DisplayUserObjectCallback(QueryAndResultPanel toppanel, String setname) {
		this.toppanel = toppanel;
	}
	@Override
	public void onFailure(Throwable caught) {
		Window.alert(caught.toString());
	}

	@Override
	public void onSuccess(DatabaseObject result) {
		String name = result.getClass().getSimpleName();
		DataPresentation present = DataPresentation.valueOf(name);
		String title = present.asOnLine(result);
		BaseDataPresentation display = present.asDisplayObject(result);
		Widget widget = display.getModalContent().asWidget();
		toppanel.addDisplayItem(title,widget);
	}

}
