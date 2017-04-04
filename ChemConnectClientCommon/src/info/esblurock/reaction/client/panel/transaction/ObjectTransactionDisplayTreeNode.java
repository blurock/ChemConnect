package info.esblurock.reaction.client.panel.transaction;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialCollapsible;
import gwt.material.design.client.ui.MaterialLink;
import info.esblurock.reaction.data.transaction.TransactionInfo;

public class ObjectTransactionDisplayTreeNode extends Composite implements HasText {

	private static ObjectTransactionDisplayTreeNodeUiBinder uiBinder = GWT
			.create(ObjectTransactionDisplayTreeNodeUiBinder.class);

	interface ObjectTransactionDisplayTreeNodeUiBinder extends UiBinder<Widget, ObjectTransactionDisplayTreeNode> {
	}

	public ObjectTransactionDisplayTreeNode() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	MaterialLink transactionlabel;
	@UiField
	MaterialCollapsible collapsible;

	public ObjectTransactionDisplayTreeNode(String title) {
		initWidget(uiBinder.createAndBindUi(this));
		transactionlabel.setText(title);
	}

	public ObjectTransactionDisplayTreeNode findTransactionNode(String nodetitle) {
		ObjectTransactionDisplayTreeNode node = null;
		boolean notfound = collapsible.getWidgetCount() > 0;
		int count = 0;
		while(notfound) {
			ObjectTransactionDisplayTreeNode subnode = (ObjectTransactionDisplayTreeNode) collapsible.getWidget(count);
			if(subnode.getText().compareTo(nodetitle) == 0) {
				node = subnode;
				notfound = false;
			}
			count++;
			if(count >= collapsible.getWidgetCount()){
				notfound = false;
			}
		}
		if(node == null) {
			node = new ObjectTransactionDisplayTreeNode(nodetitle);
			collapsible.add(node);
		}
		return node;
	}
	
	public void setText(String text) {
		transactionlabel.setText(text);
	}

	public String getText() {
		return transactionlabel.getText();
	}

	public void addTransactionInfo(TransactionInfo info) {
		String title = info.getKeyword();
		ObjectTransaction node = null;
		boolean notfound = collapsible.getWidgetCount() > 0;
		int count = 0;
		while(notfound) {
			ObjectTransaction subnode = (ObjectTransaction) collapsible.getWidget(count);
			if(subnode.getText().compareTo(title) == 0) {
				node = subnode;
				notfound = false;
			} else if(count++ >= collapsible.getWidgetCount()){
				notfound = false;
			}
		}
		if(node == null) {
			node = new ObjectTransaction(title);
			collapsible.add(node);
		}
		node.addTransactionInfo(info);
		node.refresh();
	}

}
