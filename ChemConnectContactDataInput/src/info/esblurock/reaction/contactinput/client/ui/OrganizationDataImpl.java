package info.esblurock.reaction.contactinput.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import info.esblurock.reaction.contactinput.client.view.OrganizationDataView;

public class OrganizationDataImpl extends Composite implements OrganizationDataView {

	private static OrganizationDataImplUiBinder uiBinder = GWT.create(OrganizationDataImplUiBinder.class);

	interface OrganizationDataImplUiBinder extends UiBinder<Widget, OrganizationDataImpl> {
	}

	Presenter listener;
	
	public OrganizationDataImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	

	public OrganizationDataImpl(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}


	public void setText(String text) {
	}

	public String getText() {
		return null;
	}

	@Override
	public void setName(String name) {
	}

	@Override
	public void setPresenter(Presenter listener) {
		this.listener = listener;
	}

}
