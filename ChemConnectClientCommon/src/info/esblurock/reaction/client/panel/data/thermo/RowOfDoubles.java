package info.esblurock.reaction.client.panel.data.thermo;

import java.util.ArrayList;

import info.esblurock.reaction.data.MatrixOfDoubles;

public class RowOfDoubles {
	String rowtitle;
	ArrayList<Double> values;
	
	RowOfDoubles(MatrixOfDoubles matrix, int row) {
		values = matrix.getRowOfDoubles(row);
		rowtitle = matrix.getRowTitle().get(row);
	}
}
