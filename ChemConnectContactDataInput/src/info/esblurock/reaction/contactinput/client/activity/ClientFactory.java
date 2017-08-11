package info.esblurock.reaction.contactinput.client.activity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;

import info.esblurock.reaction.contactinput.client.view.OrganizationDataView;

public interface ClientFactory {
	public EventBus getEventBus();
	public PlaceController getPlaceController();
	public OrganizationDataView getOrganizationDataView();
}
