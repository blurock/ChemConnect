package info.esblurock.reaction.xmlparse.client.ui;

import info.esblurock.reaction.client.activity.ClientFactory;
import info.esblurock.reaction.client.ui.top.ReactionInformationImpl;
import info.esblurock.reaction.client.ui.view.ReactionFirstView;
import info.esblurock.reaction.client.ui.view.ReactionInformationView;
import info.esblurock.reaction.client.ui.view.ReactionLoginValidationView;
import info.esblurock.reaction.client.ui.view.ReactionQueryView;
import info.esblurock.reaction.client.ui.view.ReactionTopView;
import info.esblurock.reaction.client.ui.view.XMLParseDocumentView;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;

public class ClientFactoryImpl implements ClientFactory {
	private final EventBus eventBus = new SimpleEventBus();
	private final PlaceController placeController = new PlaceController(eventBus);
	//private final ReactionTopView reactionTopView = new ReactionTopImpl();
	//private final ReactionFirstView reactionFirstView = new ReactionFirstImpl();
	//private final ReactionQueryView reactionQueryView = new ReactionQueryImpl();
	//private final ReactionLoginValidationView reactionLoginValidationView = new ReactionLoginValidationImpl();
	private final ReactionInformationView reactionInformationView = new ReactionInformationImpl();
	private final XMLParseDocumentView xmlParseDocumentView = new XMLParseDocumentImpl();

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}
	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}
	@Override
	public ReactionTopView getReactionTopView() {
		return null;
	}
	@Override
	public ReactionFirstView getReactionFirstView() {
		return null;
	}
	@Override
	public ReactionQueryView getReactionQueryView() {
		return null;
	}
	@Override
	public ReactionLoginValidationView getReactionLoginValidationView() {
		return null;
	}
	@Override
	public ReactionInformationView getReactionInformationView() {
		return reactionInformationView;
	}
	@Override
	public XMLParseDocumentView getXMLParseDocumentView() {
		return xmlParseDocumentView;
	}

}
