package info.esblurock.reaction.data.chemical.respect.database;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import info.esblurock.reaction.data.DatabaseObject;

@PersistenceCapable
public class ExperimentNumericalDataPointDatabaseObject extends DatabaseObject {

	@Persistent
	String dataset;
	@Persistent
	String filename;
	@Persistent
	String fileCode;
	@Persistent
	Integer rowIndex;
	@Persistent
	String label;
	@Persistent
	Double numericalValue;
	
	public ExperimentNumericalDataPointDatabaseObject(String dataset, String filename, String fileCode, 
			String label, Double numericalValue, Integer rowIndex) {
		super();
		this.dataset = dataset;
		this.filename = filename;
		this.fileCode = fileCode;
		this.label = label;
		this.numericalValue = numericalValue;
		this.rowIndex = rowIndex;
	}
	public String getDataset() {
		return dataset;
	}
	public String getFilename() {
		return filename;
	}
	public String getFileCode() {
		return fileCode;
	}
	public String getLabel() {
		return label;
	}
	public Double getNumericalValue() {
		return numericalValue;
	}
	
	
}
