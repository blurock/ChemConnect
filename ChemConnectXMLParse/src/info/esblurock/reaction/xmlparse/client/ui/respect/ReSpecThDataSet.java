package info.esblurock.reaction.xmlparse.client.ui.respect;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCollapsible;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialModal;
import gwt.material.design.client.ui.MaterialToast;
import info.esblurock.reaction.client.async.TextToDatabase;
import info.esblurock.reaction.client.async.TextToDatabaseAsync;
import info.esblurock.reaction.client.panel.description.DataDescription;
import info.esblurock.reaction.client.panel.description.ReferenceDescriptions;
import info.esblurock.reaction.client.panel.description.SetOfReferenceDescriptions;
import info.esblurock.reaction.client.panel.inputs.SuccessfulRegistrationCallback;
import info.esblurock.reaction.data.chemical.respect.ReSpecTHXMLFileBase;
import info.esblurock.reaction.data.description.DataSetReference;
import info.esblurock.reaction.data.description.DescriptionDataData;
import info.esblurock.reaction.xmlparse.client.ui.respect.process.RegisterReSpecThDataCallback;
import info.esblurock.reaction.xmlparse.client.xmlfiles.XMLDataSource;
import info.esblurock.reaction.xmlparse.resources.XMLParseResource;

public class ReSpecThDataSet extends Composite implements HasText {

	private static ReSpecThDataSetUiBinder uiBinder = GWT.create(ReSpecThDataSetUiBinder.class);

	interface ReSpecThDataSetUiBinder extends UiBinder<Widget, ReSpecThDataSet> {
	}


	XMLParseResource resource = GWT.create(XMLParseResource.class);
	
	@UiField
	MaterialLink datasetname;
	@UiField
	DataDescription description;
	@UiField
	SetOfReferenceDescriptions references;
	@UiField
	MaterialLink datasets;
	@UiField
	MaterialCollapsible experiments;
	@UiField
	MaterialIcon storedata;
	@UiField
	MaterialIcon delete;
	
	@UiField
	MaterialModal datasavemodal;
	@UiField
	MaterialLink modalsavetext;
	@UiField
	MaterialLink modalsource;
	@UiField
	MaterialLink modalkeyword;
	@UiField
	MaterialButton modalsave;
	@UiField
	MaterialButton modalsaveclose;
	
	String dataType = "ReSpecThExperimentalData";
	ArrayList<ReSpecTHXMLFileBase> experimentdata;
	ArrayList<String> fileNames;
	
	public ReSpecThDataSet() {
		initWidget(uiBinder.createAndBindUi(this));
		init();
	}
	public ReSpecThDataSet(String source, String keyword) {
		initWidget(uiBinder.createAndBindUi(this));
		String dataSetName = description.createObjectKeyword(source,keyword);
		datasetname.setText(dataSetName);
		description.setSourceAndKeyword(source, keyword);
		datasets.setText(resource.datasets());
		init();
	}

	private void init() {
		experimentdata = new ArrayList<ReSpecTHXMLFileBase>();
		fileNames = new ArrayList<String>();
		modalsave.setText("Save");
		modalsaveclose.setText("Close");
		modalsavetext.setText("Save data set?");
	}
	public void setText(String text) {
		datasetname.setText(text);
	}

	public String getText() {
		return datasetname.getText();
	}
	public ReSpecThExperimentDisplay addExperiment(XMLDataSource exp) {
		ReSpecThExperimentDisplay display = new ReSpecThExperimentDisplay(exp);
		experiments.add(display);
		experimentdata.add(exp.getParsedInfo());
		fileNames.add(exp.getFileName());
		return display;
	}
	class BuildChart implements Runnable {

		ArrayList<ReSpecThDataMatrixDisplay> displaylst = new ArrayList<ReSpecThDataMatrixDisplay>();
		public void addDisplay(ReSpecThDataMatrixDisplay display) {
			displaylst.add(display);
		}
		
		@Override
		public void run() {
			for(ReSpecThDataMatrixDisplay display : displaylst) {
			display.draw();
			}
		}
		
	}

	public void addSet(ArrayList<XMLDataSource> data) {
		ChartLoader chartLoader = new ChartLoader(ChartPackage.TABLE);
		BuildChart build = new BuildChart();
		for(XMLDataSource source : data) {
			ReSpecThExperimentDisplay display = addExperiment(source);
			build.addDisplay(display.getMatrixDisplay());
		}
		chartLoader.loadApi(build);
	}
	
	@UiHandler("storedata")
	void storeDataClick(ClickEvent e) {
		modalkeyword.setText(description.getKeyWord());
		modalsource.setText(description.getSource());
		datasavemodal.openModal();
	}
	
	@UiHandler("modalsaveclose")
	void modalCloseClick(ClickEvent e) {
		datasavemodal.closeModal();
	}
	
	@UiHandler("modalsave")
	void modalSaveClick(ClickEvent e) {
		TextToDatabaseAsync async = TextToDatabase.Util.getInstance();
		String keyword = description.createObjectKeyword();
		RegisterReSpecThDataCallback callback = new RegisterReSpecThDataCallback(keyword,fileNames,experimentdata);
		ArrayList<DataSetReference> refs = references.getReferences(keyword);
		async.registerDataInputDescription(getDescription(),refs,callback);						
		datasavemodal.closeModal();
	}
	
	private DescriptionDataData getDescription(){
		DescriptionDataData descrdata = new DescriptionDataData(
				description.getKeyWord(),
				description.getOneLineDescription(),
				description.getDescription(), 
				description.getSourceDate(), 
				description.getSource(), 
				description.getInputKey(),
				dataType,
				description.getKeywords());
		return descrdata;
	}

}
