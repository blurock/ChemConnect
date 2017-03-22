package info.esblurock.reaction.client.panel.data.thermo;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import info.esblurock.reaction.data.chemical.thermo.ThermodynamicValues;

public class CalculateNASAThermoGraphsCallback implements AsyncCallback<ThermodynamicValues> {

	NASAThermoPanel panel;
	
	public CalculateNASAThermoGraphsCallback(NASAThermoPanel panel) {
		this.panel = panel;
	}
	@Override
	public void onFailure(Throwable caught) {
		Window.alert("Error in calculating Thermodynamics for graphs:\n" + caught.toString());
	}

	@Override
	public void onSuccess(ThermodynamicValues result) {
		panel.addThermodynamicGraphs(result);
	}

}
