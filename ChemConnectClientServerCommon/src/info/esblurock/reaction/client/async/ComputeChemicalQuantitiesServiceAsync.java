package info.esblurock.reaction.client.async;

import com.google.gwt.user.client.rpc.AsyncCallback;

import info.esblurock.reaction.data.chemical.thermo.NASAPolynomialData;
import info.esblurock.reaction.data.chemical.thermo.ThermodynamicValues;

public interface ComputeChemicalQuantitiesServiceAsync {


	void computeEntropy(NASAPolynomialData nasa, double temperature, AsyncCallback<Double> callback);

	void computeHeatCapacity(NASAPolynomialData nasa, double temperature, AsyncCallback<Double> callback);

	void computeEnthalpy(NASAPolynomialData nasa, double temperature, AsyncCallback<Double> callback);

	void computeThermodynamicQuantity(NASAPolynomialData nasa, AsyncCallback<ThermodynamicValues> callback);

}
