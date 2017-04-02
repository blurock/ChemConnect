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

import gwt.material.design.client.ui.MaterialCollapsible;
import gwt.material.design.client.ui.MaterialPanel;

public class VisualizationPanel extends Composite implements HasText {

	private static VisualizationPanelUiBinder uiBinder = GWT.create(VisualizationPanelUiBinder.class);

	interface VisualizationPanelUiBinder extends UiBinder<Widget, VisualizationPanel> {
	}

	public VisualizationPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	MaterialCollapsible collapsible;
	@UiField
	MaterialPanel toppanel;

	public VisualizationPanel(String title) {
		initWidget(uiBinder.createAndBindUi(this));
		toppanel.setTitle(title);
	}

	public void addDisplayObject(String title, Widget widget) {
		ObjectVisualization visual = new ObjectVisualization(title,widget);
		collapsible.add(visual);
	}

	public void setText(String text) {
		toppanel.setTitle(text);
	}

	public String getText() {
		return toppanel.getTitle();
	}

}
