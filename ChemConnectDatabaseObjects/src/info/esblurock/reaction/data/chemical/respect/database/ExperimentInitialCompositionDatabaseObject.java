package info.esblurock.reaction.data.chemical.respect.database;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import info.esblurock.reaction.data.DatabaseObject;

@PersistenceCapable
public class ExperimentInitialCompositionDatabaseObject extends DatabaseObject {
	@Persistent
	String dataset;
	@Persistent
	String filename;
	@Persistent
	String fileCode;
	@Persistent
	String species;
	@Persistent
	Double value;
	@Persistent
	String units;
	
	public ExperimentInitialCompositionDatabaseObject() {
	}
	public ExperimentInitialCompositionDatabaseObject(String dataset, String filename, String fileCode, 
			String species, Double value, String units) {
		super();
		this.dataset = dataset;
		this.filename = filename;
		this.fileCode = fileCode;
		this.species = species;
		this.value = value;
		this.units = units;
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
	public String getSpecies() {
		return species;
	}
	public Double getValue() {
		return value;
	}
	public String getUnits() {
		return units;
	}

	
}
