package info.esblurock.reaction.server.compute;

import java.io.IOException;
import java.util.ArrayList;

import info.esblurock.reaction.client.async.ComputeChemicalQuantitiesService;
import info.esblurock.reaction.data.MatrixOfDoubles;
import info.esblurock.reaction.data.UserDTO;
import info.esblurock.reaction.data.chemical.thermo.NASAPolynomialData;
import info.esblurock.reaction.data.chemical.thermo.ThermodynamicValues;
import info.esblurock.reaction.server.ServerBase;
import info.esblurock.reaction.server.utilities.ContextAndSessionUtilities;
import info.esblurock.reaction.server.utilities.ManageDataSourceIdentification;
import thermo.data.benson.NASAPolynomial;
import thermo.exception.ThermodynamicComputeException;

@SuppressWarnings("serial")
public class ComputeChemicalQuantitiesServiceImpl extends ServerBase implements ComputeChemicalQuantitiesService {

	public ThermodynamicValues computeThermodynamicQuantity(NASAPolynomialData nasa) throws IOException {
		ThermodynamicValues calculate = TransferNASAPolynomial.calculate(nasa);
		return calculate;
	}

	public MatrixOfDoubles computeEnthalpy(ArrayList<NASAPolynomialData> nasaset, ArrayList<Double> temperatures) throws IOException {
		MatrixOfDoubles matrix = new MatrixOfDoubles(getUser(), getFileCode(), 
				rowTitle(nasaset),columnTitle(temperatures));
		
		int row = 0;
		for(NASAPolynomialData nasa : nasaset) {
			ArrayList<Double> enthalpies = computeEnthalpy(nasa,temperatures);
			matrix.insertRow(row, enthalpies);
			row++;
		}
		return matrix;
	}
	public ArrayList<Double> computeEnthalpy(NASAPolynomialData nasa, ArrayList<Double> temperatures) throws IOException {
		ArrayList<Double> values = new ArrayList<Double>();
		try {
			NASAPolynomial thergasnasa = TransferNASAPolynomial.transferNASA(nasa);
			for(Double temperature : temperatures) {
			double enthalpy = thergasnasa.computeEnthalpy(temperature.doubleValue());
			Double enthalpyD = new Double(enthalpy);
			values.add(enthalpyD);
			}
		} catch (ThermodynamicComputeException e) {
			throw new IOException("Error in calculating enthalpy");
		}
		return values;
	}
	public Double computeEnthalpy(NASAPolynomialData nasa, double temperature) throws IOException {
		Double value;
		try {
		NASAPolynomial thergasnasa = TransferNASAPolynomial.transferNASA(nasa);
		double enthalpy = thergasnasa.computeEnthalpy(temperature);
		value = new Double(enthalpy);
		} catch (ThermodynamicComputeException e) {
			throw new IOException("Error in calculating enthalpy");
		}
		return value;
	}
	
	public MatrixOfDoubles computeEntropy(ArrayList<NASAPolynomialData> nasaset, ArrayList<Double> temperatures) throws IOException {
		MatrixOfDoubles matrix = new MatrixOfDoubles(getUser(), getFileCode(), rowTitle(nasaset),columnTitle(temperatures));
		
		int row = 0;
		for(NASAPolynomialData nasa : nasaset) {
			ArrayList<Double> entropies = computeEntropy(nasa,temperatures);
			matrix.insertRow(row, entropies);
			row++;
		}
		return matrix;
	}
	public ArrayList<Double> computeEntropy(NASAPolynomialData nasa, ArrayList<Double> temperatures) throws IOException {
		ArrayList<Double> values = new ArrayList<Double>();
		try {
			NASAPolynomial thergasnasa = TransferNASAPolynomial.transferNASA(nasa);
			for(Double temperature : temperatures) {
			double entropy = thergasnasa.computeEntropy(temperature.doubleValue());
			Double entropyD = new Double(entropy);
			values.add(entropyD);
			}
		} catch (ThermodynamicComputeException e) {
			throw new IOException("Error in calculating enthalpy");
		}
		return values;
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


	public MatrixOfDoubles computeHeatCapacity(ArrayList<NASAPolynomialData> nasaset, 
			ArrayList<Double> temperatures) throws IOException {
		MatrixOfDoubles matrix = new MatrixOfDoubles(getUser(), getFileCode(), 
				rowTitle(nasaset),columnTitle(temperatures));
		int row = 0;
		for(NASAPolynomialData nasa : nasaset) {
			ArrayList<Double> cps = computeHeatCapacity(nasa,temperatures);
			matrix.insertRow(row, cps);
			row++;
		}
		return matrix;
	}

	public ArrayList<Double> computeHeatCapacity(NASAPolynomialData nasa, ArrayList<Double> temperatures) throws IOException {
		ArrayList<Double> values = new ArrayList<Double>();
		try {
			NASAPolynomial thergasnasa = TransferNASAPolynomial.transferNASA(nasa);
			for(Double temperature : temperatures) {
			double cp = thergasnasa.getHeatCapacity(temperature.doubleValue());
			Double cpD = new Double(cp);
			values.add(cpD);
			}
		} catch (ThermodynamicComputeException e) {
			throw new IOException("Error in calculating enthalpy");
		}
		return values;
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

	private ArrayList<String> columnTitle(ArrayList<Double> temperatures) {
		ArrayList<String> columnTitle = new ArrayList<String>();
		for(Double value: temperatures) {
			columnTitle.add(value.toString());
		}
		return columnTitle;
	}
	private ArrayList<String> rowTitle(ArrayList<NASAPolynomialData> nasaset) {
		ArrayList<String> rowTitle = new ArrayList<String>();
		for(NASAPolynomialData nasa: nasaset) {
			rowTitle.add(nasa.getKeyword());
		}
		return rowTitle;
	}
	
	private String getUser() {
		ContextAndSessionUtilities util = getUtilities();
		UserDTO user = util.getUserInfo();
		String username = util.getUserName();
		return username;
	}
	private String getFileCode() {
		String fileCode = ManageDataSourceIdentification.getDataSourceIdentification(getUser());
		return fileCode;
	}
}
