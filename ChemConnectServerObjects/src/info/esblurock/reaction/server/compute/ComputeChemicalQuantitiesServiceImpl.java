package info.esblurock.reaction.server.compute;

import java.io.IOException;

import info.esblurock.reaction.client.async.ComputeChemicalQuantitiesService;
import info.esblurock.reaction.data.chemical.thermo.NASAPolynomialData;
import info.esblurock.reaction.data.chemical.thermo.ThermodynamicValues;
import info.esblurock.reaction.server.ServerBase;
import thermo.data.benson.NASAPolynomial;
import thermo.exception.ThermodynamicComputeException;

@SuppressWarnings("serial")
public class ComputeChemicalQuantitiesServiceImpl extends ServerBase implements ComputeChemicalQuantitiesService {

	public ThermodynamicValues computeThermodynamicQuantity(NASAPolynomialData nasa) throws IOException {
		ThermodynamicValues calculate = TransferNASAPolynomial.calculate(nasa);
		return calculate;
	}

	public Double computeEnthalpy(NASAPolynomialData nasa, double temperature) throws IOException {
		Double enthalpyD;
		try {
			NASAPolynomial thergasnasa = TransferNASAPolynomial.transferNASA(nasa);
			double enthalpy = thergasnasa.computeEnthalpy(temperature);
			enthalpyD = new Double(enthalpy);
		} catch (ThermodynamicComputeException e) {
			throw new IOException("Error in calculating enthalpy");
		}
		return enthalpyD;
	}

	public Double computeEntropy(NASAPolynomialData nasa, double temperature) throws IOException {
		Double entropyD;
		try {
			NASAPolynomial thergasnasa = TransferNASAPolynomial.transferNASA(nasa);
			double enthalpy = thergasnasa.computeEntropy(temperature);
			entropyD = new Double(enthalpy);
		} catch (ThermodynamicComputeException e) {
			throw new IOException("Error in calculating entropy");
		}
		return entropyD;
	}

	public Double computeHeatCapacity(NASAPolynomialData nasa, double temperature) throws IOException {
		Double cpD;
		try {
			NASAPolynomial thergasnasa = TransferNASAPolynomial.transferNASA(nasa);
			double enthalpy = thergasnasa.getHeatCapacity(temperature);
			cpD = new Double(enthalpy);
		} catch (ThermodynamicComputeException e) {
			throw new IOException("Error in calculating heat capacity");
		}
		return cpD;
	}

}
