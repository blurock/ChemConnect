package info.esblurock.reaction.client.panel.data;

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
import info.esblurock.reaction.data.MatrixOfDoubles;

public class DrawMatrixOfDoubles extends Composite implements HasText {

	private static DrawMatrixOfDoublesUiBinder uiBinder = GWT.create(DrawMatrixOfDoublesUiBinder.class);

	interface DrawMatrixOfDoublesUiBinder extends UiBinder<Widget, DrawMatrixOfDoubles> {
	}

	private MatrixOfDoubles matrix;

	@UiField
	MaterialLink datapointtablelabel;
	@UiField
	HTMLPanel datapanel;

	class BuildChart implements Runnable {
		@Override
		public void run() {
			draw();
		}
	}

	public DrawMatrixOfDoubles(MatrixOfDoubles matrix) {
		initWidget(uiBinder.createAndBindUi(this));
		this.matrix = matrix;
		BuildChart build = new BuildChart();
		ChartLoader chartLoader = new ChartLoader(ChartPackage.TABLE);
		chartLoader.loadApi(build);
	}

	public void draw() {
		Table table = new Table();
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "   ");
		for (String coltitle : matrix.getColumnTitle()) {
			dataTable.addColumn(ColumnType.STRING, coltitle);
		}
		int rowcounts = matrix.getNumberOfRows();
		dataTable.addRows(rowcounts);
		for (int row = 0; row < rowcounts; row++) {
			int column = 0;
			String rowtitle = matrix.getRowTitle().get(row);
			dataTable.setCell(row, column, rowtitle);
			column++;
			ArrayList<Double> rowvalues = matrix.getRowOfDoubles(row);
			for (Double value : rowvalues) {
				dataTable.setCell(row, column, value.toString());
				column++;
			}
		}
		TableOptions options = TableOptions.create();
		options.setAlternatingRowStyle(true);
		options.setShowRowNumber(true);
		table.draw(dataTable, options);
		table.setTitle("");
		datapanel.add(table);
	}

	@Override
	public String getText() {
		return datapointtablelabel.getText();
	}

	@Override
	public void setText(String text) {
		datapointtablelabel.setText(text);
	}
}