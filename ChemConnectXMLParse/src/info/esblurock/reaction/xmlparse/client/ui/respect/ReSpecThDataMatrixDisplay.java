package info.esblurock.reaction.xmlparse.client.ui.respect;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.table.Table;
import com.googlecode.gwt.charts.client.table.TableOptions;

import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialToast;
import info.esblurock.reaction.data.chemical.respect.ReSpecTHXMLFileBase;
import info.esblurock.reaction.data.chemical.respect.ReSpecThDataPoint;
import info.esblurock.reaction.data.chemical.respect.ReSpecThProperty;
import info.esblurock.reaction.xmlparse.resources.XMLParseResource;

public class ReSpecThDataMatrixDisplay extends Composite implements HasText {

	private static ReSpecThDataMatrixDisplayUiBinder uiBinder = GWT.create(ReSpecThDataMatrixDisplayUiBinder.class);

	interface ReSpecThDataMatrixDisplayUiBinder extends UiBinder<Widget, ReSpecThDataMatrixDisplay> {
	}

	XMLParseResource resource = GWT.create(XMLParseResource.class);
	
	@UiField
	MaterialLink datapointtablelabel;
	@UiField
	HTMLPanel datapanel;
	
	ReSpecTHXMLFileBase respect;

/*
	public ReSpecThDataMatrixDisplay() {
		initWidget(uiBinder.createAndBindUi(this));
	}
*/
	public ReSpecThDataMatrixDisplay(ReSpecTHXMLFileBase respect) {
		initWidget(uiBinder.createAndBindUi(this));
		this.respect = respect;
		initialize();
	}

	private void initialize() {
		datapointtablelabel.setText(resource.datapoints());
	}
	public void draw() {
		Table table = new Table();
		DataTable dataTable = DataTable.create();
		for(ReSpecThProperty point : respect.getDataGroupProperties()) {
			dataTable.addColumn(ColumnType.STRING, point.getLabel());
		}
		ArrayList<ReSpecThDataPoint> points = respect.getDataPoints();
		dataTable.addRows(points.size());
		int row = 0;
		for(ReSpecThDataPoint point : points) {
			int column = 0;
			for(String value: point.getValues() ) {
				dataTable.setCell(row, column, value);
				column++;
			}
			row++;
		}
		TableOptions options = TableOptions.create();
		options.setAlternatingRowStyle(true);
		options.setShowRowNumber(true);
		table.draw(dataTable, options);
		datapanel.add(table);
	}
	public void setText(String text) {
		datapointtablelabel.setText(text);
	}

	public String getText() {
		return respect.getBibliographyLink();
	}

}
