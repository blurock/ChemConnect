package info.esblurock.reaction.client.panel.transaction;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialCollapsible;
import gwt.material.design.client.ui.MaterialTitle;
import info.esblurock.reaction.client.async.TransactionService;
import info.esblurock.reaction.client.async.TransactionServiceAsync;
import info.esblurock.reaction.data.transaction.TransactionInfo;

public class ObjectTransactionDisplayTree extends Composite implements HasText {

	private static ObjectTransactionDisplayTreeUiBinder uiBinder = GWT
			.create(ObjectTransactionDisplayTreeUiBinder.class);

	interface ObjectTransactionDisplayTreeUiBinder extends UiBinder<Widget, ObjectTransactionDisplayTree> {
	}


	@UiField
	MaterialCollapsible collapsible;
	@UiField
	MaterialTitle title;

	ObjectTransactionDisplayTreeNode topnode;
	
	public ObjectTransactionDisplayTree() {
		initWidget(uiBinder.createAndBindUi(this));
		init("");
	}
	public ObjectTransactionDisplayTree(String title) {
		initWidget(uiBinder.createAndBindUi(this));
		init(title);
	}

	private void init(String title) {
		this.title.setTitle(title);
		topnode = new ObjectTransactionDisplayTreeNode(title);
		collapsible.add(topnode);
		getAllTransactionInfo();
	}
	private void getAllTransactionInfo() {
		TransactionServiceAsync async = TransactionService.Util.getInstance();
		UpdateObjectTransactionsCallback callback = new UpdateObjectTransactionsCallback(this);
		async.getAllTransactions(callback);
	}

	public void setText(String text) {
		title.setTitle(text);
	}

	public String getText() {
		return title.getTitle();
	}

	public void addTransactionInfo(TransactionInfo info) {
		ArrayList<String> path = getTransactionInfoPath(info.getUser(), info);
		ObjectTransactionDisplayTreeNode node = topnode;
		for(String name : path) {
			node = node.findTransactionNode(name);
		}
		node.addTransactionInfo(info);
	}

	private ArrayList<String> getTransactionInfoPath(String name, TransactionInfo info) {
		String fullkeyword = info.getKeyword();
		int pos = fullkeyword.indexOf("#");
		String source = fullkeyword.substring(0, pos);
		String key = fullkeyword.substring(pos+1);
		ArrayList<String> path = new ArrayList<String>();
		path.add(name);
		path.add(source);
		path.add(key);
		return path;
	}
}
