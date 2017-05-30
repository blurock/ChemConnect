package info.esblurock.reaction.experiment.client.project;

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

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialModal;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;

public class AskForItemName extends Composite implements HasText {

	private static AskForItemNameUiBinder uiBinder = GWT.create(AskForItemNameUiBinder.class);

	interface AskForItemNameUiBinder extends UiBinder<Widget, AskForItemName> {
	}

	public static String delimitorS = "#";
	
	@UiField
	MaterialTextBox source;
	@UiField
	MaterialTextBox type;
	@UiField
	MaterialTextBox subtype;
	@UiField
	MaterialTextBox name;
	@UiField
	MaterialButton submit;
	@UiField
	MaterialButton close;
	@UiField
	MaterialModal modal;
	
	ResultsForAskForName askforname;

	public AskForItemName(String type, ResultsForAskForName askforname) {
		initWidget(uiBinder.createAndBindUi(this));
		this.type.setText(type);
		init(askforname);
	}
	public AskForItemName(String source, String type, ResultsForAskForName askforname) {
		initWidget(uiBinder.createAndBindUi(this));
		this.type.setText(type);
		this.source.setText(source);
	}

	void init(ResultsForAskForName askforname) {
		askforname.initialize(this);
		this.askforname = askforname;
		source.setPlaceholder("Source");
		type.setPlaceholder("Item Class");
		subtype.setPlaceholder("Subtype");
		name.setPlaceholder("Name");
		source.setText("");
		type.setText("");
		subtype.setText("");
		name.setText("");
	}
	
	public boolean fullNameGiven() {
		boolean ans = true;
		if(source.getText().length() == 0) {
			ans = false;
			MaterialToast.fireToast(source.getPlaceholder() + " not given");
		}
		if(type.getText().length() == 0) {
			ans = false;
			MaterialToast.fireToast(type.getPlaceholder() + " not given");
		}
		if(subtype.getText().length() == 0) {
			ans = false;
			MaterialToast.fireToast(subtype.getPlaceholder() + " not given");
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
