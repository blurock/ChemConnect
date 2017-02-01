package info.esblurock.reaction.server.process.description;

import java.util.Date;

import info.esblurock.reaction.data.DateAsString;
import info.esblurock.reaction.data.description.DescriptionDataData;
import info.esblurock.reaction.data.store.StoreObject;

public class StoreRegisteredDescription {
	static String descriptionString = "Description";
	static String oneLineDescription = "OneLine";
	static String fullDescription = "FullDescription";
	static String sourceOfData= "SourceOfData";
	static String keywordString = "Keyword";
	static public String dataSetType = "DataSetType";

	protected String user;
	protected String keyword;
	protected String outputSourceCode;

	DescriptionDataData data;

	public StoreRegisteredDescription(String outputSourceCode,
			DescriptionDataData data) {
		super();
		this.keyword = data.getKeyword();
		this.user = data.getInputkey();
		this.outputSourceCode = outputSourceCode;
		this.data = data;
	}


	public void storeAsRDFs() {
		StoreObject store = new StoreObject(user, keyword, outputSourceCode);		
		store.storeStringRDF(oneLineDescription,data.getOnlinedescription());
		store.storeStringRDF(fullDescription,data.getFulldescription());
		store.storeStringRDF(sourceOfData,data.getSourcekey());
		Date sourcedate = data.getSourceDate();
		if(sourcedate == null) {
			sourcedate = new Date();
		}
		String sourcedateS = DateAsString.dateAsString(sourcedate);
		store.storeStringRDF(sourceOfData,sourcedateS);
		for(String key : data.getKeywords()) {
			store.storeStringRDF(keywordString, key);
		}
		store.finish();
	}
}
