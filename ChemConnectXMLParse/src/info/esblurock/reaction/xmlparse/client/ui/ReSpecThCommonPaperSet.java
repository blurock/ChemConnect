package info.esblurock.reaction.xmlparse.client.ui;

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

import gwt.material.design.client.ui.MaterialCollapsible;
import gwt.material.design.client.ui.MaterialLink;
import info.esblurock.reaction.xmlparse.client.xmlfiles.XMLDataSource;

public class ReSpecThCommonPaperSet extends Composite implements HasText {

	private static ReSpecThCommonPaperSetUiBinder uiBinder = GWT.create(ReSpecThCommonPaperSetUiBinder.class);

	interface ReSpecThCommonPaperSetUiBinder extends UiBinder<Widget, ReSpecThCommonPaperSet> {
	}

	public ReSpecThCommonPaperSet() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	MaterialLink label;
	@UiField
	MaterialCollapsible experiments;

	public ReSpecThCommonPaperSet(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		label.setText(firstName);
	}

	public double percentMatch(String name) {
		double sze = name.length();
		int index = 0;
		String l = label.getText();
		while(name.charAt(index) == l.charAt(index)) {
			index++;
		}
		double percent = (((double) index)/sze);
		return percent;
	}
	public void setText(String text) {
		label.setText(text);
	}

	public String getText() {
		return label.getText();
	}

	public void addExperiment(XMLDataSource display) {
		experiments.add(display);
	}

}
