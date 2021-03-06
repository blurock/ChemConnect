package info.esblurock.reaction.networks.client;

import info.esblurock.reaction.client.activity.ClientFactory;
import info.esblurock.reaction.client.activity.place.NetworkVisualizationPlace;
import info.esblurock.reaction.client.async.LoginService;
import info.esblurock.reaction.client.async.LoginServiceAsync;
import info.esblurock.reaction.client.mvp.AppActivityMapper;
import info.esblurock.reaction.client.mvp.AppPlaceHistoryMapper;
import info.esblurock.reaction.networks.client.network.JavaScriptCallEvent;
import info.esblurock.reaction.networks.client.ui.NetworkVisualizationImpl;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ChemConnectNetworks implements EntryPoint {

	private SimplePanel appWidget = new SimplePanel();
	private Place defaultPlace = new NetworkVisualizationPlace("Networks");

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		JavaScriptCallEvent.exportStaticMethod();
		
		ClientFactory clientFactory = GWT.create(ClientFactory.class);
		EventBus eventBus = clientFactory.getEventBus();
		PlaceController placeController = clientFactory.getPlaceController();
		
		// Start ActivityManager for the main widget with our ActivityMapper
		ActivityMapper activityMapper = new AppActivityMapper(clientFactory);
		ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
		activityManager.setDisplay(appWidget);

		// Start PlaceHistoryHandler with our PlaceHistoryMapper
		AppPlaceHistoryMapper historyMapper= GWT.create(AppPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);
		
		NetworkVisualizationImpl impl = new NetworkVisualizationImpl();
		RootPanel.get().add(impl);
		historyHandler.handleCurrentHistory();
		
		LoginServiceAsync async = LoginService.Util.getInstance();
		SimpleLoginCallback callback = new SimpleLoginCallback();
		async.loginServer("Administration", "laguna", callback);
		/*
		//Element parent = RootPanel.getBodyElement();
		ForceGraph graph = new ForceGraph("Mine");
		RootPanel.get().add(graph);
		*/
	}
}
