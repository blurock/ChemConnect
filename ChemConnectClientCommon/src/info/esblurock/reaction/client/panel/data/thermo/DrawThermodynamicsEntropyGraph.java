package info.esblurock.reaction.client.panel.data.thermo;

import java.util.ArrayList;

import info.esblurock.reaction.data.chemical.thermo.ThermodynamicValues;

public class DrawThermodynamicsEntropyGraph extends DrawThermodynamicsGraph {

	String entropyS = "Entropy";
	String titleS = "Entropy";

	public DrawThermodynamicsEntropyGraph(NASAThermoPanel panel) {
		super(panel);
		this.setTitle(titleS);
		panel.addEntropyGraph(this);
	}

	public void fillThermodynamics(ThermodynamicValues values) {
		super.fillThermodynamics(values);
		this.xaxisTitle = entropyS;
		this.title = titleS;
		this.sets = new String[1];
		this.sets[0] = "Entropy";
		ArrayList<Double> enthalpy = values.getEntropyValues();
		this.fillThermodynamics(enthalpy);
	}

}
