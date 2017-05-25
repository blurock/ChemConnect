package info.esblurock.reaction.experiment.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialCollapsible;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialNavBrand;
import gwt.material.design.client.ui.MaterialToast;
import info.esblurock.reaction.client.client.info.InformationPageModal;
import info.esblurock.reaction.client.ui.view.ExperimentalDataView;
import info.esblurock.reaction.experiment.client.project.AskForItemName;
import info.esblurock.reaction.experiment.client.project.ExperimentalProjectTop;
import info.esblurock.reaction.experiment.client.project.items.ChooseExperimentalCollapsibleItem;
import info.esblurock.reaction.experiment.client.project.items.ExperimentalApparatusTemplatesCollapsibleItem;
import info.esblurock.reaction.experiment.client.ui.resources.ChemConnectExperimentFiles;
import info.esblurock.reaction.experiment.client.ui.resources.ChemConnectExperimentKeys;

public class ExperimentalDataImpl extends Composite implements ExperimentalDataView {

	private static ExperimentalDataImplUiBinder uiBinder = GWT.create(ExperimentalDataImplUiBinder.class);

	interface ExperimentalDataImplUiBinder extends UiBinder<Widget, ExperimentalDataImpl> {
	}

	ChemConnectExperimentKeys resource = GWT.create(ChemConnectExperimentKeys.class);
	ChemConnectExperimentFiles infofiles = GWT.create(ChemConnectExperimentFiles.class);
	
	@UiField
	HTMLPanel modalpanel;
	@UiField
	MaterialNavBrand toptext;
	@UiField
	MaterialLink info;
	@UiField
	MaterialLink addProject;
	@UiField
	MaterialLink searchProject;
	@UiField
	MaterialCollapsible projectList;
	@UiField
	MaterialCollapsible experimentItemList;
	
	Presenter listener;
	ExperimentalApparatusTemplatesCollapsibleItem apparatus;
	
	public ExperimentalDataImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		toptext.setText("Project");
		init();
	}

	public ExperimentalDataImpl(String name) {
		initWidget(uiBinder.createAndBindUi(this));
		init();
	}

	void init() {
		toptext.setText(resource.experimentaldataproject());
		apparatus = new ExperimentalApparatusTemplatesCollapsibleItem(this);
		experimentItemList.add(apparatus);
	}
	public ExperimentalApparatusTemplatesCollapsibleItem getApparatusTemplates() {
		return apparatus;
	}
	
	@UiHandler("info")
	public void infoModal(ClickEvent event) {
		String title = resource.projecttoptitle();
		String description = resource.projecttopdescription();
		String info = infofiles.projectInformation().getText();
		startInformationModal(title, description, info);
	}
	@UiHandler("addProject")
	public void start(ClickEvent event) {
		ExperimentalProjectTop project = new ExperimentalProjectTop(this);
		projectList.add(project);
	}
	@UiHandler("searchProject")
	public void find(ClickEvent event) {
		MaterialToast.fireToast("Find an existing project");
	}
	
	public void startInformationModal(String title, String description, String info) {
		InformationPageModal infomodal = new InformationPageModal(title, description, info);
		modalpanel.clear();
		modalpanel.add(infomodal);
		infomodal.showModal();		
	}
	public HTMLPanel getModalPanel() {
		return modalpanel;
	}
	public void setText(String text) {
		toptext.setText(text);
	}

	public String getText() {
		return toptext.getText();
	}

	@Override
	public void setName(String name) {
		setText(name);
	}

	@Override
	public void setPresenter(Presenter listener) {
		this.listener = listener;
	}

}
