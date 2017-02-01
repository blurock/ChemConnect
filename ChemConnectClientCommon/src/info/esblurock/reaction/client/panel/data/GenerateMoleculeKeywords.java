package info.esblurock.reaction.client.panel.data;

import info.esblurock.reaction.client.utilities.GenerateKeywords;
import info.esblurock.reaction.data.chemical.molecule.MechanismMoleculeData;

public class GenerateMoleculeKeywords {
	static public String delimitor = "#";

	static public String getDataKeyword(MechanismMoleculeData molecule) {
		String key = GenerateKeywords.generateMoleculeKeyword(molecule.getMechanismKeyword(),molecule.getMoleculeName());
		return key;
	}

}
