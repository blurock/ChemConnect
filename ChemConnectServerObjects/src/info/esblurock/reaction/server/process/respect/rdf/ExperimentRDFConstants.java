package info.esblurock.reaction.server.process.respect.rdf;

import java.util.ArrayList;

public class ExperimentRDFConstants {
	public static String reactionPredicateS = "ExperimentReaction";
	public static String reactantsPredicateS = "ExperimentProducts";
	public static String productsPredicateS = "ExperimentProducts";
	public static String reactantPredicateS = "ExperimentReactant";
	public static String productPredicateS = "ExperimentProduct";
	public static String bulkGasPredicateS = "ExperimentBulkGas";
	public static String productsKeyS = "products";
	public static String reactionOrderPredicateS = "ExperimentOrder";
	public static String experimentTypePredicateS = "ExperimentType";
	
	public static String ignitionDelayS = "Ignition delay measurement";
	public static String laminarFlameS = "Laminar flame speed measurement";
	public static String outletConcentrationS = "Outlet concentration measurement";
	public static String concentrationVersusTimeS = "Concentration time profile measurement";
	public static String jetStirredReactorS = "Jet stirred reactor measurement";
	public static String flameSpeciationS = "Burner stabilized flame speciation measurement";

	public static String ignitionDelayP = "IgnitionDelayMeasurement";
	public static String laminarFlameP = "LaminarFlameSpeedMeasurement";
	public static String outletConcentrationP = "OutletConcentrationMeasurement";
	public static String concentrationVersusTimeP = "ConcentrationOverTimeMeasurement";
	public static String jetStirredReactorP = "JetStirredReactorMeasurement";
	public static String flameSpeciationP = "FlameSpeciationMeasurement";

	static String[] phrases = {ignitionDelayS, laminarFlameS, outletConcentrationS, 
			concentrationVersusTimeS, jetStirredReactorS, flameSpeciationS};
	static String[] predicates = {ignitionDelayP, laminarFlameP, outletConcentrationP, 
			concentrationVersusTimeP, jetStirredReactorP, flameSpeciationP};
	
	
	public static String translateExperimentTypesToPredicate(String type) {
		int score = 0;
		String match = type;
		for(int i=0;i< phrases.length; i++) {
			int s = type.compareTo(phrases[i]);
			if(s>score) {
				score = s;
				match = predicates[i];
			}
		}
		return match;
	}
}
