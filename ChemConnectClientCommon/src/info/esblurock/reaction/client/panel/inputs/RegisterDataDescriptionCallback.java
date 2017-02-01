package info.esblurock.reaction.client.panel.inputs;

import com.google.gwt.user.client.rpc.AsyncCallback;


public class RegisterDataDescriptionCallback  implements AsyncCallback<String> {
	SetOfInputs inputs;
	
	public RegisterDataDescriptionCallback(String dataType, 
			SetOfInputs inputs) {
		this.inputs = inputs;
	}
	
	@Override
	public void onFailure(Throwable caught) {
		inputs.findValidProcesses();
	}

	@Override
	public void onSuccess(String result) {
		inputs.askRegisterModal(result);
	}

}
