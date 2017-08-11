package info.esblurock.reaction.contactinput.client.mvp;


import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import info.esblurock.reaction.contactinput.client.activity.ClientFactory;
import info.esblurock.reaction.contactinput.client.activity.OrganizationDataActivity;
import info.esblurock.reaction.contactinput.client.activity.place.OrganizationDataPlace;

public class AppActivityMapper implements ActivityMapper {

	private ClientFactory clientFactory;

	/**
	 * AppActivityMapper associates each Place with its corresponding
	 * {@link Activity}
	 * 
	 * @param clientFactory
	 *            Factory to be passed to activities
	 */
	public AppActivityMapper(ClientFactory clientFactory) {
		super();
		this.clientFactory = clientFactory;
	}

	/**
	 * Map each Place to its corresponding Activity. This would be a great use
	 * for GIN.
	 */
	@Override
	public Activity getActivity(Place place) {
		if (place instanceof OrganizationDataPlace) {
			return new OrganizationDataActivity((OrganizationDataPlace) place, clientFactory);
		}
	
		return null;
	}
}