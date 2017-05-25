package info.esblurock.reaction.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import info.esblurock.reaction.client.activity.place.NetworkVisualizationPlace;
import info.esblurock.reaction.client.ui.view.NetworkVisualizationView;

public class NetworkVisualizationActivity  extends AbstractActivity implements NetworkVisualizationView.Presenter {
	private ClientFactory clientFactory;
	private String name;

	public NetworkVisualizationActivity(NetworkVisualizationPlace place, ClientFactory clientFactory) {
		this.name = place.getHelloName();
		this.clientFactory = clientFactory;
	}

	/**
	 * Invoked by the ActivityManager to start a new Activity
	 */
	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		NetworkVisualizationView networkVisualizationView = clientFactory.getNetworkVisualizationView();
		networkVisualizationView.setName(name);
		networkVisualizationView.setPresenter(this);
		containerWidget.setWidget(networkVisualizationView.asWidget());
	}
	   @Override
	    public String mayStop() {
			return null;
	    }
		@Override
		public void goTo(Place place) {
			clientFactory.getPlaceController().goTo(place);
		}

}
