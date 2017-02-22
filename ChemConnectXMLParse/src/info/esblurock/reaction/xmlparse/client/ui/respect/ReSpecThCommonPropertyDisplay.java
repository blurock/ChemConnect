package info.esblurock.reaction.xmlparse.client.ui.respect;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialRow;
import info.esblurock.reaction.data.chemical.respect.ReSpecThComponent;
import info.esblurock.reaction.data.chemical.respect.ReSpecThProperty;
import info.esblurock.reaction.xmlparse.resources.XMLParseResource;

public class ReSpecThCommonPropertyDisplay extends Composite implements HasText {

	private static ReSpecThPropertyDisplayUiBinder uiBinder = GWT.create(ReSpecThPropertyDisplayUiBinder.class);

	interface ReSpecThPropertyDisplayUiBinder extends UiBinder<Widget, ReSpecThCommonPropertyDisplay> {
	}

	XMLParseResource resource = GWT.create(XMLParseResource.class);
	
	String initialcompositionS = "Initial Composition";
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
	@UiField
	MaterialLabel initialcomponent;
	@UiField
	MaterialLabel species;
	@UiField
	MaterialLabel amount;
	@UiField
	MaterialLabel units;
	@UiField
	MaterialRow propertyrow;
	@UiField
	MaterialRow componentrow;
	@UiField
	MaterialRow componentsrow;
	@UiField
	HTMLPanel components;
	
	ReSpecThProperty property;
	
	public ReSpecThCommonPropertyDisplay() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public ReSpecThCommonPropertyDisplay(String descr) {
		initWidget(uiBinder.createAndBindUi(this));
		description.setText(descr);
	}

	public void fill(ReSpecThProperty property) {
		this.property = property;
		componentsrow.setVisible(false);
		propertyrow.setVisible(false);
		componentrow.setVisible(false);
		descriptionrow.setVisible(false);
		if(property.getDescription().length() != 0) {
			description.setText(property.getDescription());
			descriptionrow.setVisible(true);
		}
		if(!property.isInitialComponents()) {
			propertyrow.setVisible(true);
			String name = property.getName() + " (" + property.getLabel() + ")";
			parametername.setText(name);
			parametervalue.setText(property.getValue());
			parameterunits.setText(property.getUnits());
		} else {
			componentsrow.setVisible(true);
			ArrayList<ReSpecThComponent> componentlst = property.getComponents();
			for(ReSpecThComponent component: componentlst) {
				ReSpecThCommonPropertyDisplay display = new ReSpecThCommonPropertyDisplay();
				display.fillComponent(component);
				components.add(display);
			}
		}
	}

	public void fillComponent(ReSpecThComponent component) {
		componentsrow.setVisible(false);
		propertyrow.setVisible(false);
		descriptionrow.setVisible(false);
		componentrow.setVisible(true);
		initialcomponent.setText(resource.component());
		species.setText(component.getSpeciesLink());
		amount.setText(component.getAmount());
		units.setText(component.getAmountunits());
	}
	public void setText(String text) {
		description.setText(text);
	}
	public String getText() {
		return description.getText();
	}

}
