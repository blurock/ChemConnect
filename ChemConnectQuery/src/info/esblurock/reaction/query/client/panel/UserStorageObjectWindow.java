package info.esblurock.reaction.query.client.panel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.addins.client.window.MaterialWindow;
import gwt.material.design.client.ui.MaterialCollapsible;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialTab;
import info.esblurock.reaction.client.async.TransactionService;
import info.esblurock.reaction.client.async.TransactionServiceAsync;
import info.esblurock.reaction.data.store.UserObjectStorage;
import info.esblurock.reaction.data.store.UserStorageObjectTreeNode;

public class UserStorageObjectWindow extends Composite implements HasText {

	private static UserStorageObjectWindowUiBinder uiBinder = GWT.create(UserStorageObjectWindowUiBinder.class);

	interface UserStorageObjectWindowUiBinder extends UiBinder<Widget, UserStorageObjectWindow> {
	}

	public UserStorageObjectWindow() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public UserStorageObjectWindow(QueryAndResultPanel toppanel) {
		initWidget(uiBinder.createAndBindUi(this));
		this.toppanel = toppanel;
	}

	@UiField
	ScrollPanel storagetree;
	@UiField
	HTMLPanel windowTab;
	@UiField
	MaterialLink closewindow;
	@UiField
	MaterialLink refresh;
	
	QueryAndResultPanel toppanel;
	
	private void addStoreObject(MaterialCollapsible collapsible, UserStorageObjectTreeNode node) {
		if(node.isNameNode()) {
			UserStorageObjectTreeNodeDisplay display = 
					new UserStorageObjectTreeNodeDisplay(node.getNodeName(),toppanel);
			collapsible.add(display);
			for(UserStorageObjectTreeNode child : node.getChildren()) {
				addStoreObject(display.getCollapsible(),child);
			}
		} else {
			UserObjectStorage store = node.getObject();
			UserStorageObjectTreeNodeDisplay display = new UserStorageObjectTreeNodeDisplay(node.getShortdescription(),
					store,toppanel);
			collapsible.add(display);
		}
	}

	public void clearVisualization() {
		storagetree.clear();
	}
	public void refreshStorageTree() {
		TransactionServiceAsync async = TransactionService.Util.getInstance();
		RefreshStorageTreeCallback callback = new RefreshStorageTreeCallback(this);
		async.getStorageObjectsForCurrentUser(callback);
	}
	
	public void insertTree(UserStorageObjectTreeNode node) {
		storagetree.clear();
		MaterialCollapsible collapsible = new MaterialCollapsible();
		storagetree.add(collapsible);
		addStoreObject(collapsible,node);
	}
	@UiHandler("refresh")
	public void refreshWindow(ClickEvent e) {
		refreshStorageTree();
	}
	public void setText(String text) {
		windowTab.setTitle(text);
	}

	public String getText() {
		return windowTab.getTitle();
	}

	public void setQueryPanel(QueryAndResultPanel queryAndResultPanel) {
		toppanel = queryAndResultPanel;
	}

}
