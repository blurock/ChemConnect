package info.esblurock.reaction.client.panel.data;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.constants.ModalType;
import gwt.material.design.client.ui.MaterialCard;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialModal;
import gwt.material.design.client.ui.MaterialModalContent;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialTitle;
import gwt.material.design.client.ui.MaterialToast;
import info.esblurock.reaction.client.async.TransactionService;
import info.esblurock.reaction.client.async.TransactionServiceAsync;
import info.esblurock.reaction.data.DatabaseObject;
import info.esblurock.reaction.data.store.UserObjectStorage;

public class BaseDataPresentation extends Composite implements HasText {

	private static BaseDataPresentationUiBinder uiBinder = GWT.create(BaseDataPresentationUiBinder.class);

	interface BaseDataPresentationUiBinder extends UiBinder<Widget, BaseDataPresentation> {
	}

	@UiField
	MaterialTitle title;
	@UiField
	MaterialModalContent modalcontent;
	@UiField
	MaterialModal modal;
	@UiField
	MaterialLink close;
	@UiField
	MaterialLink basket;
	@UiField
	HTMLPanel panel;
	@UiField
	MaterialCard savemodal;
	@UiField
	MaterialTextBox prefix;
	@UiField
	MaterialTextBox type;
	@UiField
	MaterialTextBox postfix;
	@UiField
	MaterialLabel pathtitle;
	@UiField
	MaterialLink saveclose;
	@UiField
	MaterialLink saveok;

	
	DatabaseObject object;
	String description;
	
	public BaseDataPresentation() {
		initWidget(uiBinder.createAndBindUi(this));
		init();
	}

	public BaseDataPresentation(String text, String description, DatabaseObject object) {
		initWidget(uiBinder.createAndBindUi(this));
		this.description = description;
		title.setTitle(text);
		title.setDescription(description);
		this.object = object;
		init();
	}

	private void init() {
		//basket.setText("Save");
		//basket.setIconType(IconType.SAVE);
		//close.setText("Close");
		//close.setIconType(IconType.CLOSE);
		pathtitle.setText("Set up save path (comma delimited), merged prefix,type and postfix");
		//prefix.setPlaceholder("prefix path (comma delimited)");
		//type.setPlaceholder("type of object");
		//prefix.setPlaceholder("postfix path (comma delimited)");
		setPath("prefix", "object","postfix");
		savemodal.setVisible(false);
	}

	@UiHandler("close")
	void onClick(ClickEvent e) {
		modal.closeModal();
	}
	@UiHandler("basket")
	void onSaveClick(ClickEvent e) {
		
		MaterialToast.fireToast("Save: " + buildPath());
		savemodal.setVisible(true);
	}

	public void openModal() {
		modal.openModal();
	}
	public void openModal(ModalType type) {
		modal.setType(type);
		modal.openModal();
	}
	
	public void setText(String text) {
		title.setTitle(text);
	}

	public String getText() {
		return title.getTitle();
	}
	public MaterialModalContent getModalContent() {
		return modalcontent;
	}
	public HTMLPanel getPanel() {
		return panel;
	}
	public void setPath(String prefix, String type, String postfix) {
		this.prefix.setText(prefix);
		this.type.setText(type);
		this.postfix.setText(postfix);
	}
	public String buildPath() {
		StringBuilder build = new StringBuilder();
		if(prefix.getText().length() > 0) {
			build.append(prefix.getText());
			build.append(",");
		}
		build.append(type.getText());
		if(postfix.getText().length() > 0) {
			build.append(",");
			build.append(postfix.getText());
		}
		return build.toString();
	}
	public UserObjectStorage buildUserObjectStorage() {
		Window.alert("BaseDataPresentation: buildUserObjectStorage: " + description);
		UserObjectStorage store = new UserObjectStorage(null,
				prefix.getText(),type.getText(),postfix.getText(),
				description,
				object.getKey(),
				object.getClass().getName());
		return store;
	}
	@UiHandler("saveclose")
	public void saveClose(ClickEvent e) {
		savemodal.setVisible(false);
		MaterialToast.fireToast("Not saved");
	}
	@UiHandler("saveok")
	public void saveOK(ClickEvent e) {
		savemodal.setVisible(false);
		MaterialToast.fireToast("Save: " + buildPath());
		UserObjectStorage store = buildUserObjectStorage();
		StoreUserObjectStorageCallback callback = new StoreUserObjectStorageCallback();
		TransactionServiceAsync async = TransactionService.Util.getInstance();
		async.storeUserObjectStorage(store,callback);
	}
	
}
