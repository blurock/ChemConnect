package info.esblurock.reaction.client.async;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import info.esblurock.reaction.data.MatrixOfDoubles;
import info.esblurock.reaction.data.chemical.thermo.NASAPolynomialData;
import info.esblurock.reaction.data.chemical.thermo.ThermodynamicValues;

public interface ComputeChemicalQuantitiesServiceAsync {


	void computeThermodynamicQuantity(NASAPolynomialData nasa, AsyncCallback<ThermodynamicValues> callback);


	void computeEnthalpy(NASAPolynomialData nasa, double temperature, 
			AsyncCallback<Double> callback);
	void computeEnthalpy(ArrayList<NASAPolynomialData> nasaset, ArrayList<Double> temperatures,
			AsyncCallback<MatrixOfDoubles> callback);
	void computeEnthalpy(NASAPolynomialData nasa, ArrayList<Double> temperatures,
			AsyncCallback<ArrayList<Double>> callback);

	void computeEntropy(NASAPolynomialData nasa, double temperature, AsyncCallback<Double> callback);
	void computeEntropy(ArrayList<NASAPolynomialData> nasaset, ArrayList<Double> temperatures,
			AsyncCallback<MatrixOfDoubles> callback);
	void computeEntropy(NASAPolynomialData nasa, ArrayList<Double> temperatures,
			AsyncCallback<ArrayList<Double>> callback);

	void computeHeatCapacity(ArrayList<NASAPolynomialData> nasaset, ArrayList<Double> temperatures,
			AsyncCallback<MatrixOfDoubles> callback);
	void computeHeatCapacity(NASAPolynomialData nasa, double temperature, AsyncCallback<Double> callback);
	void computeHeatCapacity(NASAPolynomialData nasa, ArrayList<Double> temperatures,
			AsyncCallback<ArrayList<Double>> callback);


}
