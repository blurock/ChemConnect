package info.esblurock.reaction.data.chemical.respect;

import java.io.Serializable;
import java.util.ArrayList;

public class ReSpecThDataPoint implements Serializable {

	private static final long serialVersionUID = 1L;

	ArrayList<String> values;
	
	public ReSpecThDataPoint(int sze) {
		values = new ArrayList<String>(sze);
	}
	public ReSpecThDataPoint() {
		values = new ArrayList<String>();
	}
	
	public void addValue(String value,int index) {
		values.add(index, value);
	}
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for(String value : values) {
			buffer.append(value + "\t");
		}
		buffer.append("\n");
		return buffer.toString();
	}
}
