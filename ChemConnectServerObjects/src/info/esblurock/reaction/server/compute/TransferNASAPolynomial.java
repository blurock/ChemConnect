package info.esblurock.reaction.server.compute;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import info.esblurock.reaction.data.chemical.molecule.isomer.IsomerData;
import info.esblurock.reaction.data.chemical.thermo.NASAPolynomialData;
import info.esblurock.reaction.data.chemical.thermo.ThermodynamicValues;
import thermo.data.benson.NASAPolynomial;
import thermo.exception.ThermodynamicComputeException;

public class TransferNASAPolynomial {

	static public NASAPolynomial transferNASA(NASAPolynomialData nasa) {
		NASAPolynomial thergasnasa = new NASAPolynomial();
		thergasnasa.upperT = nasa.getUpperT().doubleValue();
		thergasnasa.lowerT = nasa.getLowerT().doubleValue();
		thergasnasa.middleT = nasa.getMiddleT().doubleValue();
		thergasnasa.phase = nasa.getPhase();
		thergasnasa.name = nasa.getMoleculeName();
		
		IsomerData isomer = nasa.getMoleculeComposition();
		Map<String,Integer> atomcount = isomer.getAtomCounts();
		Set<String> names = atomcount.keySet();
		
		int i = 0;
		for(String name : names) {
			Integer count = atomcount.get(name);
			thergasnasa.atomcnt[i] = count.intValue();
			thergasnasa.atoms[i] = name;
		}
		
		int count = 0;
		for(Double l : nasa.getLower()) {
			thergasnasa.lower[count] = l.doubleValue();
			count++;
		}
		count = 0;
		for(Double u : nasa.getUpper()) {
			thergasnasa.upper[count] = u.doubleValue();
			count++;
		}
		
		return thergasnasa;
	}
	
	static public ThermodynamicValues calculate(NASAPolynomialData nasa) throws IOException {
		ThermodynamicValues values = new ThermodynamicValues();
		calculate(nasa,values);
		return values;
	}
	static public ThermodynamicValues calculate(NASAPolynomialData nasa, ArrayList<Double> temp) throws IOException {
		ThermodynamicValues values = new ThermodynamicValues(temp);
		calculate(nasa, values);
		return values;
	}
	static void calculate(NASAPolynomialData nasa, ThermodynamicValues values) throws IOException {
		try {
		NASAPolynomial thergasnasa = TransferNASAPolynomial.transferNASA(nasa);
		for(Double temperature : values.getTemperatures()) {
			double enthalpy = thergasnasa.computeEnthalpy(temperature.doubleValue());
			double entropy = thergasnasa.computeEntropy(temperature.doubleValue());
			double cp = thergasnasa.getHeatCapacity(temperature.doubleValue());
			Double ethalpyD = new Double(enthalpy);
			Double entropyD = new Double(entropy);
			Double cpD = new Double(cp);
			
			values.addEnthalpy(ethalpyD);
			values.addEntropy(entropyD);
			values.addCp(cpD);
		}
		} catch (ThermodynamicComputeException e) {
			throw new IOException("Error calculating thermodynamics");
		}
	}

}
