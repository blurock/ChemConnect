package info.esblurock.reaction.client.panel.inputs;

import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;


public class SetUpProcessesCallback  implements AsyncCallback<List<String>> {

	String keyword;
	ValidProcesses modal;
	
	public SetUpProcessesCallback(String keyword, ValidProcesses modal) {
		this.keyword = keyword;
		this.modal = modal;
	}
	@Override
	public void onFailure(Throwable caught) {
		Window.alert(caught.toString());
	}

	@Override
	public void onSuccess(List<String> result) {
		modal.showValidProcesses(keyword, result);
	}
}
