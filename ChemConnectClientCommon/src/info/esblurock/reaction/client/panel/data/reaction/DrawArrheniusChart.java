package info.esblurock.reaction.client.panel.data.reaction;

import info.esblurock.reaction.data.chemical.reaction.ChemkinCoefficientsData;

public class DrawArrheniusChart extends MaterialAreaChart {

	private double[][] values;
	private String[] sets = new String[] { "Ln k" };
	private double[] xaxis;
	
	double minTemperature = 600.0;
	double maxTemperature = 2000.0;

	public DrawArrheniusChart() {
	}

	void fill(ChemkinCoefficientsData coefficients) {
		int num = (int) (Math.floor(maxTemperature - minTemperature)/100.0 + 1.5);
		xaxis = new double[num];
		int i = 0;
		for(double t= maxTemperature; t>=minTemperature;t-=100.0) {
			xaxis[i++] = 1000.0/t;
		}
		
		double A = Double.parseDouble(coefficients.getA());
		double Ea = Double.parseDouble(coefficients.getEa());
		double R = 1.9872036;
		double EoverR = Ea / R;
		double n = Double.parseDouble(coefficients.getN());

		values = new double[sets.length][xaxis.length];
		//StringBuilder build = new StringBuilder();
		for (int scount = 0; scount < sets.length; scount++) {
			for (int count = 0; count < xaxis.length; count++) {
				double T = 1000.0/(xaxis[count]);
				double k = computeLnK(T, A, EoverR, n);
				//build.append("(" + count + ": "+ xaxis[count] + ", " + k  + ")\n");
				values[scount][count] = k;
			}
			//Window.alert(build.toString());
		}
		String setTitle = "Thermo";
		String xaxisTitle = "1000.0/T";
		String yaxisTitle = "ln k";
		setTitle("Arrhenius Plot");
		fillNumber(setTitle, xaxisTitle, yaxisTitle, xaxis, sets, values);
	}

	private double computeLnK(double T, double A, double EoverR, double n) {
		double logA = Math.log10(A);
		double logT = Math.log10(T);
		double lnk = logA + EoverR / T + n * logT;
		return lnk;
	}
	/*
	private double computeK(double T, double A, double EoverR, double n) {
		double k = A * Math.pow(T, n)*Math.exp(-EoverR/T);
		return k;
	}
	*/
}
