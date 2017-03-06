package info.esblurock.reaction.data.chemical.respect.database;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
@Inheritance(customStrategy = "complete-table")

public class ExperimentDescriptionTimeProfile extends ExperimentDescriptionExperiment {
	
	@Persistent
	String timeshiftTarget;
	@Persistent
	String timeshiftType;
	@Persistent
	Double timeshiftAmount;

	public ExperimentDescriptionTimeProfile(String dataset, String filename, String fileCode, 
			String fileReference,
			String experimentType, 
			String apparatusKind, String apparatusMode, 
			String timeshiftTarget, String timeshiftType, Double timeshiftAmount) {
		super(dataset, filename, fileCode, fileReference, experimentType, apparatusKind, apparatusMode);
		
		this.timeshiftTarget = timeshiftTarget;
		this.timeshiftType = timeshiftType;
		this.timeshiftAmount = timeshiftAmount;
	}

}
