package info.esblurock.reaction.client.panel.transaction;

import info.esblurock.reaction.data.transaction.TransactionInfo;

import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class UpdateObjectTransactionsCallback implements
		AsyncCallback<List<TransactionInfo>> {

	ObjectTransactionDisplayTree node;

	public UpdateObjectTransactionsCallback(ObjectTransactionDisplayTree objectTransactionDisplayTree) {
		this.node = objectTransactionDisplayTree;
	}

	@Override
	public void onFailure(Throwable caught) {
		Window.alert(caught.toString());
	}

	@Override
	public void onSuccess(List<TransactionInfo> result) {
		try {
			for (TransactionInfo info : result) {
				node.addTransactionInfo(info);
			}
		} catch (Exception ex) {
			Window.alert(ex.toString());
		}

	}

}
