package info.esblurock.reaction.server.parse.query;

import info.esblurock.reaction.server.parse.interpretation.ANDInterpretation;
import info.esblurock.reaction.server.parse.interpretation.SetOfInterpretations;
import info.esblurock.reaction.server.parse.objects.two.ObjectAndPredicateParseObject;
import info.esblurock.reaction.server.parse.objects.two.SubjectAndPredicateParseObject;

public class ParseQueryAsTwoKeywords  implements ParseQuery {

	@Override
	public SetOfInterpretations parseInput() {
		SetOfInterpretations set = new SetOfInterpretations();
		ObjectAndPredicateParseObject objectParse = new ObjectAndPredicateParseObject();
		SubjectAndPredicateParseObject subjectParse = new SubjectAndPredicateParseObject();
		ANDInterpretation andinterpretation = new ANDInterpretation(objectParse,subjectParse);
		
		//set.add(andinterpretation);
		return set;
	}

}
