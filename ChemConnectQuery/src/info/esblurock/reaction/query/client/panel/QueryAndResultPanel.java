package info.esblurock.reaction.query.client.panel;

import info.esblurock.reaction.client.async.ReactionSearchService;
import info.esblurock.reaction.client.async.ReactionSearchServiceAsync;
import info.esblurock.reaction.client.ui.login.UiImplementationBase;
import info.esblurock.reaction.client.ui.view.ReactionQueryView.Presenter;
import info.esblurock.reaction.data.UserDTO;
import info.esblurock.reaction.data.store.UserObjectStorage;
import gwt.material.design.client.ui.MaterialCollapsible;
import gwt.material.design.client.ui.MaterialPanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class QueryAndResultPanel extends UiImplementationBase implements HasText {
	

	private static QueryAndResultPanelUiBinder uiBinder = GWT
			.create(QueryAndResultPanelUiBinder.class);

	interface QueryAndResultPanelUiBinder extends
			UiBinder<Widget, QueryAndResultPanel> {
	}
	
	@UiField
	MaterialCollapsible topoftree;
	@UiField
	MaterialPanel toppanel;
	@UiField
	VisualizationPanel visualizepanel;
	@UiField
	MaterialPanel objectsave;
	
	String text;
	Presenter listener;
	
	public QueryAndResultPanel() {
		initWidget(uiBinder.createAndBindUi(this));
		init("Search");
	}
	public QueryAndResultPanel(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		init(firstName);
	}
	private void init(String text) {
		UserStorageObjectWindow objwindow = new UserStorageObjectWindow(this);
		objectsave.add(objwindow);
	}
	public MaterialCollapsible getQueryTop() {
		return topoftree;
	}
	public MaterialPanel getTopPanel() {
		return toppanel;
	}
	
	@Override
	public void setText(String text) {
		this.text = text;
	}

	
	@Override
	public String getText() {
		return this.text;
	}
	public void setPresenter(Presenter listener) {
		this.listener = listener;
	}
	@Override
	public void setUser(UserDTO user) {
		super.setUser(user);
	}
	public void displayItem(UserObjectStorage store, String visualizationPanelS) {
		ReactionSearchServiceAsync async = ReactionSearchService.Util.getInstance();
		DisplayUserObjectCallback callback = new DisplayUserObjectCallback(this,visualizationPanelS);
		async.getObjectFromUserObjectStorage(store, callback);
	}
	public void addVisualization(Widget widget, String visualizationPanelS) {
		
	}
	public void addDisplayItem(String title, Widget widget) {
		visualizepanel.addDisplayObject(title,widget);
	}
}
