package info.esblurock.reaction.data;

import java.util.ArrayList;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.datanucleus.annotations.Unindexed;

@PersistenceCapable
public class MatrixOfObjects extends DatabaseObject {
	private static final long serialVersionUID = 1L;
	@Persistent
	@Unindexed
	ArrayList<Object> objects;
	@Persistent
	@Unindexed
	ArrayList<String> columnTitle;
	@Persistent
	@Unindexed
	ArrayList<String> rowTitle;
	@Persistent
	@Unindexed
	int numberOfRows;
	@Persistent
	@Unindexed
	int numberOfColumns;
    @Persistent
    String user;
    @Persistent
    String sourceCode;
	
    public MatrixOfObjects() {
    }
    
	public MatrixOfObjects(String user, String sourceCode,
			ArrayList<String> rowTitle, ArrayList<String> columnTitle) {
		this.user = user;
		this.sourceCode = sourceCode;
		this.rowTitle = rowTitle;
		this.columnTitle = columnTitle;
		this.numberOfColumns = columnTitle.size();
		this.numberOfRows = rowTitle.size();
		int matsize = numberOfColumns * numberOfRows;
		this.objects = new ArrayList<Object>(matsize);
		for(int i=0; i<matsize;i++) {
			objects.add(new Object());
		}
	}

	public void insert(int row, int column, Object value) {
		int position = row * numberOfRows + column;
		objects.set(position, value);
	}
	public String asSpreadSheet(String delimitor) {
		StringBuilder build = new StringBuilder();
		
		int count = 0;
		for(int row=0;row<numberOfRows;row++) {
			boolean first = true;
			for(int column=0;column<numberOfColumns; column++) {
				if(first) {
					first = false;
				} else {
					build.append(delimitor);
				}
				build.append(objects.get(count)).toString();
				count++;
			}
			build.append("\n");
		}
		return build.toString();
	}

	public ArrayList<Object> getObjects() {
		return objects;
	}

	public ArrayList<String> getColumnTitle() {
		return columnTitle;
	}

	public ArrayList<String> getRowTitle() {
		return rowTitle;
	}

	public int getNumberOfRows() {
		return numberOfRows;
	}

	public int getNumberOfColumns() {
		return numberOfColumns;
	}

	public String getUser() {
		return user;
	}

	public String getSourceCode() {
		return sourceCode;
	}


}
