package info.esblurock.reaction.experiment.client.activity.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ExperimentalDataPlace extends Place {
	private String placeName;

	public ExperimentalDataPlace(String token) {
		this.placeName = token;
	}
	public String getHelloName() {
		return placeName;
	}

	public static class Tokenizer implements PlaceTokenizer<ExperimentalDataPlace> {

		@Override
		public ExperimentalDataPlace getPlace(String token) {
			return new ExperimentalDataPlace(token);
		}

		@Override
		public String getToken(ExperimentalDataPlace place) {
			return place.getHelloName();
		}
		
	}
}
