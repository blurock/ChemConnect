package info.esblurock.reaction.xmlparse.client.ui.respect;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialRow;
import info.esblurock.reaction.data.chemical.respect.ReSpecThProperty;

public class ReSpecThCommonPropertyDisplay extends Composite implements HasText {

	private static ReSpecThPropertyDisplayUiBinder uiBinder = GWT.create(ReSpecThPropertyDisplayUiBinder.class);

	interface ReSpecThPropertyDisplayUiBinder extends UiBinder<Widget, ReSpecThCommonPropertyDisplay> {
	}

	public ReSpecThCommonPropertyDisplay() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	MaterialLabel description;
	@UiField
	MaterialRow descriptionrow;
	@UiField
	MaterialLabel parametername;
	@UiField
	MaterialLabel parametervalue;
	@UiField
	MaterialLabel parameterunits;

	ReSpecThProperty property;
	
	public ReSpecThCommonPropertyDisplay(String descr) {
		initWidget(uiBinder.createAndBindUi(this));
		description.setText(descr);
	}

	public void fill(ReSpecThProperty property) {
		this.property = property;
		if(property.getDescription().length() != 0) {
			description.setText(property.getDescription());
			descriptionrow.setVisible(true);
		} else {
			descriptionrow.setVisible(true);			
		}
		String name = property.getName() + " (" + property.getLabel() + ")";
		parametername.setText(name);
		parametervalue.setText(property.getValue());
		parameterunits.setText(property.getUnits());
	}

	public void setText(String text) {
		description.setText(text);
	}
	public String getText() {
		return description.getText();
	}

}
