package info.esblurock.reaction.xmlparse.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import info.esblurock.reaction.client.activity.ClientFactory;
import info.esblurock.reaction.client.ui.view.XMLParseDocumentView;
import info.esblurock.reaction.xmlparse.client.place.XMLParseDocumentPlace;

public class XMLParseDocumentActivity extends AbstractActivity implements XMLParseDocumentView.Presenter {
	private ClientFactory clientFactory;
	private String name;

	public XMLParseDocumentActivity(XMLParseDocumentPlace place, ClientFactory clientFactory) {
		this.name = place.getHelloName();
		this.clientFactory = clientFactory;
	}
	
	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		XMLParseDocumentView xmlParseDocumentView = clientFactory.getXMLParseDocumentView();
		xmlParseDocumentView.setName(name);
		xmlParseDocumentView.setPresenter(this);
		containerWidget.setWidget(xmlParseDocumentView.asWidget());
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
