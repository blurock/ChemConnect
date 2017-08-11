package info.esblurock.reaction.contactinput.client.activity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;

import info.esblurock.reaction.contactinput.client.ui.OrganizationDataImpl;
import info.esblurock.reaction.contactinput.client.view.OrganizationDataView;


public class ClientFactoryImpl implements ClientFactory {
	private final EventBus eventBus = new SimpleEventBus();
	@SuppressWarnings("deprecation")
	private final PlaceController placeController = new PlaceController(eventBus);
	private final OrganizationDataView organizationDataView = new OrganizationDataImpl();

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}
	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}
	@Override
	public OrganizationDataView getOrganizationDataView() {
		return organizationDataView;
	}

}
