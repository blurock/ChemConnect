package info.esblurock.reaction.query.client.panel;

import java.util.ArrayList;

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
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialCollapsible;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialModal;
import gwt.material.design.client.ui.MaterialToast;
import info.esblurock.reaction.data.store.UserObjectStorage;

public class UserStorageObjectTreeNodeDisplay extends Composite implements HasText {

	private static UserStorageObjectTreeNodeDisplayUiBinder uiBinder = GWT
			.create(UserStorageObjectTreeNodeDisplayUiBinder.class);

	interface UserStorageObjectTreeNodeDisplayUiBinder extends UiBinder<Widget, UserStorageObjectTreeNodeDisplay> {
	}


	@UiField
	MaterialLink title;
	@UiField
	MaterialLink deletesub;
	@UiField
	MaterialCollapsible collapsible;
	@UiField
	MaterialLink savenode;
	@UiField
	MaterialModal displaymodal;
	@UiField
	HTMLPanel modalobjects;
	@UiField
	MaterialLink modalclose;
	@UiField
	MaterialLink modalok;
	
	UserObjectStorage userobject;
	//String key;
	//String objectclass;
	ArrayList<UserStorageObjectTreeNodeDisplay> objects;
	QueryAndResultPanel toppanel;
	
	String visualizationPanelS = "Display";
	
	public UserStorageObjectTreeNodeDisplay(QueryAndResultPanel toppanel) {
		initWidget(uiBinder.createAndBindUi(this));
		this.toppanel = toppanel;
	}

	public UserStorageObjectTreeNodeDisplay(String title,QueryAndResultPanel toppanel) {
		initWidget(uiBinder.createAndBindUi(this));
		this.title.setText(title);
		this.userobject = null;
		savenode.setVisible(false);
		this.toppanel = toppanel;
	}
	public UserStorageObjectTreeNodeDisplay(String title, UserObjectStorage userobject, QueryAndResultPanel toppanel) {
		initWidget(uiBinder.createAndBindUi(this));
		this.title.setText(title);
		this.userobject = userobject;
		savenode.setVisible(true);
		this.toppanel = toppanel;
	}

	@UiHandler("deletesub")
	public void deleteNode(ClickEvent event) {
		this.removeFromParent();
	}
	@UiHandler("modalclose")
	public void closeModal(ClickEvent event) {
		displaymodal.closeModal();
		objects = null;
	}
	@UiHandler("modalok")
	public void okModal(ClickEvent event) {
		displaymodal.closeModal();
		
	}
	@UiHandler("savenode")
	public void saveNode(ClickEvent event) {
		toppanel.displayItem(userobject,visualizationPanelS);
	}
	
	
	private ArrayList<UserStorageObjectTreeNodeDisplay> findSubnodeObjects(UserStorageObjectTreeNodeDisplay node) {
		ArrayList<UserStorageObjectTreeNodeDisplay> objects = null;
		if(userobject.getStoredObjectKey() == null) {
			objects = new ArrayList<UserStorageObjectTreeNodeDisplay>();
			int count = collapsible.getWidgetCount();
			for(int i=0;i<count;i++) {
				Widget widget = collapsible.getWidget(count);
				UserStorageObjectTreeNodeDisplay display = (UserStorageObjectTreeNodeDisplay) widget;
				ArrayList<UserStorageObjectTreeNodeDisplay> subobjects = findSubnodeObjects(display);
				objects.addAll(subobjects);
			}
		} else {
			objects = new ArrayList<UserStorageObjectTreeNodeDisplay>();
			objects.add(this);
		}
		return objects;
	}

	public MaterialCollapsible getCollapsible() {
		return collapsible;
	}

	public void setText(String text) {
		title.setText(text);
	}

	public String getText() {
		return title.getText();
	}

	public UserObjectStorage getUserobject() {
		return userobject;
	}


}
