package info.esblurock.reaction.data.chemical.respect;

import java.io.Serializable;

public class ReSpecThComponent implements Serializable {
	
	String amount;
	String amountunits;
	String speciesLink;
	public ReSpecThComponent() {
	}
	
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("Component:\n");
		buffer.append("speciesLink: " + speciesLink);
		buffer.append("\n");
		buffer.append("amount     : " + amount + " " + amountunits);
		buffer.append("\n");
		
		return buffer.toString();
	}
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getSpeciesLink() {
		return speciesLink;
	}
	public void setSpeciesLink(String speciesLink) {
		this.speciesLink = speciesLink;
	}


	public String getAmountunits() {
		return amountunits;
	}


	public void setAmountunits(String amountunits) {
		this.amountunits = amountunits;
	}
	
	

}
