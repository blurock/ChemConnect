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
	
	@UiField
	Button button;

	public OrganizationDataImpl(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		button.setText(firstName);
	}

	@UiHandler("button")
	void onClick(ClickEvent e) {
		Window.alert("Hello!");
	}

	public void setText(String text) {
		button.setText(text);
	}

	public String getText() {
		return button.getText();
	}

	@Override
	public void setName(String name) {
	}

	@Override
	public void setPresenter(Presenter listener) {
		this.listener = listener;
	}

}
