package info.esblurock.reaction.contactinput.client.activity.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class OrganizationDataPlace extends Place {
	private String placeName;

	public OrganizationDataPlace(String placeName) {
		super();
		this.placeName = placeName;
	}

	public String getPlaceName() {
		return placeName;
	}
	
	public static class Tokenizer implements PlaceTokenizer<OrganizationDataPlace> {

		@Override
		public OrganizationDataPlace getPlace(String token) {
			return new OrganizationDataPlace(token);
		}

		@Override
		public String getToken(OrganizationDataPlace place) {
			return place.getPlaceName();
		}
		
	}
	

}
