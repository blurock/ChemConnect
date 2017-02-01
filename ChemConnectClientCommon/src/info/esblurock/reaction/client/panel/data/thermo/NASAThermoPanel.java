package info.esblurock.reaction.client.panel.data.thermo;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialLabel;

import info.esblurock.reaction.data.chemical.thermo.NASAPolynomialData;

public class NASAThermoPanel extends Composite implements HasText {

	private static NASAThermoPanelUiBinder uiBinder = GWT.create(NASAThermoPanelUiBinder.class);

	interface NASAThermoPanelUiBinder extends UiBinder<Widget, NASAThermoPanel> {
	}

	public NASAThermoPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	@UiField
	MaterialLabel lower1,lower2,lower3,lower4,lower5,lower6,lower7;
	@UiField
	MaterialLabel upper1,upper2,upper3,upper4,upper5,upper6,upper7;
	@UiField
	MaterialLabel line1,line2,line3,line4;

	@UiField
	MaterialButton calculateEnthalpy;
	@UiField
	MaterialButton calculateEntropy;
	
	@UiField
	MaterialLabel lowerLabel, upperLabel;
	
	@UiField
	MaterialLabel enthalpy, ethalpyLabel, entropy, entropyLabel;
	
	@UiField
	HTMLPanel panel;
	
	NASAPolynomialData nasa;
	
	public NASAThermoPanel(NASAPolynomialData nasa) {
		
		initWidget(uiBinder.createAndBindUi(this));
		ArrayList<Double> lc = nasa.getLower();
		ArrayList<Double> uc = nasa.getUpper();
		this.nasa = nasa;		
		
		//NASAPolynomial poly = new NASAPolynomial();
		
		//FormatNASAPolynomialData formatter = new FormatNASAPolynomialData();
		//formatter.convertNASAPolynomial(nasa);
		
		enthalpy.setText(nasa.getStandardEnthalpy().toString());
		entropy.setText(nasa.getStandardEntropy().toString());
		
		
		coefficientFormat(lower1,lc.get(0));
		coefficientFormat(lower2,lc.get(1));
		coefficientFormat(lower3,lc.get(2));
		coefficientFormat(lower4,lc.get(3));
		coefficientFormat(lower5,lc.get(4));
		coefficientFormat(lower6,lc.get(5));
		coefficientFormat(lower7,lc.get(6));
		
		coefficientFormat(upper1,uc.get(0));
		coefficientFormat(upper2,uc.get(1));
		coefficientFormat(upper3,uc.get(2));
		coefficientFormat(upper4,uc.get(3));
		coefficientFormat(upper5,uc.get(4));
		coefficientFormat(upper6,uc.get(5));
		coefficientFormat(upper7,uc.get(6));
		
		
		//line1.setText(formatter.getLine1());
		//line2.setText(formatter.getLine2());
		//line3.setText(formatter.getLine3());
		//line4.setText(formatter.getLine4());
/*
		try {
			poly.parse(line1.getText(), line1.getText(), line1.getText(), line1.getText());
			data(poly);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ThermodynamicComputeException e) {
			e.printStackTrace();
		}
		*/
	}

	void coefficientFormat(MaterialLabel lbl, Double num) {
		lbl.getElement().getStyle().setFontSize(0.8, Unit.EM);
		lbl.setText(NumberFormat.getScientificFormat().overrideFractionDigits(4).format(num.doubleValue()));
	}
	
	@UiHandler("calculateEnthalpy")
	void onEthaplyClick(ClickEvent e) {
		Window.alert("Calculate Entalpy at given temperature");
	}
	@UiHandler("calculateEntropy")
	void onEntropyClick(ClickEvent e) {
		Window.alert("Calculate Entropy at given temperature");
	}
	/*
	@Override
	protected void onAttach() {
		initialize();
	}

	private void initialize() {
		ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
		chartLoader.loadApi(new Runnable() {

			@Override
			public void run() {
				enthalpyChart();
				entropyChart();
				heatCapacityChart();
				panel.add(chart1);
				panel.add(chart2);
				panel.add(chart3);
			}
		});
	}

	
	private LineChart chart1;
	private LineChart chart2;
	private LineChart chart3;
	
	double[] temperatures = new double[200];
	double[] enthalpyValues = new double[200];
	double[] entropyValues = new double[200];
	double[] cpValues = new double[200];
	int length = 200;
	private void data(NASAPolynomial nasapolynomial) throws ThermodynamicComputeException {
		double temperature = 300.0;
		double increment = (2500.0-300.0)/200.0;
		for(int i=0; i<200;i++) {
			temperatures[i] = temperature;
			enthalpyValues[i] = nasapolynomial.computeEnthalpy(temperature);
			entropyValues[i] = nasapolynomial.computeEntropy(temperature);
			cpValues[i] = nasapolynomial.getHeatCapacity(temperature);
			temperature += increment;
		}
	}
	private void enthalpyChart() {
		// Prepare the data
		int length = 200;
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.NUMBER, "Temperature");
		dataTable.addColumn(ColumnType.NUMBER, "Enthalpy");
		dataTable.addRows(length);
		for(int i=0; i<length;i++) {
			dataTable.setValue(i,0,temperatures[i]);
			dataTable.setValue(i,1,enthalpyValues[i]);
		}
		chart1 = new LineChart();
		chart1.draw(dataTable, getOptions());
	}
	private void entropyChart() {
		// Prepare the data
		int length = 200;
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.NUMBER, "Temperature");
		dataTable.addColumn(ColumnType.NUMBER, "Entropy");
		dataTable.addRows(length);
		for(int i=0; i<length;i++) {
			dataTable.setValue(i,0,temperatures[i]);
			dataTable.setValue(i,1,entropyValues[i]);
		}
		chart2 = new LineChart();
		chart2.draw(dataTable, getOptions());
	}

	private void heatCapacityChart() {
		// Prepare the data
		int length = 200;
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.NUMBER, "Temperature");
		dataTable.addColumn(ColumnType.NUMBER, "Heat Capacity");
		dataTable.addRows(length);
		for(int i=0; i<length;i++) {
			dataTable.setValue(i,0,temperatures[i]);
			dataTable.setValue(i,1,cpValues[i]);
		}
		chart3 = new LineChart();
		chart3.draw(dataTable, getOptions());
	}

	private LineChartOptions getOptions() {
		// Options
		LineChartOptions options = LineChartOptions.create();
		options.setBackgroundColor("#2196F3");

		// Legends
		Legend legend = Legend.create();
		legend.setPosition(LegendPosition.NONE);
		options.setLegend(legend);

		// Text Styles
		TextStyle hStyle = TextStyle.create();
		hStyle.setColor("90caf9");

		// Grid Lines
		Gridlines lines = Gridlines.create();
		lines.setColor("1e88e5");

		// HAxis and VAxis
		HAxis hAxis = HAxis.create("");
		VAxis vAxis = VAxis.create("");
		hAxis.setTextStyle(hStyle);
		hAxis.setGridlines(lines);
		vAxis.setTextStyle(hStyle);
		vAxis.setGridlines(lines);

		options.setHAxis(hAxis);
		options.setVAxis(vAxis);

		CurveType type = CurveType.NONE;

		options.setColors("white");
		options.setEnableInteractivity(true);
		options.setCurveType(type);
		options.setPointSize(5);

		// Set Animation
		Animation animation = Animation.create();
		animation.setEasing(AnimationEasing.OUT);
		animation.setDuration(250);

		options.setAnimation(animation);
		return options;
	}
	
	*/
	
	
	public void setText(String text) {
		
	}

	public String getText() {
		return null;
	}

}
