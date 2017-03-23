package info.esblurock.reaction.data;

import java.util.ArrayList;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class MatrixOfDoubles extends MatrixOfObjects {

	public MatrixOfDoubles() {
	}
	public MatrixOfDoubles(String user, String sourceCode, ArrayList<String> rowTitle, ArrayList<String> columnTitle) {
		super(user, sourceCode, rowTitle, columnTitle);
	}


	
	public ArrayList<Double> getRowOfDoubles(int row) {
		int beginposition = row * numberOfRows;
		int endposition = beginposition + numberOfColumns;
		ArrayList<Double> values = new ArrayList<Double>();
		for(int pos = beginposition; pos < endposition;pos++) {
			Double value = (Double) objects.get(pos);
			values.add(value);
		}
		return values;
	}
	public void insertRow(int row, ArrayList<Double> values) {
		if (values.size() == numberOfColumns) {
			int position = row * numberOfRows;
			for (Double value : values) {
				this.objects.set(position, value);
				position++;
			}
		}
	}
	public void insert(int row, int column, Double value) {
		int position = row * numberOfRows + column;
		objects.set(position, value);
	}

}
