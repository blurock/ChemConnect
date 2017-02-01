package info.esblurock.reaction.server.utilities;

import java.util.ArrayList;

import info.esblurock.reaction.client.utilities.GenerateKeywords;
import info.esblurock.reaction.client.utilities.GenerateReactionKeywords;
import info.esblurock.reaction.data.chemical.reaction.ChemkinReactionData;

public class GenerateReactionKeywordsServer extends GenerateReactionKeywords {
	String keywordBase;
	String delimitor = "#";

	public GenerateReactionKeywordsServer(String keywordBase) {
		super(keywordBase);
		this.keywordBase = keywordBase;
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
	
	public String getReactionFullName(ChemkinReactionData reaction) {
		String rxn = getReactionSimpleName(reaction);
		return getReactionFullName(rxn);
	}
	
}
