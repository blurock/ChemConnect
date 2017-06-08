package info.esblurock.reaction.experiment.client.project.items;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialCollapsible;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialToast;
import info.esblurock.reaction.experiment.client.ui.ExperimentalDataImpl;

public class ChooseExperimentalCollapsibleItem extends Composite implements HasText {

	private static ChooseExperimentalCollapsibleItemUiBinder uiBinder = GWT
			.create(ChooseExperimentalCollapsibleItemUiBinder.class);

	interface ChooseExperimentalCollapsibleItemUiBinder extends UiBinder<Widget, ChooseExperimentalCollapsibleItem> {
	}

	@UiField
	MaterialLink listTitle;
	@UiField
	MaterialLink addItem;
	@UiField
	MaterialLink info;
	@UiField
	protected MaterialCollapsible itemsList;
		
	protected ExperimentalDataImpl experimentalData;
	protected String subtype;

	public ChooseExperimentalCollapsibleItem(ExperimentalDataImpl experimentalData) {
		initWidget(uiBinder.createAndBindUi(this));
		this.experimentalData = experimentalData;

	}

	public ChooseExperimentalCollapsibleItem(String title,ExperimentalDataImpl experimentalData) {
		initWidget(uiBinder.createAndBindUi(this));
		this.experimentalData = experimentalData;
		listTitle.setText(title);
	}

	@UiHandler("addItem")
	void onItemAddClick(ClickEvent e) {
		addItem();
	}
	protected void addItem() {
		MaterialToast.fireToast("Add item: " + listTitle);
	}

	@UiHandler("info")
	void onInfoClick(ClickEvent e) {
	}

	public void setText(String text) {
		listTitle.setText(text);
		//info.setText(text);
	}

	public String getText() {
		return listTitle.getText();
	}

	public void setSubType(String subtype) {
		this.subtype = subtype;
	}
}
