package info.esblurock.reaction.xmlparse.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialCollapsible;
import gwt.material.design.client.ui.MaterialLink;
import info.esblurock.reaction.xmlparse.client.xmlfiles.XMLDataSource;

public class ReSpectExperimentCategory extends Composite implements HasText {

	private static ReSpectExperimentCategoryUiBinder uiBinder = GWT.create(ReSpectExperimentCategoryUiBinder.class);

	interface ReSpectExperimentCategoryUiBinder extends UiBinder<Widget, ReSpectExperimentCategory> {
	}

	public ReSpectExperimentCategory() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	MaterialLink label;
	@UiField
	MaterialCollapsible experiments;

	XMLParseDocumentImpl top;
	
	public ReSpectExperimentCategory(String name, XMLParseDocumentImpl top) {
		initWidget(uiBinder.createAndBindUi(this));
		label.setText(name);
		this.top = top;
	}

	public void setText(String text) {
		label.setText(text);
	}

	public String getText() {
		return label.getText();
	}

	public void addExperiment(XMLDataSource display) {
		String name = display.getText();
		int sze = experiments.getWidgetCount();
		int index = -1;
		double percent = 0.0;
		for(int i = 0; i<sze ; i++) {
			ReSpecThCommonPaperSet set = (ReSpecThCommonPaperSet) experiments.getWidget(i);
			double p = set.percentMatch(name);
			if(p > percent) {
				percent = p;
				index = i;
			}
		}
		if(percent > 0.25) {
			ReSpecThCommonPaperSet set = (ReSpecThCommonPaperSet) experiments.getWidget(index);
			set.addExperiment(display);
		} else {
			ReSpecThCommonPaperSet newset = new ReSpecThCommonPaperSet(display.getText(), top);
			experiments.add(newset);
			newset.addExperiment(display);
		}
	}

}
