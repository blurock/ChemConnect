package info.esblurock.reaction.client.panel.data.thermo;

import java.util.ArrayList;

import com.google.gwt.uibinder.client.UiField;

import info.esblurock.reaction.client.panel.data.DrawMatrixOfDoubles;
import info.esblurock.reaction.client.panel.data.reaction.MaterialAreaChart;
import info.esblurock.reaction.data.MatrixOfDoubles;
import info.esblurock.reaction.data.chemical.thermo.NASAPolynomialData;
import info.esblurock.reaction.data.chemical.thermo.ThermodynamicValues;

public class DrawThermodynamicsGraph  extends MaterialAreaChart {

	protected NASAThermoPanel panel;
	protected ArrayList<Double> temperatures;
	protected String xaxisTitle;
	protected String yaxisTitle;
	protected String title;
	protected double[] xaxis;
	protected String[] sets;
	double[][] values;
	
	public DrawThermodynamicsGraph(NASAThermoPanel panel) {
		super();
		this.panel = panel;
	}
	public void fillThermodynamics(ThermodynamicValues values) {
		temperatures = values.getTemperatures();
		this.xaxisTitle = "Temperature";
		this.xaxis = new double[temperatures.size()];
		int count = 0;
		for(Double temp : temperatures) {
			xaxis[count++] = temp.doubleValue(); 
		}
	}
	public void fillThermodynamics(ArrayList<Double> doubles) {
		this.values = new double[1][doubles.size()];
		int count = 0;
		for(Double temp : doubles) {
			this.values[0][count++] = temp.doubleValue(); 
		}
		fillNumber(title,xaxisTitle,yaxisTitle, xaxis,sets,values);
	}
	
	public void drawMatrix(String title, MatrixOfDoubles matrix) {
		calculatetitle.setText(title);
		DrawMatrixOfDoubles panel = new DrawMatrixOfDoubles(matrix);
		panel.setText(matrix.getColumnTitle().toString());
		answer.add(panel);
	}
	public NASAPolynomialData getNasa() {
		return panel.getNasa();
	}

}
