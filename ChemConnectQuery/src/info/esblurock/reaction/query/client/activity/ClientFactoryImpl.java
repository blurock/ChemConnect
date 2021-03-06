package info.esblurock.reaction.query.client.activity;

import info.esblurock.reaction.client.activity.ClientFactory;

import info.esblurock.reaction.client.ui.top.ReactionInformationImpl;
import info.esblurock.reaction.client.ui.top.ReactionTopImpl;
import info.esblurock.reaction.query.client.ui.ReactionQueryImpl;
import info.esblurock.reaction.client.ui.view.ExperimentalDataView;
import info.esblurock.reaction.client.ui.view.NetworkVisualizationView;
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
	private final ReactionTopView reactionTopView = new ReactionTopImpl();
	//private final ReactionFirstView reactionFirstView = new ReactionFirstImpl();
	private final ReactionQueryView reactionQueryView = new ReactionQueryImpl();
	//private final ReactionLoginValidationView reactionLoginValidationView = new ReactionLoginValidationImpl();
	private final ReactionInformationView reactionInformationView = new ReactionInformationImpl();

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
		return reactionTopView;
	}
	@Override
	public ReactionFirstView getReactionFirstView() {
		return null;
		//return reactionFirstView;
	}
	@Override
	public ReactionQueryView getReactionQueryView() {
		return reactionQueryView;
	}
	@Override
	public ReactionLoginValidationView getReactionLoginValidationView() {
		return null;
		//return reactionLoginValidationView;
	}
	@Override
	public ReactionInformationView getReactionInformationView() {
		return reactionInformationView;
	}
	@Override
	public XMLParseDocumentView getXMLParseDocumentView() {
		return null;
	}
	@Override
	public ExperimentalDataView getExperimentalDataView() {
		return null;
	}
	@Override
	public NetworkVisualizationView getNetworkVisualizationView() {
		// TODO Auto-generated method stub
		return null;
	}


}
