package info.esblurock.reaction.data.chemical.respect.database;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
@Inheritance(customStrategy = "complete-table")
public class ExperimentDescriptionKineticConstants extends ExperimentDescriptionDatabaseObject {

	@Persistent
	String reaction;
	@Persistent
	String information;
	@Persistent
	String bulkgas;
	@Persistent
	String order;
	
	public ExperimentDescriptionKineticConstants(String dataset, String filename, String fileCode, 
			String fileReference,
			String experimentType, String reaction,
			String information, String bulkgas, String order) {
		super(dataset, filename, fileCode, fileReference, experimentType);
		this.reaction = reaction;
		this.information = information;
		this.bulkgas = bulkgas;
		this.order = order;
	}

	public String getReaction() {
		return reaction;
	}

	public String getInformation() {
		return information;
	}

	public String getBulkgas() {
		return bulkgas;
	}

	public String getOrder() {
		return order;
	}

}
