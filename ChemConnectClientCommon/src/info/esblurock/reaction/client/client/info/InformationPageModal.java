package info.esblurock.reaction.client.client.info;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialModal;
import gwt.material.design.client.ui.MaterialTitle;

public class InformationPageModal extends Composite implements HasText {

	private static InformationPageModalUiBinder uiBinder = GWT.create(InformationPageModalUiBinder.class);

	interface InformationPageModalUiBinder extends UiBinder<Widget, InformationPageModal> {
	}

	public InformationPageModal() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	HorizontalPanel information;
	@UiField
	MaterialTitle infotitle;
	@UiField
	MaterialButton close;
	@UiField
	MaterialModal infomodal;

	public InformationPageModal(String title, String description, String info) {
		initWidget(uiBinder.createAndBindUi(this));
		infotitle.setTitle(title);
		infotitle.setDescription(description);
		HTML html = new HTML(info);
		information.add(html);
	}

	@UiHandler("close")
	public void close(ClickEvent event) {
		infomodal.closeModal();
	}
	public void showModal() {
		infomodal.openModal();
	}
	public void setText(String title) {
		infotitle.setTitle(title);
	}

	public String getText() {
		return infotitle.getTitle();
	}

}
