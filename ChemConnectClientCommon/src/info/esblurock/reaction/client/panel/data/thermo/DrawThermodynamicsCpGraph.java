package info.esblurock.reaction.client.panel.data.thermo;

import java.util.ArrayList;

import info.esblurock.reaction.data.chemical.thermo.ThermodynamicValues;

public class DrawThermodynamicsCpGraph extends DrawThermodynamicsGraph {
	String cpS = "Cp";
	String titleS = "Heat Capacity";

	public DrawThermodynamicsCpGraph(NASAThermoPanel panel) {
		super(panel);
		this.setTitle(titleS);
		panel.addCpGraph(this);
	}

	public void fillThermodynamics(ThermodynamicValues values) {
		super.fillThermodynamics(values);
		this.xaxisTitle = cpS;
		this.title = titleS;
		this.sets = new String[1];
		this.sets[0] = "Cp";
		ArrayList<Double> cp = values.getCpValues();
		this.fillThermodynamics(cp);
	}


}
