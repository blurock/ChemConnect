package info.esblurock.reaction.data.chemical.respect;

import java.util.ArrayList;

public class ReSpecThXMLExperiment extends ReSpecTHXMLFileBase {
	
	public static String experimentS = "experiment";
	public static String apparatusS = "apparatus";
	public static String kindS = "kind";
	public static String modeS = "mode";
	public static String ignitionTypeS= "ignitionType";
	public static String targetS = "target";
	public static String typeS = "type";
	public static String operationS = "operation";
	public static String timeshiftS = "timeshift";
	public static String experimentTypeS = "experimentType";

	String kind;
	String mode;
	String ignitionTarget;
	String ignitionType;
	String ignitionAmount;
	String ignitionUnits;
	String ignitionOperation;
	String timeshiftTarget;
	String timeshiftType;
	String timeshiftAmount;
	String experimentType;
	ArrayList<ReSpecThProperty> commonProperties;
	ArrayList<ReSpecThProperty> dataGroupProperties;
	
	
	
	public ReSpecThXMLExperiment() {
		xmlExperimentCategory = "experiment";
		commonProperties = new ArrayList<ReSpecThProperty>();
		dataGroupProperties = new ArrayList<ReSpecThProperty>();
	}

	public void addCommonProperty(ReSpecThProperty property) {
		commonProperties.add(property);
	}
	public void addDataGroupProperty(ReSpecThProperty property) {
		dataGroupProperties.add(property);
	}
	public String toString() {
		String base = super.toString();
		StringBuffer buffer = new StringBuffer();
		buffer.append(base);
		
		buffer.append("Ignition Target     :" + ignitionTarget);
		buffer.append("\n");
		buffer.append("Ignition Type       :" + ignitionType);
		buffer.append("\n");
		buffer.append("Ignition Amount     :" + ignitionAmount);
		buffer.append("\n");
		buffer.append("Ignition Units      :" + ignitionUnits);
		buffer.append("\n");
		buffer.append("Ignition Operation  :" + ignitionOperation);
		buffer.append("\n");
		buffer.append("Timeshift Target    :" + timeshiftTarget);
		buffer.append("\n");
		buffer.append("Timeshift Type      :" + timeshiftType);
		buffer.append("\n");
		buffer.append("Timeshift Amount    :" + timeshiftAmount);
		
		buffer.append("\nCommon Properties;\n");
		for(ReSpecThProperty property: commonProperties) {
			buffer.append(property.toString());
		}
		buffer.append("\nDataGroup Properties;\n");
		for(ReSpecThProperty property: dataGroupProperties) {
			buffer.append(property.toString());			
		}
		
		return buffer.toString();
	}
	public String getKind() {
		return kind;
	}


	public void setKind(String kind) {
		this.kind = kind;
	}


	public String getMode() {
		return mode;
	}


	public void setMode(String mode) {
		this.mode = mode;
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


	public String getTimeshiftTarget() {
		return timeshiftTarget;
	}


	public void setTimeshiftTarget(String timeshiftTarget) {
		this.timeshiftTarget = timeshiftTarget;
	}


	public String getTimeshiftType() {
		return timeshiftType;
	}


	public void setTimeshiftType(String timeshiftType) {
		this.timeshiftType = timeshiftType;
	}


	public String getTimeshiftAmount() {
		return timeshiftAmount;
	}


	public void setTimeshiftAmount(String timeshiftAmount) {
		this.timeshiftAmount = timeshiftAmount;
	}


	public String getExperimentType() {
		return experimentType;
	}


	public void setExperimentType(String experimentType) {
		this.experimentType = experimentType;
	}

}
