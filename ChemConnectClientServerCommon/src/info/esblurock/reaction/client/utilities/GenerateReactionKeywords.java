package info.esblurock.reaction.client.utilities;

import java.util.ArrayList;
import java.util.Collections;

import info.esblurock.reaction.data.chemical.reaction.ChemkinReactionData;

public class GenerateReactionKeywords {
	String keywordBase;
	String keywordDelimitor = "#";

	public GenerateReactionKeywords(String keywordBase) {
		this.keywordBase = keywordBase;
	}

	public String getReactionFullName(String rxnEquation) {
		String key;
		if (keywordBase != null) {
			key = keywordBase + keywordDelimitor + rxnEquation;
		} else {
			key = rxnEquation;
		}
		return key;
	}
	
	public String getReactionSimpleName(ChemkinReactionData reaction) {
		ArrayList<String> simpleReactantNames = new ArrayList<String>();
		ArrayList<String> simpleProductNames = new ArrayList<String>();
		for(String fullname : reaction.getReactantKeys()) {
			String name = GenerateKeywords.moleculeNameFromMoleculeKeyword(fullname);
			simpleReactantNames.add(name);
		}
		for(String fullname : reaction.getProductKeys()) {
			String name = GenerateKeywords.moleculeNameFromMoleculeKeyword(fullname);
			simpleProductNames.add(name);
		}
		String rxn = getReactionName(simpleReactantNames,simpleProductNames);
		return rxn.toLowerCase();
	}

	public String getReactionName(ArrayList<String> reactants, ArrayList<String> products) {
		String reacS = getMoleculeListString(reactants);
		String prodS = getMoleculeListString(products);
		String rxnS = reacS + "=" + prodS;
		return rxnS.toLowerCase();
	}

	public String getMoleculeListString(ArrayList<String> mol) {
		Collections.sort(mol);
		StringBuilder build = new StringBuilder();
		for (String name : mol) {
			build.append(name);
			build.append("+");
		}
		String appendname = build.toString();
		String endmol = appendname.substring(0, appendname.length() - 1);
		return endmol;
	}

	public String parseOutReactionMechanismName(String rxnname) {
		String ans = rxnname;
		int pos = rxnname.lastIndexOf(keywordDelimitor);
		if (pos > 0) {
			ans = rxnname.substring(0, pos);
		}
		return ans;
	}

	private String parseOutSimpleMoleculeName(String name) {
		String delim = GenerateKeywords.delimitor;
		String simplename = name;
		int pos = 0;
		while (pos >= 0) {
			String sub = simplename.substring(pos + 1);
			simplename = sub;
			pos = simplename.indexOf(delim);
		}
		return simplename;
	}

}
