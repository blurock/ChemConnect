package info.esblurock.reaction.networks.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import info.esblurock.reaction.client.ui.view.NetworkVisualizationView;
import info.esblurock.reaction.networks.client.network.ForceGraph;

public class NetworkVisualizationImpl extends Composite implements NetworkVisualizationView  {

	private static NetworkVisualizationImplUiBinder uiBinder = GWT.create(NetworkVisualizationImplUiBinder.class);

	interface NetworkVisualizationImplUiBinder extends UiBinder<Widget, NetworkVisualizationImpl> {
	}

	public NetworkVisualizationImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		ForceGraph graph = new ForceGraph("your");
		toppanel.add(graph);
		
	}
	Presenter listener;
	String name;

	@UiField
	Button button;
	@UiField
	HTMLPanel toppanel;
	
	public NetworkVisualizationImpl(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		button.setText(firstName);
		
		ForceGraph graph = new ForceGraph("Mine");
		toppanel.add(graph);
	}

	@UiHandler("button")
	void onClick(ClickEvent e) {
		Window.alert("Hello!");
	}

	public void setText(String text) {
	}

	public String getText() {
		return null;
	}
	@Override
	public void setPresenter(Presenter listener) {
		this.listener = listener;
	}

	@Override
	public void setName(String helloName) {
	}

}
