package info.esblurock.reaction.xmlparse.client.ui.respect;


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialRow;
import gwt.material.design.client.ui.MaterialToast;
import info.esblurock.reaction.data.chemical.respect.ReSpecThProperty;

public class ReSpecThPlotAxisDisplay extends Composite implements HasText {

	private static ReSpecThPlotAxisDisplayUiBinder uiBinder = GWT.create(ReSpecThPlotAxisDisplayUiBinder.class);

	interface ReSpecThPlotAxisDisplayUiBinder extends UiBinder<Widget, ReSpecThPlotAxisDisplay> {
	}


	String compositionS = "composition";
	@UiField
	MaterialLabel description;
	@UiField
	MaterialRow descriptionrow;
	@UiField
	MaterialLabel label;
	@UiField
	MaterialLabel name;
	@UiField
	MaterialLabel parameterunits;

	ReSpecThProperty property;
	
	public ReSpecThPlotAxisDisplay() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	public ReSpecThPlotAxisDisplay(String descr) {
		initWidget(uiBinder.createAndBindUi(this));
		description.setText(descr);
	}
	public void fill(ReSpecThProperty property) {
		MaterialToast.fireToast("ReSpecThPlotAxisDisplay  1");
		Window.alert("ReSpecThPlotAxisDisplay\n" + property.toString());
		this.property = property;
		if(property.getDescription().length() != 0) {
			description.setText(property.getDescription());
			descriptionrow.setVisible(true);
		} else {
			descriptionrow.setVisible(true);			
		}
		if(property.getName().compareToIgnoreCase(compositionS) == 0) {
			name.setText(property.getValue());
		} else {
			name.setText(property.getName());
		}
		String parameter = property.getLabel() + "(" + property.getId() + ")";
		label.setText(parameter);
		parameterunits.setText(property.getUnits());
	}
	public void setText(String text) {
		description.setText(text);
	}

	public String getText() {
		return description.getText();
	}

}
