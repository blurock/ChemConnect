package info.esblurock.reaction.xmlparse.client.ui.respect;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;

import gwt.material.design.client.ui.MaterialCollapsible;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialToast;
import info.esblurock.reaction.client.panel.description.DataDescription;
import info.esblurock.reaction.client.panel.description.SetOfReferenceDescriptions;
import info.esblurock.reaction.xmlparse.client.xmlfiles.XMLDataSource;
import info.esblurock.reaction.xmlparse.resources.XMLParseResource;

public class ReSpecThDataSet extends Composite implements HasText {

	private static ReSpecThDataSetUiBinder uiBinder = GWT.create(ReSpecThDataSetUiBinder.class);

	interface ReSpecThDataSetUiBinder extends UiBinder<Widget, ReSpecThDataSet> {
	}

	public ReSpecThDataSet() {
		initWidget(uiBinder.createAndBindUi(this));
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
	
	public ReSpecThDataSet(String source, String keyword) {
		initWidget(uiBinder.createAndBindUi(this));
		String dataSetName = description.createObjectKeyword(source,keyword);
		datasetname.setText(dataSetName);
		description.setSourceAndKeyword(source, keyword);
		datasets.setText(resource.datasets());
	}

	public void setText(String text) {
		datasetname.setText(text);
	}

	public String getText() {
		return datasetname.getText();
	}
	public void addExperiment(XMLDataSource exp) {
		ReSpecThExperimentDisplay display = new ReSpecThExperimentDisplay(exp);
		experiments.add(display);
		
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
			ReSpecThExperimentDisplay display = new ReSpecThExperimentDisplay(source);
			experiments.add(display);
			build.addDisplay(display.getMatrixDisplay());
		}
		chartLoader.loadApi(build);

	}
	
}
