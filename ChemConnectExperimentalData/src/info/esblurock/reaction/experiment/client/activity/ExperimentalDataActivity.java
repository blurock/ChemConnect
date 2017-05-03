package info.esblurock.reaction.experiment.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import info.esblurock.reaction.client.activity.ClientFactory;
import info.esblurock.reaction.client.ui.view.ExperimentalDataView;
import info.esblurock.reaction.experiment.client.activity.place.ExperimentalDataPlace;

public class ExperimentalDataActivity  extends AbstractActivity implements ExperimentalDataView.Presenter {
	private ClientFactory clientFactory;
	private String name;

	
	public ExperimentalDataActivity(ExperimentalDataPlace place, ClientFactory clientFactory) {
		super();
		this.clientFactory = clientFactory;
		this.name = place.getHelloName();
	}


	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		ExperimentalDataView experimentalDataView = clientFactory.getExperimentalDataView();
		experimentalDataView.setName(name);
		experimentalDataView.setPresenter(this);
		panel.setWidget(experimentalDataView.asWidget());
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
