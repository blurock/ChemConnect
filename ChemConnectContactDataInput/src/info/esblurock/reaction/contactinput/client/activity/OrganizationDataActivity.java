package info.esblurock.reaction.contactinput.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import info.esblurock.reaction.contactinput.client.view.OrganizationDataView;
import info.esblurock.reaction.contactinput.client.activity.place.OrganizationDataPlace;

public class OrganizationDataActivity extends AbstractActivity implements OrganizationDataView.Presenter {

	private ClientFactory clientFactory;
	private String name;

	public OrganizationDataActivity(OrganizationDataPlace place, ClientFactory clientFactory) {
		super();
		this.clientFactory = clientFactory;
		this.name = place.getPlaceName();
		
	}
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		OrganizationDataView organizationDataView = clientFactory.getOrganizationDataView();
		organizationDataView.setName(name);
		organizationDataView.setPresenter(this);
		panel.setWidget(organizationDataView.asWidget());
	}

	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}

}
