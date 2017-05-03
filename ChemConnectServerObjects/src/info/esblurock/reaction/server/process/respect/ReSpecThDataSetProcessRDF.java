package info.esblurock.reaction.server.process.respect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import info.esblurock.react.mechanisms.chemkin.ChemkinMolecule;
import info.esblurock.react.mechanisms.chemkin.ParseChemkinReaction;
import info.esblurock.reaction.client.utilities.GenerateKeywords;
import info.esblurock.reaction.data.DatabaseObject;
import info.esblurock.reaction.data.chemical.respect.database.ExperimentDescriptionKineticConstants;
import info.esblurock.reaction.data.store.StoreObject;
import info.esblurock.reaction.data.transaction.chemkin.rdf.MechanismMoleculeRDFTransaction;
import info.esblurock.reaction.data.transaction.respect.ReSpecThDataSetToDatabaseTransaction;
import info.esblurock.reaction.data.transaction.respect.ReSpecThDataSetToRDFTransaction;
import info.esblurock.reaction.server.process.ProcessBase;
import info.esblurock.reaction.server.queries.ReSpecThDataQuery;

public class ReSpecThDataSetProcessRDF  extends ProcessBase {

	String toDatabaseS;
	String rdfTransactionS;
	
	ReSpecThDataSetToRDFTransaction rdfTransaction;
	

	
	@Override
	public void initialization() {
		toDatabaseS = "info.esblurock.reaction.data.transaction.respect.ReSpecThDataSetToDatabaseTransaction";
		rdfTransactionS = "info.esblurock.reaction.data.transaction.respect.ReSpecThDataSetToRDFTransaction";
	}

	@Override
	protected String getProcessName() {
		return "ReSpecThDataSetProcessRDF";
	}

	@Override
	protected String getProcessDescription() {
		return "Store RDFs for ReSpecTH";
	}

	@Override
	protected ArrayList<String> getInputTransactionObjectNames() {
		ArrayList<String> input = new ArrayList<String>();
		input.add(toDatabaseS);
		return input;
	}

	@Override
	protected ArrayList<String> getOutputTransactionObjectNames() {
		ArrayList<String> output = new ArrayList<String>();
		output.add(rdfTransactionS);
		return output;
	}

	@Override
	protected void initializeOutputObjects() throws IOException {
		super.initializeOutputObjects();
		rdfTransaction = new ReSpecThDataSetToRDFTransaction(user, outputSourceCode, keyword);
		objectOutputs.add(rdfTransaction);
	}

	@Override
	protected void createObjects() throws IOException {
		StoreObject store = new StoreObject(user,keyword, outputSourceCode);
		String datakey = GenerateKeywords.keywordFromDataKeyword(keyword);
		String sourcekey = GenerateKeywords.sourceFromDataKeyword(keyword);

		rdfExperimentDescriptionKineticConstants(keyword,store);
		
		store.finish();		
	}
	

}
