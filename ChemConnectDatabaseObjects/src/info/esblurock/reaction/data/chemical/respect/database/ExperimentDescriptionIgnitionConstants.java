package info.esblurock.reaction.data.chemical.respect.database;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
@Inheritance(customStrategy = "complete-table")
public class ExperimentDescriptionIgnitionConstants extends ExperimentDescriptionExperiment {

	@Persistent
	String ignitionTarget;
	@Persistent
	String ignitionType;
	@Persistent
	String ignitionAmount;
	@Persistent
	String ignitionUnits;
	@Persistent
	String ignitionOperation;

	public ExperimentDescriptionIgnitionConstants(String dataset, String filename, String fileCode,
			String fileReference, String experimentType, String apparatusKind, String apparatusMode,
			String ignitionTarget, String ignitionType, String ignitionAmount, String ignitionUnits,
			String ignitionOperation) {
		super(dataset, filename, fileCode, fileReference, experimentType, apparatusKind, apparatusMode);
		this.ignitionTarget = ignitionTarget;
		this.ignitionType = ignitionType;
		this.ignitionAmount = ignitionAmount;
		this.ignitionUnits = ignitionUnits;
		this.ignitionOperation = ignitionOperation;
	}

	public String getIgnitionTarget() {
		return ignitionTarget;
	}

	public void setIgnitionTarget(String ignitionTarget) {
		this.ignitionTarget = ignitionTarget;
	}

	public String getIgnitionType() {
		return ignitionType;
	}

	public void setIgnitionType(String ignitionType) {
		this.ignitionType = ignitionType;
	}

	public String getIgnitionAmount() {
		return ignitionAmount;
	}

	public void setIgnitionAmount(String ignitionAmount) {
		this.ignitionAmount = ignitionAmount;
	}

	public String getIgnitionUnits() {
		return ignitionUnits;
	}

	public void setIgnitionUnits(String ignitionUnits) {
		this.ignitionUnits = ignitionUnits;
	}

	public String getIgnitionOperation() {
		return ignitionOperation;
	}

	public void setIgnitionOperation(String ignitionOperation) {
		this.ignitionOperation = ignitionOperation;
	}

}
