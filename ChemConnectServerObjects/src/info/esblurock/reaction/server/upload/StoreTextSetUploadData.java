package info.esblurock.reaction.server.upload;

import java.util.HashSet;

import info.esblurock.reaction.data.DatabaseObject;
import info.esblurock.reaction.data.description.DescriptionDataData;
import info.esblurock.reaction.data.store.StoreObject;
import info.esblurock.reaction.data.transaction.TransactionInfo;
import info.esblurock.reaction.data.upload.InputTextSource;
import info.esblurock.reaction.data.upload.TextSetUploadData;
import info.esblurock.reaction.server.process.description.StoreRegisteredDescription;

public class StoreTextSetUploadData  extends StoreObject {
	static String textSourceType = "TextInputSourceType";
	static String textSourceName = "TextSourceName";
	static String textType = "TextType";
	static String textInformationKey = "TextInformationKey";
	static String createdBy = "CreatedBy";
	static String keyword = "DataSetKeyword";
	
	public StoreTextSetUploadData(String keyword, DatabaseObject object,
			TransactionInfo transaction) {
		super(keyword, object, transaction);
	}
	protected void storeObject() {
		super.storeObject();
		TextSetUploadData data = (TextSetUploadData) object;
		data.getDescription().setParentKey(data.getKey());
		StoreRegisteredDescription storedescr = new StoreRegisteredDescription(transaction.getSourceCode(), data.getDescription());
		storedescr.storeAsRDFs();
		flushStore();
	}
	protected void storeRDF() {
		TextSetUploadData data = (TextSetUploadData) object;
		storeObjectRDF(data);
		DescriptionDataData description = data.getDescription();
		isA(data.getDescription().getDataType());
		storeStringRDF(createdBy, description.getSourcekey());
		for(InputTextSource source : data.getInputTextSources()) {
			storeStringRDF(textSourceType,source.getSourceType());
			storeStringRDF(textSourceName,source.getTextname());
			storeStringRDF(textType,source.getTextType());
			storeStringRDF(textInformationKey, source.getID());
		}
		HashSet<String> keywords = description.getKeywords();
		for(String key : keywords) {
			storeStringRDF(keyword,key);
		}
	}
	
}
