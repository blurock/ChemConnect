package info.esblurock.reaction.client.panel.description;

import java.util.ArrayList;
import java.util.Iterator;

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
import gwt.material.design.client.ui.MaterialLink;
import info.esblurock.reaction.client.async.StoreDescriptionData;
import info.esblurock.reaction.client.async.StoreDescriptionDataAsync;
import info.esblurock.reaction.data.description.DataSetReference;
import info.esblurock.reaction.data.description.ObjectLinkDescription;

public class SetOfObjectLinks extends Composite implements HasText {

	private static SetOfObjectLinksUiBinder uiBinder = GWT.create(SetOfObjectLinksUiBinder.class);

	interface SetOfObjectLinksUiBinder extends UiBinder<Widget, SetOfObjectLinks> {
	}


	@UiField
	MaterialLink addlink;
	@UiField
	MaterialCollapsible links;
	@UiField
	MaterialLink linkheader;

	ArrayList<ObjectLinkInformation> linkdescriptions;
	
	public SetOfObjectLinks() {
		initWidget(uiBinder.createAndBindUi(this));
		init();
	}
	public SetOfObjectLinks(String text) {
		initWidget(uiBinder.createAndBindUi(this));
		addlink.setText(text);
		init();
	}
	void init() {
		linkheader.setText("Links");
		addlink.setText("Add Link");
	}
	public void setUnenabled() {
		addlink.setVisible(false);
	}
	
	@UiHandler("addlink")
	void onClick(ClickEvent e) {
		ObjectLinkInformation reference = new ObjectLinkInformation();
		links.add(reference);
	}
	public void setText(String text) {
		addlink.setText(text);
	}

	public String getText() {
		return addlink.getText();
	}

	public ArrayList<ObjectLinkInformation> getLinks() {
		linkdescriptions = new ArrayList<ObjectLinkInformation>();
		Iterator<Widget> iter = links.iterator();
		while(iter.hasNext()) {
			Widget widget = iter.next();
			String type = widget.getClass().getSimpleName();
			if(type.matches("ReferenceDescriptions")) {
				ObjectLinkInformation link = (ObjectLinkInformation) widget;
				linkdescriptions.add(link);
			}
		}
		return linkdescriptions;
	}
	public void fillInLinks(String datasetkeyword) {
		StoreDescriptionDataAsync async = StoreDescriptionData.Util.getInstance();
		FillInObjectLinksCallback callback = new FillInObjectLinksCallback(this);
		async.getDataSetOfLinks(datasetkeyword, callback);
	}
	public void addlink(ObjectLinkInformation link) {
		links.add(link);
	}

}
