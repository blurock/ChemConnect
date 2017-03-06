package info.esblurock.reaction.data.chemical.respect.database;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
@Inheritance(customStrategy = "complete-table")
public class ExperimentDescriptionExperiment extends ExperimentDescriptionDatabaseObject {

	@Persistent
	String apparatusKind;
	@Persistent
	String apparatusMode;
	
	public ExperimentDescriptionExperiment(String dataset, String filename, String fileCode, 
			String fileReference,
			String experimentType, String apparatusKind, String apparatusMode) {
		super(dataset, filename, fileCode, fileReference, experimentType);
		this.apparatusKind = apparatusKind;
		this.apparatusMode = apparatusMode;
	}

}
