package info.esblurock.reaction.data.chemical.thermo;

import java.io.Serializable;
import java.util.ArrayList;

public class ThermodynamicValues implements Serializable {
	private static final long serialVersionUID = 1L;
	double minT = 300.0;
	double incT = 100.0;
	double maxT = 2000.0;
	
	ArrayList<Double> temperatures;
	ArrayList<Double> enthalpyValues;
	ArrayList<Double> entropyValues;
	ArrayList<Double> cpValues;
	
	public ThermodynamicValues(ArrayList<Double> temperatures) {
		this.temperatures = temperatures;
		enthalpyValues = new ArrayList<Double>();
		entropyValues = new ArrayList<Double>();
		cpValues = new ArrayList<Double>();
	}
	public ThermodynamicValues()  {
		this.temperatures = new ArrayList<Double>();
		for(double T = minT; T <= maxT; T +=incT) {
			Double temp = new Double(T);
			this.temperatures.add(temp);
		}
		this.enthalpyValues = new ArrayList<Double>();
		this.entropyValues = new ArrayList<Double>();
		this.cpValues = new ArrayList<Double>();
	}
	public void addEntropy(Double entropy) {
		entropyValues.add(entropy);
	}
	public void addEnthalpy(Double enthalpy) {
		enthalpyValues.add(enthalpy);
	}
	public void addCp(Double cp) {
		cpValues.add(cp);
	}
	
	
	public ArrayList<Double> getTemperatures() {
		return temperatures;
	}
	public ArrayList<Double> getEnthalpyValues() {
		return enthalpyValues;
	}
	public ArrayList<Double> getEntropyValues() {
		return entropyValues;
	}
	public ArrayList<Double> getCpValues() {
		return cpValues;
	}
	
}
