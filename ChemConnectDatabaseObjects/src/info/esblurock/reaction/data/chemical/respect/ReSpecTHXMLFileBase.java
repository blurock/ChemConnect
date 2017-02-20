package info.esblurock.reaction.data.chemical.respect;

import java.io.Serializable;
import java.util.ArrayList;

public class ReSpecTHXMLFileBase implements Serializable {

	private static final long serialVersionUID = 1L;

	public static String fileAuthorS = "fileAuthor";
	public static String fileVersionS = "fileVersion";
	public static String ReSpecThVersionS = "ReSpecThVersion";
	public static String bibliographyLinkS = "bibliographyLink";
	public static String commonPropertiesS = "commonProperties";
	public static String dataGroupS = "dataGroup";
	public static String majorS = "major";
	public static String minorS = "minor";
	public static String preferredKey = "preferredKey";
	public static String propertyS = "property";
	public static String idS = "id";
	public static String labelS = "label";
	public static String nameS = "name";
	public static String unitsS = "units";
	public static String valueS = "value";
	public static String componentS = "component";
	public static String speciesLinkS = "speciesLink";
	public static String initialCompositionS = "initial composition";
	public static String amountS = "amount";
	public static String dataPointS = "dataPoint";
	
	
	protected String xmlExperimentCategory;
	protected String fileAuthor;
	protected String fileVersion;
	protected String ReSpecThVersion;
	protected String bibliographyLink;
	protected ArrayList<ReSpecThDataPoint> dataPoints;

	protected ArrayList<ReSpecThProperty> commonProperties;
	protected ArrayList<ReSpecThProperty> dataGroupProperties;

	public ReSpecTHXMLFileBase() {
	}
	
	public void initializeDataPoints() {
		dataPoints = new ArrayList<ReSpecThDataPoint>();
	}
	public String getFileAuthor() {
		return fileAuthor;
	}
	public void setFileAuthor(String fileAuthor) {
		this.fileAuthor = fileAuthor;
	}
	public String getFileVersion() {
		return fileVersion;
	}
	public void setFileVersion(String fileVersion) {
		this.fileVersion = fileVersion;
	}
	public String getReSpecThVersion() {
		return ReSpecThVersion;
	}
	public void setReSpecThVersion(String reSpecThVersion) {
		ReSpecThVersion = reSpecThVersion;
	}
	public String getBibliographyLink() {
		return bibliographyLink;
	}
	public void setBibliographyLink(String bibliographyLink) {
		this.bibliographyLink = bibliographyLink;
	}
	public String getXmlExperimentCategory() {
		return xmlExperimentCategory;
	}
	public void setXmlExperimentCategory(String xmlExperimentCategory) {
		this.xmlExperimentCategory = xmlExperimentCategory;
	}

	public void addCommonProperty(ReSpecThProperty property) {
		commonProperties.add(property);
	}
	public void addDataGroupProperty(ReSpecThProperty property) {
		dataGroupProperties.add(property);
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Type             : " + xmlExperimentCategory);
		buffer.append("\n");
		buffer.append("File Author      : " + fileAuthor);
		buffer.append("\n");
		buffer.append("File Version     : " + fileVersion);
		buffer.append("\n");
		buffer.append("ReSpecTh Version : " + ReSpecThVersion);
		buffer.append("\n");
		buffer.append("Reference        : " + bibliographyLink);
		buffer.append("\n");
		
		buffer.append("Data Points (" + dataPoints.size() + ")\n");
		for(ReSpecThDataPoint point : dataPoints) {
			buffer.append(point.toString());
		}
		return buffer.toString();
	}

	public void addDataPoint(ReSpecThDataPoint point) {
		dataPoints.add(point);
	}

	public ArrayList<ReSpecThDataPoint> getDataPoints() {
		return dataPoints;
	}

	public ArrayList<ReSpecThProperty> getCommonProperties() {
		return commonProperties;
	}

	public ArrayList<ReSpecThProperty> getDataGroupProperties() {
		return dataGroupProperties;
	}
	
}
