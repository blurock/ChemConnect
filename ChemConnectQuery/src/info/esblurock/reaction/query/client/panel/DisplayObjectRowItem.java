package info.esblurock.reaction.query.client.panel;

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

import gwt.material.design.client.ui.MaterialLink;

public class DisplayObjectRowItem extends Composite implements HasText {

	private static DisplayObjectRowItemUiBinder uiBinder = GWT.create(DisplayObjectRowItemUiBinder.class);

	interface DisplayObjectRowItemUiBinder extends UiBinder<Widget, DisplayObjectRowItem> {
	}

	public DisplayObjectRowItem() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	MaterialLink description;
	@UiField
	MaterialLink delete;
	
	public DisplayObjectRowItem(String objectdescr) {
		initWidget(uiBinder.createAndBindUi(this));
		description.setText(objectdescr);
	}

	@UiHandler("delete")
	void onClick(ClickEvent e) {
		this.removeFromParent();
	}

	public void setText(String text) {
		description.setText(text);
	}

	public String getText() {
		return description.getText();
	}

}
