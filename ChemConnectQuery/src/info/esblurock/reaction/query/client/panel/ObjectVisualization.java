package info.esblurock.reaction.query.client.panel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialCollapsibleBody;
import gwt.material.design.client.ui.MaterialLink;

public class ObjectVisualization extends Composite implements HasText {

	private static ObjectVisualizationUiBinder uiBinder = GWT.create(ObjectVisualizationUiBinder.class);

	interface ObjectVisualizationUiBinder extends UiBinder<Widget, ObjectVisualization> {
	}

	@UiField
	MaterialLink visualizationheader;
	@UiField
	MaterialCollapsibleBody body;

	public ObjectVisualization() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public ObjectVisualization(String title, Widget widget) {
		initWidget(uiBinder.createAndBindUi(this));
		visualizationheader.setText(title);
		body.add(widget);
	}

	public void setText(String text) {
		visualizationheader.setText(text);
	}

	public String getText() {
		return visualizationheader.getText();
	}

}
