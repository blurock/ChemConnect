package info.esblurock.reaction.data.chemical.respect.database;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import info.esblurock.reaction.data.DatabaseObject;
import info.esblurock.reaction.data.chemical.respect.ReSpecThProperty;

@PersistenceCapable
public class ExperimentCommonPropertyDatabaseObject extends DatabaseObject {
	@Persistent
	String dataset;
	@Persistent
	String filename;
	@Persistent
	String fileCode;
	@Persistent
	String label;
	@Persistent
	String name;
	@Persistent
	String units;
	@Persistent
	String value;
	@Persistent
	String description;
	
	public ExperimentCommonPropertyDatabaseObject() {
	}
	public ExperimentCommonPropertyDatabaseObject(String dataset, String fileName, String fileCode, ReSpecThProperty property) {
		super();
		this.dataset = dataset;
		this.fileCode = fileCode;
		this.filename = fileName;
		this.label = property.getLabel();
		this.name = property.getName();
		this.units = property.getUnits();
		this.value = property.getValue();
		this.description = property.getDescription();
	}
	public ExperimentCommonPropertyDatabaseObject(String dataset, String fileCode,
			String label, String name, String units, String value,
			String description) {
		super();
		this.dataset = dataset;
		this.fileCode = fileCode;
		this.label = label;
		this.name = name;
		this.units = units;
		this.value = value;
		this.description = description;
	}

	public String getLabel() {
		return label;
	}

	public String getName() {
		return name;
	}

	public String getUnits() {
		return units;
	}

	public String getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}
}
