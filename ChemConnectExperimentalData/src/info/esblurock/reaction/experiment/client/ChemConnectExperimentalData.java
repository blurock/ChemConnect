package info.esblurock.reaction.experiment.client;

import info.esblurock.reaction.client.activity.ClientFactory;
import info.esblurock.reaction.client.async.LoginService;
import info.esblurock.reaction.client.async.LoginServiceAsync;
import info.esblurock.reaction.client.async.StoreDescriptionData;
import info.esblurock.reaction.client.async.StoreDescriptionDataAsync;
import info.esblurock.reaction.client.mvp.AppActivityMapper;
import info.esblurock.reaction.client.mvp.AppPlaceHistoryMapper;
import info.esblurock.reaction.experiment.client.activity.place.ExperimentalDataPlace;
import info.esblurock.reaction.experiment.client.ui.ExperimentalDataImpl;


import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ChemConnectExperimentalData implements EntryPoint {

	private SimplePanel appWidget = new SimplePanel();
	private Place defaultPlace = new ExperimentalDataPlace("Experiment");
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
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
		
		Window.alert("StoreDescriptionData 1");
		StoreDescriptionDataAsync asyncStore = StoreDescriptionData.Util.getInstance();
		Window.alert("StoreDescriptionData 2");
		DataInitializationCallback callbackStore = new DataInitializationCallback();
		Window.alert("StoreDescriptionData 3");
		asyncStore.initializeDatabaseObjects(callbackStore);
		Window.alert("StoreDescriptionData 4");

		
		Window.alert("ExperimentalDataImpl 1");
		ExperimentalDataImpl impl = new ExperimentalDataImpl();
		Window.alert("ExperimentalDataImpl 2");
		RootPanel.get().add(impl);
		Window.alert("ExperimentalDataImpl 3");
		historyHandler.handleCurrentHistory();
		Window.alert("ExperimentalDataImpl 4");
		
		Window.alert("LoginService 1");
		LoginServiceAsync async = LoginService.Util.getInstance();
		SimpleLoginCallback callback = new SimpleLoginCallback();
		async.loginServer("Administration", "laguna", callback);
	}
}
