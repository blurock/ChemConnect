package info.esblurock.reaction.query.client.panel.repository.data;

import info.esblurock.reaction.query.client.panel.repository.RepositoryBaseItem;
import info.esblurock.reaction.query.client.panel.repository.RepositoryFileItemTextField;

public class RepositoryFileItem extends RepositoryBaseItem {

	RepositoryFileItemTextField itemField;
	String classname;
	
	public RepositoryFileItem(String datasetkeyword, String classname) {
		super(datasetkeyword);
		this.classname = classname;
		//TransactionServiceAsync async = TransactionService.Util.getInstance();
		//FileUploadTextCallback callback = new FileUploadTextCallback(this);
		//async.getFileUploadTextBlockFromTransaction(datasetkeyword, classname, callback);
	}
/*
	public RepositoryFileItemTextField initializeText(ArrayList<String> result) {
		int pos = classname.lastIndexOf('.');
		RepositoryFileItemTextField itemField = new RepositoryFileItemTextField(classname.substring(pos));
		itemField.initializeText(result);
		setText(itemField.getText());
		setcollapse.add(itemField);
		initialSettings();
		return itemField;
	}
	*/
} 
