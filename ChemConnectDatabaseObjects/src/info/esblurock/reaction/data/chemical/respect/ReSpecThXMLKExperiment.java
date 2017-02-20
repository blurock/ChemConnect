package info.esblurock.reaction.data.chemical.respect;

import java.io.Serializable;
import java.util.ArrayList;

public class ReSpecThXMLKExperiment extends ReSpecTHXMLFileBase {

	private static final long serialVersionUID = 1L;

	public static String kexperimentS = "kexperiment";
	public static String reactionS = "reaction";
	public static String preferredKeyS = "preferredKey";
	public static String orderS = "order";
	public static String bulkgasS = "bulkgas";
	
	String reaction;
	String order;
	String bulkgas;
	
	public ReSpecThXMLKExperiment() {
		xmlExperimentCategory = kexperimentS;
		commonProperties = new ArrayList<ReSpecThProperty>();
		dataGroupProperties = new ArrayList<ReSpecThProperty>();
	}
	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getBulkgas() {
		return bulkgas;
	}

	public void setBulkgas(String bulkgas) {
		this.bulkgas = bulkgas;
	}
	public String getReaction() {
		return reaction;
	}

	public void setReaction(String reaction) {
		this.reaction = reaction;
	}


	public String toString() {
		String base = super.toString();
		StringBuffer buffer = new StringBuffer();
		buffer.append(base);
		buffer.append("Reaction : " + reaction);
		buffer.append("\n");
		buffer.append("Order    : " + order);
		buffer.append("\n");
		buffer.append("Bulkgas  : " + bulkgas);
		buffer.append("\n");
		
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

	
}
