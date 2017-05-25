package info.esblurock.reaction.client.activity.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class NetworkVisualizationPlace extends Place {
	private String token;

	public NetworkVisualizationPlace(String token) {
		super();
		this.token = token;
	}
	
	public String getHelloName()
	{
		return token;
	}
	public static class Tokenizer implements PlaceTokenizer<NetworkVisualizationPlace> {
		@Override
		public String getToken(NetworkVisualizationPlace place) {
			return place.getHelloName();
		}

		@Override
		public NetworkVisualizationPlace getPlace(String token)  {
			return new NetworkVisualizationPlace(token);
		}
		
	}

}
