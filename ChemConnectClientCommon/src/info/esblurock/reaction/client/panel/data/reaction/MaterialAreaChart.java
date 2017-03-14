package info.esblurock.reaction.client.panel.data.reaction;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.corechart.LineChart;
import com.googlecode.gwt.charts.client.corechart.LineChartOptions;
import com.googlecode.gwt.charts.client.options.ChartArea;
import com.googlecode.gwt.charts.client.options.Gridlines;
import com.googlecode.gwt.charts.client.options.HAxis;
import com.googlecode.gwt.charts.client.options.Legend;
import com.googlecode.gwt.charts.client.options.LegendAlignment;
import com.googlecode.gwt.charts.client.options.LegendPosition;
import com.googlecode.gwt.charts.client.options.TextPosition;
import com.googlecode.gwt.charts.client.options.VAxis;
import gwt.material.design.client.ui.MaterialCardContent;
import gwt.material.design.client.ui.MaterialCardTitle;

public class MaterialAreaChart extends Composite {

	private static MaterialAreaChartUiBinder uiBinder = GWT.create(MaterialAreaChartUiBinder.class);

	interface MaterialAreaChartUiBinder extends UiBinder<Widget, MaterialAreaChart> {
	}

	@UiField
	MaterialCardContent cardContent;
	MaterialCardTitle cardtitle;
	//private AreaChart chart;
	private LineChart chart;
	boolean isLoop = false;
	
	private double[][] values;
	private String[] sets;
	private String[] xaxisS;
	private double[] xaxisD;
	
	private String xaxisTitle;
	private String yaxisTitle;
	private String setTitle;
	boolean logScale;
	
	public MaterialAreaChart() {
		initWidget(uiBinder.createAndBindUi(this));
		logScale = false;
		initialize();
	}

	public void setLogScale() {
		logScale = true;
	}
	public void setTitle(String title) {
		cardtitle.setText(title);
	}
	public void fillString(String setTitle, String xaxisTitle, String yaxisTitle, 
			String[] xaxis, String[] sets, double[][] values) {
		this.setTitle = setTitle;
		this.xaxisTitle = xaxisTitle;
		this.yaxisTitle = yaxisTitle;
		this.xaxisS = xaxis;
		this.xaxisD = null;
		this.sets = sets;
		this.values = values;
	}
	public void fillNumber(String setTitle, String xaxisTitle, String yaxisTitle, 
			double[] xaxis, String[] sets, double[][] values) {
		this.setTitle = setTitle;
		this.xaxisTitle = xaxisTitle;
		this.yaxisTitle = yaxisTitle;
		this.xaxisD = xaxis;
		this.xaxisS = null;
		this.sets = sets;
		this.values = values;
	}
	private void initialize() {
		ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
		chartLoader.loadApi(new Runnable() {

			@Override
			public void run() {
				chart = new LineChart();
				cardContent.add(chart);
				// Prepare the data with loop inside to populate the initial data 
				//setLoop();
				drawChart();
			}
		});
	}
	private void drawChart() {
		DataTable dataTable = DataTable.create();
		//StringBuilder build = new StringBuilder();
		//build.append("(");

		if(xaxisS != null) {
			dataTable.addColumn(ColumnType.STRING, setTitle);
			dataTable.addRows(xaxisS.length);
			for (int i = 0; i < xaxisS.length; i++) {
				dataTable.setValue(i, 0, xaxisS[i]);
			}
		} else {
			dataTable.addColumn(ColumnType.NUMBER, setTitle);
			dataTable.addRows(xaxisD.length);
			for (int i = 0; i < xaxisD.length; i++) {
				//build.append(" " + xaxisD[i] + " ");
				dataTable.setValue(i, 0, xaxisD[i]);
			}

		}
		//build.append(")\n");
		for (int i = 0; i < sets.length; i++) {
			dataTable.addColumn(ColumnType.NUMBER, sets[i]);
		}
		for (int col = 0; col < values.length; col++) {
			for (int row = 0; row < values[col].length; row++) {
				//build.append(values[col][row] + "\n");
				dataTable.setValue(row, col + 1, values[col][row]);
			}
		}
		//Window.alert(build.toString());
		chart.draw(dataTable, getOptions());
	}
	private LineChartOptions getOptions() {
		LineChartOptions options = LineChartOptions.create();
		// Text Positions X and Y Axis
		//Grid Lines
		Gridlines lines = Gridlines.create();
		lines.setColor("black");
		lines.setCount(5);

		HAxis hAxis = HAxis.create();
		hAxis.setTextPosition(TextPosition.OUT);
		hAxis.setGridlines(lines);
		hAxis.setTitle(xaxisTitle);
		hAxis.setBaseline(2);
		hAxis.setBaselineColor("blue");

		VAxis vAxis = VAxis.create();
		vAxis.setTextPosition(TextPosition.OUT);
		vAxis.setGridlines(lines);
		vAxis.setLogScale(logScale);
		vAxis.setTitle(yaxisTitle);

		// Legend
		Legend legend = Legend.create();
		legend.setPosition(LegendPosition.TOP);
		legend.setAligment(LegendAlignment.END);
		
		ChartArea area = ChartArea.create();
		area.setTop(20);
		area.setLeft(200);
		area.setWidth("70%");
		area.setHeight("70%");
		options.setChartArea(area);
		
		options.setVAxis(vAxis);
		options.setHAxis(hAxis);
		options.setLegend(legend);

		return options;
	}
	/*
	private AreaChartOptions getOptions() {
		//Grid Lines
		Gridlines lines = Gridlines.create();
		lines.setColor("fff");

		// Text Positions X and Y Axis
		HAxis hAxis = HAxis.create();
		hAxis.setTextPosition(TextPosition.NONE);

		VAxis vAxis = VAxis.create();
		vAxis.setTextPosition(TextPosition.NONE);
		vAxis.setGridlines(lines);
		hAxis.setGridlines(lines);
		// Legend
		Legend legend = Legend.create();
		legend.setPosition(LegendPosition.TOP);
		legend.setAligment(LegendAlignment.END);

		// Set options
		AreaChartOptions options = AreaChartOptions.create();
		options.setIsStacked(true);
		options.setAreaOpacity(1);
		options.setVAxis(vAxis);
		options.setHAxis(hAxis);
		options.setLegend(legend);
		options.setColors("2196f3", "42a5f5", "64b5f6", "90caf9", "bbdefb");
		
		ChartArea area = ChartArea.create();
		area.setTop(0);
		area.setLeft(0);
		area.setWidth("100%");
		area.setHeight("100%");
		options.setChartArea(area);
		
		//Set Animation
		Animation animation = Animation.create();
		animation.setDuration(500);
		animation.setEasing(AnimationEasing.OUT);
		options.setAnimation(animation);
		
		return options;
	}
*/
}