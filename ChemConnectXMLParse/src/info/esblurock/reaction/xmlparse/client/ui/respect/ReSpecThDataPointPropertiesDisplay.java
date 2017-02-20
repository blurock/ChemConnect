package info.esblurock.reaction.xmlparse.client.ui.respect;
import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialLink;
import info.esblurock.reaction.data.chemical.respect.ReSpecThProperty;
import info.esblurock.reaction.xmlparse.resources.XMLParseResource;

public class ReSpecThDataPointPropertiesDisplay extends Composite implements HasText {

	private static ReSpecThDataPointPropertiesDisplayUiBinder uiBinder = GWT
			.create(ReSpecThDataPointPropertiesDisplayUiBinder.class);

	interface ReSpecThDataPointPropertiesDisplayUiBinder extends UiBinder<Widget, ReSpecThDataPointPropertiesDisplay> {
	}

	XMLParseResource resource = GWT.create(XMLParseResource.class);
	
	@UiField
	MaterialLink datapointslabel;
	@UiField
	HTMLPanel datapoints;

	ArrayList<ReSpecThProperty> dataGroupProperties;
	
	public ReSpecThDataPointPropertiesDisplay() {
		initWidget(uiBinder.createAndBindUi(this));
		init();
	}

	void init() {
		datapointslabel.setText(resource.datapoints());
	}
	
	public void fill(ArrayList<ReSpecThProperty> dataGroupProperties) {
		this.dataGroupProperties = dataGroupProperties;
		for(ReSpecThProperty property : dataGroupProperties) {
			ReSpecThPlotAxisDisplay display = new ReSpecThPlotAxisDisplay();
			display.fill(property);
			datapoints.add(display);
		}
	}
	public void setText(String text) {
		datapointslabel.setText(text);
	}

	public String getText() {
		return datapointslabel.getText();
	}

}
