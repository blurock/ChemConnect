package info.esblurock.reaction.xmlparse.client.ui.respect;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialLink;
import info.esblurock.reaction.data.chemical.respect.ReSpecThProperty;
import info.esblurock.reaction.xmlparse.resources.XMLParseResource;

public class ReSpecThCommonPropertiesDisplay extends Composite implements HasText {

	private static ReSpecThCommonPropertiesDisplayUiBinder uiBinder = GWT
			.create(ReSpecThCommonPropertiesDisplayUiBinder.class);

	interface ReSpecThCommonPropertiesDisplayUiBinder extends UiBinder<Widget, ReSpecThCommonPropertiesDisplay> {
	}

	XMLParseResource resource = GWT.create(XMLParseResource.class);
	@UiField
	MaterialLink commonpropertylabel;
	@UiField
	HTMLPanel commonproperties;

	ArrayList<ReSpecThProperty> common;
	
	public ReSpecThCommonPropertiesDisplay() {
		initWidget(uiBinder.createAndBindUi(this));
		init();
	}
	void init() {
		commonpropertylabel.setText(resource.commonproperties());
	}
	public void setText(String text) {
		commonpropertylabel.setText(text);
	}

	public void fill(ArrayList<ReSpecThProperty> common) {
		this.common = common;
		for(ReSpecThProperty property: common) {
			ReSpecThCommonPropertyDisplay display = new ReSpecThCommonPropertyDisplay();
			display.fill(property);
			commonproperties.add(display);
		}
	}
	public String getText() {
		return commonpropertylabel.getText();
	}

}
