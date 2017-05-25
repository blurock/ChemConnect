package info.esblurock.reaction.experiment.client.project.items;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialCollapsible;
import gwt.material.design.client.ui.MaterialLink;

public class SpecificationList extends Composite implements HasText {

	private static SpecificationListUiBinder uiBinder = GWT.create(SpecificationListUiBinder.class);

	interface SpecificationListUiBinder extends UiBinder<Widget, SpecificationList> {
	}

	public SpecificationList() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	MaterialLink title;
	@UiField
	MaterialLink addProperty;
	@UiField
	MaterialLink addImage;
	@UiField
	MaterialLink addText;
	@UiField
	MaterialCollapsible collapsibleList;

	public SpecificationList(String text) {
		initWidget(uiBinder.createAndBindUi(this));
		title.setText(text);
	}


	public void setText(String text) {
		title.setText(text);
	}

	public String getText() {
		return title.getText();
	}

}
