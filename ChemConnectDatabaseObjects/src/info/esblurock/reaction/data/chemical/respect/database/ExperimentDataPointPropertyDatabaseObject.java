package info.esblurock.reaction.data.chemical.respect.database;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import info.esblurock.reaction.data.DatabaseObject;

@PersistenceCapable
public class ExperimentDataPointPropertyDatabaseObject extends DatabaseObject {

	@Persistent
	String dataset;
	@Persistent
	String filename;
	@Persistent
	String fileCode;
	@Persistent
	String id;
	@Persistent
	String label;
	@Persistent
	String plotaxis;
	@Persistent
	String plotscale;
	@Persistent
	String name;
	@Persistent
	String units;
	@Persistent
	String description;
	
	public ExperimentDataPointPropertyDatabaseObject(String dataset, String filename, String fileCode, String id,
			String label, String plotaxis, String plotscale, String name, String units, String description) {
		super();
		this.dataset = dataset;
		this.filename = filename;
		this.fileCode = fileCode;
		this.id = id;
		this.label = label;
		this.plotaxis = plotaxis;
		this.plotscale = plotscale;
		this.name = name;
		this.units = units;
		this.description = description;
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
	public String getId() {
		return id;
	}
	public String getLabel() {
		return label;
	}
	public String getPlotaxis() {
		return plotaxis;
	}
	public String getPlotscale() {
		return plotscale;
	}
	public String getName() {
		return name;
	}
	public String getUnits() {
		return units;
	}
	public String getDescription() {
		return description;
	}
	
	
}
