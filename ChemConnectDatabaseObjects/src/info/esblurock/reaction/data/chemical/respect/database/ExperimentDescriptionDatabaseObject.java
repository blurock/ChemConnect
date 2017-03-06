package info.esblurock.reaction.data.chemical.respect.database;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.datanucleus.annotations.Unindexed;

import info.esblurock.reaction.data.DatabaseObject;

@PersistenceCapable
public class ExperimentDescriptionDatabaseObject extends DatabaseObject {

	@Persistent
	String dataset;
	@Persistent
	String filename;
	@Persistent
	String fileCode;
	@Persistent
	String fileReference;
	@Persistent
	String experimentType;
	@Persistent
	@Unindexed
	Integer numberOfRows;
	
	public ExperimentDescriptionDatabaseObject(String dataset, String filename, String fileCode, String fileReference,
			String experimentType) {
		super();
		this.dataset = dataset;
		this.filename = filename;
		this.fileCode = fileCode;
		this.fileReference = fileReference;
		this.experimentType = experimentType;
		numberOfRows = 0;
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
	public String getFileReference() {
		return fileReference;
	}
	public String getExperimentType() {
		return experimentType;
	}
	public Integer getNumberOfRows() {
		return numberOfRows;
	}
	public void setNumberOfRows(Integer numberOfRows) {
		this.numberOfRows = numberOfRows;
	}
	
}
