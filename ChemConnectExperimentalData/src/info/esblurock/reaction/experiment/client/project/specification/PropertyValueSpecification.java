package info.esblurock.reaction.experiment.client.project.specification;

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
import gwt.material.design.client.ui.MaterialTextBox;

public class PropertyValueSpecification extends Composite implements HasText {

	private static PropertyValueSpecificationUiBinder uiBinder = GWT.create(PropertyValueSpecificationUiBinder.class);

	interface PropertyValueSpecificationUiBinder extends UiBinder<Widget, PropertyValueSpecification> {
	}


	@UiField
	MaterialLink specification;
	@UiField
	MaterialTextBox property;
	@UiField
	MaterialTextBox value;

	public PropertyValueSpecification() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public PropertyValueSpecification(String text) {
		initWidget(uiBinder.createAndBindUi(this));
		init(text);
	}
	
	private void init(String text) {
		specification.setText(text);
		property.setPlaceholder("property");
		value.setPlaceholder("value");		
	}

	public void setText(String text) {
		specification.setText(text);
	}

	public String getText() {
		return specification.getText();
	}

}
