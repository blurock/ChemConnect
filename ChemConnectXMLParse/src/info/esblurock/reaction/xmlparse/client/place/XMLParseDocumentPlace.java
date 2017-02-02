package info.esblurock.reaction.xmlparse.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class XMLParseDocumentPlace  extends Place {
	private String helloName;

	public XMLParseDocumentPlace(String token) {
		this.helloName = token;
	}
	public String getHelloName() {
		return helloName;
	}

	public static class Tokenizer implements PlaceTokenizer<XMLParseDocumentPlace> {
		@Override
		public String getToken(XMLParseDocumentPlace place) {
			return place.getHelloName();
		}
		@Override
		public XMLParseDocumentPlace  getPlace(String token)  {
			return new XMLParseDocumentPlace(token);
		}
	}
}
