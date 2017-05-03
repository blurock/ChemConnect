package info.esblurock.reaction.server.process.respect.rdf;

import java.io.IOException;
import java.util.List;

import info.esblurock.react.mechanisms.chemkin.ChemkinMolecule;
import info.esblurock.react.mechanisms.chemkin.ParseChemkinReaction;
import info.esblurock.reaction.data.DatabaseObject;
import info.esblurock.reaction.data.chemical.respect.database.ExperimentDescriptionKineticConstants;
import info.esblurock.reaction.data.store.StoreObject;
import info.esblurock.reaction.server.queries.ReSpecThDataQuery;

public class RDFExperimentDescriptionKineticConstants {


	private void rdfExperimentDescriptionKineticConstants(String keyword, StoreObject store) throws IOException {
		List<DatabaseObject> lst = ReSpecThDataQuery.findExperimentDescriptionKineticConstants(keyword);
		for(Object obj : lst) {
			ExperimentDescriptionKineticConstants constants = (ExperimentDescriptionKineticConstants) obj;
			ParseChemkinReaction parserxn = new ParseChemkinReaction();
			String reaction = parserxn.normalize(constants.getReaction());
			String reactants = parserxn.getReactantsAsString();
			String products = parserxn.getReactantsAsString();
			store.storeStringRDF(ExperimentRDFConstants.reactionPredicateS, reaction);
			store.storeStringRDF(ExperimentRDFConstants.reactantsPredicateS, reactants);
			if(products.compareToIgnoreCase(ExperimentRDFConstants.productsKeyS) != 0) {
				store.storeStringRDF(ExperimentRDFConstants.productPredicateS, products);
			}
			for(ChemkinMolecule react : parserxn.getReactants()) {
				store.storeStringRDF(ExperimentRDFConstants.reactantPredicateS, react.getLabel().toLowerCase());
			}
			for(ChemkinMolecule prod : parserxn.getReactants()) {
				store.storeStringRDF(ExperimentRDFConstants.productPredicateS, prod.getLabel().toLowerCase());
			}
			store.storeStringRDF(ExperimentRDFConstants.bulkGasPredicateS, constants.getBulkgas());
			store.storeStringRDF(ExperimentRDFConstants.reactionOrderPredicateS, constants.getOrder());
			String type = ExperimentRDFConstants.translateExperimentTypesToPredicate(constants.getExperimentType());
			store.storeStringRDF(ExperimentRDFConstants.experimentTypePredicateS, type);
			store.storeStringRDF(ExperimentRDFConstants.reactionOrderPredicateS, 
					constants.getOrder());
		}
	}
	
}
