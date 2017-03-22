package info.esblurock.reaction.client.async;

import java.io.IOException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import info.esblurock.reaction.data.chemical.thermo.NASAPolynomialData;
import info.esblurock.reaction.data.chemical.thermo.ThermodynamicValues;

@RemoteServiceRelativePath("computechemicalquantities")
public interface ComputeChemicalQuantitiesService extends RemoteService {
	   public static class Util
	   {
	       private static ComputeChemicalQuantitiesServiceAsync instance;

	       public static ComputeChemicalQuantitiesServiceAsync getInstance()
	       {
	           if (instance == null)
	           {
	               instance = GWT.create(ComputeChemicalQuantitiesService.class);
	           }
	           return instance;
	       }
	   }

	   ThermodynamicValues computeThermodynamicQuantity(NASAPolynomialData nasa) throws IOException;
	   Double computeEnthalpy(NASAPolynomialData nasa, double temperature) throws IOException;
	   Double computeEntropy(NASAPolynomialData nasa, double temperature) throws IOException;
	   Double computeHeatCapacity(NASAPolynomialData nasa, double temperature) throws IOException;
}
