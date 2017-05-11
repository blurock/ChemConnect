package info.esblurock.reaction.server.process.networks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.Text;

import info.esblurock.reaction.client.utilities.GenerateKeywords;
import info.esblurock.reaction.client.utilities.GenerateMoleculeKeywords;
import info.esblurock.reaction.data.DatabaseObject;
import info.esblurock.reaction.data.chemical.molecule.MechanismMoleculeData;
import info.esblurock.reaction.data.chemical.reaction.ChemkinReactionData;
import info.esblurock.reaction.data.network.BaseGraphWithNodesAndLinks;
import info.esblurock.reaction.data.network.GraphLinkBaseData;
import info.esblurock.reaction.data.network.GraphNodeBaseData;
import info.esblurock.reaction.data.network.MechanismAsJSONGraph;
import info.esblurock.reaction.data.store.StoreObject;
import info.esblurock.reaction.data.transaction.chemkin.MechanismReactionsToDatabaseTransaction;
import info.esblurock.reaction.data.transaction.network.JSONNetworkObject;
import info.esblurock.reaction.data.transaction.network.MechanismToNetworkTransaction;
import info.esblurock.reaction.server.process.ProcessBase;
import info.esblurock.reaction.server.process.ProcessInputSpecificationsBase;
import info.esblurock.reaction.server.queries.ChemicalMechanismDataQuery;
import info.esblurock.reaction.server.utilities.GenerateReactionKeywordsServer;

public class MechanismAsNetwork  extends ProcessBase {
	
	public static String reactantS = "isReactant";
	public static String productS = "isProduct";
	public static String linkDelimitorS = "#";
	public static String networkAsJSONS = "NetworkAsJSONString";
	
	String molDatabaseS;
	String rxnDatabaseS;
	String networkTransactionS;
	MechanismReactionsToDatabaseTransaction rxntransaction;
	MechanismToNetworkTransaction networkTransaction;

	GenerateReactionKeywordsServer generateReactions;
	StoreObject store;
	
	public MechanismAsNetwork() {
		super();
	}

	public MechanismAsNetwork(ProcessInputSpecificationsBase input) {
		super(input);
	}

	@Override
	public void initialization() {
		molDatabaseS = "info.esblurock.reaction.data.transaction.chemkin.MechanismMoleculesToDatabaseTransaction";
		rxnDatabaseS = "info.esblurock.reaction.data.transaction.chemkin.MechanismReactionsToDatabaseTransaction";
		networkTransactionS = "info.esblurock.reaction.data.transaction.network.MechanismToNetworkTransaction";
		
	}

	@Override
	protected String getProcessName() {
		return "MechanismAsNetwork";
	}

	@Override
	protected String getProcessDescription() {
		return "Convert mechanism to network";
	}

	@Override
	protected ArrayList<String> getInputTransactionObjectNames() {
		ArrayList<String> input = new ArrayList<String>();
		input.add(molDatabaseS);
		input.add(rxnDatabaseS);
		return input;
	}

	@Override
	protected ArrayList<String> getOutputTransactionObjectNames() {
		ArrayList<String> input = new ArrayList<String>();
		input.add(networkTransactionS);
		return input;
	}

	@Override
	protected void createObjects() throws IOException {
		store = new StoreObject(user,keyword, outputSourceCode);
		List<DatabaseObject> moleculelist = ChemicalMechanismDataQuery.moleculesFromMechanismName(keyword);
		List<DatabaseObject> reactionlist = ChemicalMechanismDataQuery.reactionsFromMechanismName(keyword);
		List<DatabaseObject> photolist = ChemicalMechanismDataQuery.photoReactionsFromMechanismName(keyword);
		generateReactions = new GenerateReactionKeywordsServer(keyword);

		BaseGraphWithNodesAndLinks basegraph = new BaseGraphWithNodesAndLinks(keyword, outputSourceCode);
		//String datakey = GenerateKeywords.keywordFromDataKeyword(keyword);
		String sourcekey = GenerateKeywords.sourceFromDataKeyword(keyword);
		
		for (DatabaseObject object : moleculelist) {
			MechanismMoleculeData molecule = (MechanismMoleculeData) object;
			String fullname = GenerateMoleculeKeywords.getDataKeyword(molecule);
			String name = molecule.getMoleculeName();
			GraphNodeBaseData graphnode = basegraph.addBaseNode(name);
			store.store(graphnode);
		}		
		
		for(DatabaseObject object : reactionlist) {
			ChemkinReactionData data = (ChemkinReactionData) object;
			String fullrxnS = data.getReactionName();
			String rxnS = generateReactions.parseOutSimpleReactionName(fullrxnS);
			basegraph.addBaseNode(rxnS);
			for(String reactant : data.getReactantKeys()) {
				String molS = generateReactions.parseOutSimpleMoleculeName(reactant);
				String name = molS + linkDelimitorS + rxnS;
				GraphLinkBaseData graphlink = basegraph.addSimpleLink(name,molS,rxnS,reactantS);
				store.store(graphlink);
			}
			for(String product : data.getProductKeys()) {
				String molS = generateReactions.parseOutSimpleMoleculeName(product);
				String name = rxnS + linkDelimitorS + molS;
				GraphLinkBaseData graphlink = basegraph.addSimpleLink(name,rxnS,molS,productS);
				store.store(graphlink);
			}
		}
		JSONNetworkObject networkobject = new JSONNetworkObject(basegraph);
		Text jsongraphT = new Text(networkobject.toString());
		MechanismAsJSONGraph jsongraph = new MechanismAsJSONGraph(keyword, outputSourceCode,jsongraphT);
		store.store(jsongraph);
		store.finish();
	}

}
