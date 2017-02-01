package info.esblurock.reaction.server.parse.interpretation.reaction;

import java.io.IOException;
import java.util.StringTokenizer;

import info.esblurock.reaction.server.parse.interpretation.Interpretation;
import info.esblurock.reaction.server.parse.interpretation.QueryParameters;
import info.esblurock.reaction.server.parse.interpretation.RDFQueryResultSet;
import info.esblurock.reaction.server.parse.interpretation.set.NeighborCombinationInterpretation;
import info.esblurock.reaction.server.parse.objects.chemical.reaction.CanonicalReactionName;
import info.esblurock.reaction.server.parse.objects.chemical.reaction.ParsedReactionInformation;

public class InterpretationAsReactionMultiple extends Interpretation {

	@Override
	public boolean interpretable(QueryParameters input) {
		String inputS = input.getInputString();
		StringTokenizer tok = new StringTokenizer(inputS, " ");
		boolean ans = tok.countTokens() > 1;
		System.out.println("InterpretationAsReactionSimple: interpretable(" + input + ")=" + ans);
		return ans;
	}

	@Override
	public RDFQueryResultSet getResults(QueryParameters input) {
		RDFQueryResultSet set = null;
		CanonicalReactionName canonical = new CanonicalReactionName();
		try {
			ParsedReactionInformation parsed = canonical.getCanonicalReactionName(input.getInputString());
			NeighborCombinationInterpretation neighbor = new NeighborCombinationInterpretation();
			QueryParameters params = new QueryParameters(parsed.tokensAsString(),5000);
			set = neighbor.getResults(params);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return set;
	}
}
