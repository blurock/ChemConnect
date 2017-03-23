package info.esblurock.reaction.client.panel.data.thermo;

import java.io.IOException;
import java.util.ArrayList;

import info.esblurock.reaction.client.async.ComputeChemicalQuantitiesService;
import info.esblurock.reaction.client.async.ComputeChemicalQuantitiesServiceAsync;
import info.esblurock.reaction.data.chemical.thermo.NASAPolynomialData;
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
	protected void calculate(String values) throws IOException {
		super.calculate(values);
		ComputeChemicalQuantitiesServiceAsync async = ComputeChemicalQuantitiesService.Util.getInstance();
		DrawChartEnthalpyCallback callback = new DrawChartEnthalpyCallback(this);
		ArrayList<NASAPolynomialData> set = new ArrayList<NASAPolynomialData>();
		set.add(getNasa());
		async.computeEnthalpy(set,valuesD,callback);
	}

}
