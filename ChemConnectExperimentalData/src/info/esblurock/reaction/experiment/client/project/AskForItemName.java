package info.esblurock.reaction.experiment.client.project;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialDropDown;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialModal;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;
import info.esblurock.reaction.client.async.StoreDescriptionData;
import info.esblurock.reaction.client.async.StoreDescriptionDataAsync;

public class AskForItemName extends Composite implements HasText {

	private static AskForItemNameUiBinder uiBinder = GWT.create(AskForItemNameUiBinder.class);

	interface AskForItemNameUiBinder extends UiBinder<Widget, AskForItemName> {
	}

	public static String delimitorS = "#";
	
	@UiField
	MaterialLink source;
	@UiField
	MaterialLink type;
	@UiField
	MaterialLink subtype;
	@UiField
	MaterialTextBox name;
	@UiField
	MaterialButton submit;
	@UiField
	MaterialButton close;
	@UiField
	MaterialModal modal;
	@UiField
	MaterialDropDown subtypedropdown;
	@UiField
	MaterialDropDown sourcedropdown;
	
	ResultsForAskForName askforname;

	public AskForItemName(String type, ResultsForAskForName askforname) {
		initWidget(uiBinder.createAndBindUi(this));
		init(askforname);
		this.type.setText(type);
	}
	public AskForItemName(String source, String type, ResultsForAskForName askforname) {
		initWidget(uiBinder.createAndBindUi(this));
		this.type.setText(type);
		this.source.setText(source);
	}

	void init(ResultsForAskForName askforname) {
		askforname.initialize(this);
		this.askforname = askforname;
		//source.setPlaceholder("Source");
		//type.setPlaceholder("Item Class");
		name.setPlaceholder("Name");
		//source.setText("");
		type.setText("");
		
		
		String defaultname = todaysdate();
		name.setText(defaultname);
		
		StoreDescriptionDataAsync async = GWT.create(StoreDescriptionData.class);
		ApparatusListCallback callback = new ApparatusListCallback(this);
		async.getIsAList("apparatus", callback);
		
		RepositoryListCallback repository = new RepositoryListCallback(this);
		String user = Cookies.getCookie("user");
		async.getUserRepositoryList(user,repository);
	}
	
	private String todaysdate() {
		Date date = new Date();
		DateTimeFormat fmt = DateTimeFormat.getFormat("yyyyMMdd");
		return fmt.format( date );
	}
	public void addDropDownElement(String element) {
		MaterialLink link = new MaterialLink(element);
		link.setTextColor("black");
		link.setStyleName("dropdownlist");
		subtypedropdown.add(link);
	}
	public void addDropDownRepositoryElement(String element) {
		MaterialLink link = new MaterialLink(element);
		link.setTextColor("black");
		link.setStyleName("dropdownlist");
		sourcedropdown.add(link);
		source.setText(element);
	}
	public boolean fullNameGiven() {
		boolean ans = true;
		if(source.getText().length() == 0) {
			ans = false;
		}
		if(type.getText().length() == 0) {
			ans = false;
		}
		if(name.getText().length() == 0) {
			ans = false;
			MaterialToast.fireToast(name.getPlaceholder() + " not given");
		}
		
		return ans;
	}
	
	public String getFullName() {
		String sourceS = source.getText();
		String typeS = type.getText();
		String subtypeS = subtype.getText();
		String nameS = name.getText();
		
		String fullname = sourceS + delimitorS 
				+ typeS + delimitorS
				+ subtypeS + delimitorS
				+ nameS;
		return fullname;
	}
	
	public void openModal() {
		modal.openModal();
	}
	public void closeModal() {
		modal.closeModal();
	}

	@UiHandler("subtypedropdown")
	void onDropdown(SelectionEvent<Widget> callback) {
		String selected = ((MaterialLink)callback.getSelectedItem()).getText();
		subtype.setText(selected);
	}
	@UiHandler("sourcedropdown")
	void onSourceDropdown(SelectionEvent<Widget> callback) {
		String selected = ((MaterialLink)callback.getSelectedItem()).getText();
		source.setText(selected);
	}

	@UiHandler("submit")
	void onSubmitClick(ClickEvent event) {
		if(askforname.setInResults()) {
			closeModal();
		}
	}

	@UiHandler("close")
	void onCloseClick(ClickEvent event) {
		closeModal();
	}
	public void setText(String text) {
		type.setText(text);
	}

	public String getText() {
		return type.getText();
	}
	public String getSource() {
		return source.getText();
	}
	public void setSource(String source) {
		this.source.setText(source);
	}
	public String getType() {
		return type.getText();
	}
	public void setType(String type) {
		this.type.setText(type);
	}
	public String getSubtype() {
		return subtype.getText();
	}
	public void setSubtype(String subtype) {
		this.subtype.setText(subtype);
	}
	public String getName() {
		return name.getText();
	}
	public void setName(String name) {
		this.name.setText(name);
	}
	public void freezeSource() {
		this.source.setEnabled(true);
	}
}
