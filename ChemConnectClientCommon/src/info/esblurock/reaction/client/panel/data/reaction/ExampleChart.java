package info.esblurock.reaction.client.panel.data.reaction;

public class ExampleChart extends MaterialAreaChart {

	private double[][] values = new double[][] { { 165, 135, 157, 139, 136 }, { 938, 1120, 1167, 1110, 691 }, { 522, 599, 587, 615, 629 }, { 998, 1268, 807, 968, 1026 }, { 450, 288, 397, 215, 366 } };
	private String[] sets = new String[] { "Bolivia", "Ecuador", "Madagascar", "Papua Guinea", "Rwanda" };
	private String[] xaxis = new String[] { "2004/05", "2005/06", "2006/07", "2007/08", "2008/09" };
	
	private String xaxisTitle = "XAxisTitle";
	private String yaxisTitle = "YAxisTitle";
	private String setTitle = "SetTitle";

	ExampleChart() {
		super.fillString(setTitle,xaxisTitle,yaxisTitle,xaxis,sets,values);
	}
}
