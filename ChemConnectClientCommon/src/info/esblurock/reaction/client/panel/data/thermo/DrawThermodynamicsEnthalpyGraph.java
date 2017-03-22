package info.esblurock.reaction.client.panel.data.thermo;

import java.util.ArrayList;

import info.esblurock.reaction.data.chemical.thermo.ThermodynamicValues;

public class DrawThermodynamicsEnthalpyGraph extends DrawThermodynamicsGraph {

	String enthalpyS = "Enthalpy";
	String titleS = "Enthalpy";
	public DrawThermodynamicsEnthalpyGraph(NASAThermoPanel panel) {
		super(panel);
		this.setTitle(titleS);
		panel.addEnthaplyGraph(this);
	}
	
	public void fillThermodynamics(ThermodynamicValues values) {
		super.fillThermodynamics(values);
		this.xaxisTitle = enthalpyS;
		this.title = titleS;
		this.sets = new String[1];
		this.sets[0] = "Enthalpy";
		ArrayList<Double> enthalpy = values.getEnthalpyValues();
		this.fillThermodynamics(enthalpy);
	}
}
