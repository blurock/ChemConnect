package info.esblurock.reaction.data.chemical.respect;

import java.io.Serializable;
import java.util.ArrayList;

public class ReSpecThProperty implements Serializable {

	private static final long serialVersionUID = 1L;
	
	String id;
	String label;
	String name;
	String units;
	String value;
	String component;
	String speciesLink;
	
	ArrayList<ReSpecThComponent> components;
	boolean initialComponents;
	public ReSpecThProperty() {
		initialComponents = false;
	}
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("Property");
		buffer.append("\n");
		buffer.append("\tid         : " + id);
		buffer.append("\n");
		buffer.append("\tlabel      :" + label);
		buffer.append("\n");
		buffer.append("\tname       :" + name);
		buffer.append("\n");
		buffer.append("\tunits      :" + units);
		buffer.append("\n");
		buffer.append("\tvalue      :" + value);
		buffer.append("\n");
		buffer.append("\tcomponent  :" + component);
		buffer.append("\n");
		buffer.append("\tspeciesLink:" + speciesLink);
		buffer.append("\n");
		
		if(initialComponents) {
			buffer.append("Components\n");
			for(ReSpecThComponent component : components) {
				buffer.append(component.toString());
			}
		}
		
		return buffer.toString();
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public String getSpeciesLink() {
		return speciesLink;
	}
	public void setSpeciesLink(String speciesLink) {
		this.speciesLink = speciesLink;
	}
	public void initializeComponents() {
		components = new ArrayList<ReSpecThComponent>();
		initialComponents = true;
	}
	public void addComponent(ReSpecThComponent component) {
		components.add(component);
	}
	

}
