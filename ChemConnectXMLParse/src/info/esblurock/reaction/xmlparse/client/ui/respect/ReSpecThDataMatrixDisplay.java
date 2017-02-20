package info.esblurock.reaction.xmlparse.client.ui.respect;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
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
import info.esblurock.reaction.data.chemical.respect.ReSpecTHXMLFileBase;
import info.esblurock.reaction.data.chemical.respect.ReSpecThDataPoint;
import info.esblurock.reaction.data.chemical.respect.ReSpecThProperty;

public class ReSpecThDataMatrixDisplay extends Composite implements HasText {

	private static ReSpecThDataMatrixDisplayUiBinder uiBinder = GWT.create(ReSpecThDataMatrixDisplayUiBinder.class);

	interface ReSpecThDataMatrixDisplayUiBinder extends UiBinder<Widget, ReSpecThDataMatrixDisplay> {
	}

	@UiField
	MaterialLink datapointtablelabel;
	@UiField
	HTMLPanel datapanel;

	private Table table;
	ReSpecTHXMLFileBase respect;

	public ReSpecThDataMatrixDisplay() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public ReSpecThDataMatrixDisplay(ReSpecTHXMLFileBase respect) {
		initWidget(uiBinder.createAndBindUi(this));
		this.respect = respect;
		initialize();
	}

	class BuildChart implements Runnable {

		@Override
		public void run() {
			table = new Table();
			datapanel.add(table);
			draw();
		}
		
	}
	private void initialize() {
		ChartLoader chartLoader = new ChartLoader(ChartPackage.TABLE);
		BuildChart build = new BuildChart();
		chartLoader.loadApi(build);
	}
	private void draw() {

		// Prepare the data
		DataTable dataTable = DataTable.create();
		for(ReSpecThProperty point : respect.getDataGroupProperties()) {
			dataTable.addColumn(ColumnType.STRING, point.getLabel());
		}
		ArrayList<ReSpecThDataPoint> points = respect.getDataPoints();
		dataTable.addRows(points.size());
		
		Window.alert("Draw Table: " + points.size());
		
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

		// Draw the chart
		table.draw(dataTable, options);
		Window.alert("Draw Table: drawn");

	}
	public void setText(String text) {
		datapointtablelabel.setText(text);
	}

	public String getText() {
		return datapointtablelabel.getText();
	}

}
