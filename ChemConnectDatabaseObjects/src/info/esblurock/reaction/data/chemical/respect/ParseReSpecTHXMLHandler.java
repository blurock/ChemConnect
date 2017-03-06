package info.esblurock.reaction.data.chemical.respect;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ParseReSpecTHXMLHandler extends DefaultHandler {

	final String experimentS = "experiment";
	final String kexperimentS = "kmeasurement";
	
	String elementBody;
	String minor;
	String major;
	ReSpecTHXMLFileBase experimentBase;
	ReSpecThXMLKExperiment kexperiment;
	ReSpecThXMLExperiment experiment;
	ReSpecThProperty property;
	boolean commonProperty;
	boolean dataGroupProperty;
	ReSpecThComponent component;
	boolean componentElement;
	String amountunits;
	ArrayList<String> dataGroupPoints;
	ReSpecThDataPoint point;
	boolean dataPointProperty;
	String value;
	
	public ParseReSpecTHXMLHandler() {
		experimentBase = null;
		dataGroupPoints = new ArrayList<String>();
	}
    public void startElement(String uri, String localName,
            String qName, Attributes attributes) throws SAXException {
    	if(qName.equalsIgnoreCase(experimentS)) {
    		experiment = new ReSpecThXMLExperiment();
    		experimentBase = experiment;
    	} else if(qName.equalsIgnoreCase(kexperimentS)) {
    		kexperiment = new ReSpecThXMLKExperiment();
    		experimentBase = kexperiment;
    	} else if(qName.equalsIgnoreCase(ReSpecTHXMLFileBase.fileVersionS)) {
    		minor = "";
    		major = "";
    	} else if(qName.equalsIgnoreCase(ReSpecTHXMLFileBase.ReSpecThVersionS)) {
    		minor = "";
    		major = "";
    	} else if(qName.equalsIgnoreCase(ReSpecTHXMLFileBase.bibliographyLinkS)) {
    		String reference = attributes.getValue(ReSpecTHXMLFileBase.preferredKey);
    		if(reference != null) {
    			experimentBase.setBibliographyLink(reference);
    		}    		
    	} else if(qName.equalsIgnoreCase(ReSpecThXMLKExperiment.reactionS)) {
    		if(kexperiment != null) {
    			String reaction = attributes.getValue(ReSpecThXMLKExperiment.preferredKeyS);
    			String bulkgas = attributes.getValue(ReSpecThXMLKExperiment.bulkgasS);
    			String order = attributes.getValue(ReSpecThXMLKExperiment.orderS);
    			kexperiment.setReaction(reaction);
    			kexperiment.setBulkgas(bulkgas);
    			kexperiment.setOrder(order);
    		} else {
    			throw new SAXException(ReSpecThXMLKExperiment.reactionS + 
    					" but not within" + ReSpecThXMLKExperiment.kexperimentS);
    		}
    	} else if(qName.equalsIgnoreCase(ReSpecThXMLExperiment.apparatusS)) {
    		if(experiment != null) {
    			experiment.setKind("");
    			experiment.setMode("");
    		} else {
    			throw new SAXException(ReSpecThXMLExperiment.apparatusS + 
    					" but not within" + ReSpecThXMLExperiment.experimentS);
    		}
    	} else if(qName.equalsIgnoreCase(ReSpecThXMLExperiment.ignitionTypeS)) {
    		if(experiment != null) {
    			String amount = attributes.getValue(ReSpecThXMLExperiment.amountS);
    			String operation = attributes.getValue(ReSpecThXMLExperiment.operationS);
    			String target = attributes.getValue(ReSpecThXMLExperiment.targetS);
    			String type = attributes.getValue(ReSpecThXMLExperiment.typeS);
    			String units = attributes.getValue(ReSpecThXMLExperiment.unitsS);
    			experiment.setIgnitionAmount(amount);
    			experiment.setIgnitionOperation(operation);
    			experiment.setIgnitionTarget(target);
    			experiment.setIgnitionType(type);
    			experiment.setIgnitionUnits(units);
    		} else {
    			throw new SAXException(ReSpecThXMLExperiment.ignitionTypeS + 
    					" but not within" + ReSpecThXMLExperiment.experimentS);
    		}
    	} else if(qName.equalsIgnoreCase(ReSpecThXMLExperiment.timeshiftS)) {
    		if(experiment != null) {
    			String target = attributes.getValue(ReSpecThXMLExperiment.targetS);
    			String type = attributes.getValue(ReSpecThXMLExperiment.typeS);
    			String amount = attributes.getValue(ReSpecThXMLExperiment.amountS);
    			experiment.setTimeshiftAmount(amount);
    			experiment.setTimeshiftTarget(target);
    			experiment.setTimeshiftType(type);
    		} else {
    			throw new SAXException(ReSpecThXMLExperiment.timeshiftS + 
    					" but not within" + ReSpecThXMLExperiment.experimentS);
    		}
   		
    	} else if(qName.equalsIgnoreCase(ReSpecThXMLExperiment.experimentTypeS)) {
    		if(experiment != null) {
    			
    		} else {
    			throw new SAXException(ReSpecThXMLExperiment.experimentTypeS + 
    					" but not within" + ReSpecThXMLExperiment.experimentS);
    		}
    	} else if(qName.equalsIgnoreCase(ReSpecTHXMLFileBase.commonPropertiesS)) {
    		commonProperty = true;
    		dataGroupProperty = false;
    	} else if(qName.equalsIgnoreCase(ReSpecTHXMLFileBase.dataGroupS)) {
    		commonProperty = false;
    		dataGroupProperty = true;
    		experimentBase.initializeDataPoints();
    	} else if(qName.equalsIgnoreCase(ReSpecTHXMLFileBase.propertyS)) {
    		property = new ReSpecThProperty();
    		String id = attributes.getValue(ReSpecTHXMLFileBase.idS);
    		String name = attributes.getValue(ReSpecTHXMLFileBase.nameS);
    		String label = attributes.getValue(ReSpecTHXMLFileBase.labelS);
    		String units = attributes.getValue(ReSpecTHXMLFileBase.unitsS);
    		String plotaxis = attributes.getValue(ReSpecTHXMLFileBase.plotAxisS);
    		String plotscale = attributes.getValue(ReSpecTHXMLFileBase.plotScaleS);
    		if(name.equalsIgnoreCase(ReSpecTHXMLFileBase.initialCompositionS)) {
    			property.initializeComponents();
    			componentElement = true;
    		}
    		property.setId(id);
    		property.setName(name);
    		property.setLabel(label);
    		property.setUnits(units);
    		property.setPlotAxis(plotaxis);
    		property.setPlotScale(plotscale);
    		value = null;
    	} else if(qName.equalsIgnoreCase(ReSpecTHXMLFileBase.componentS)) {
    		component = new ReSpecThComponent();
    	} else if(qName.equalsIgnoreCase(ReSpecTHXMLFileBase.amountS)) {
    		amountunits = attributes.getValue(ReSpecTHXMLFileBase.unitsS);
    		component.setAmountunits(amountunits);
    	} else if(qName.equalsIgnoreCase(ReSpecTHXMLFileBase.speciesLinkS)) {
    		String speciesLink = attributes.getValue(ReSpecTHXMLFileBase.preferredKey);
    		if(property != null) {
    			property.setSpeciesLink(speciesLink);
    		} 
    		if(component != null) {
    			component.setSpeciesLink(speciesLink);
    		}
    	} else if(qName.equalsIgnoreCase(ReSpecTHXMLFileBase.dataPointS)) {
    		point = new ReSpecThDataPoint(dataGroupPoints.size());
    		dataPointProperty = true;
    	}
    }
    
    public void endElement(String uri, String localName,
    		String qName) throws SAXException {
    	if(qName.equalsIgnoreCase(ReSpecTHXMLFileBase.fileAuthorS)) {
    		experimentBase.setFileAuthor(elementBody);
    	} else if(qName.equalsIgnoreCase(ReSpecTHXMLFileBase.minorS)) {
    		minor = elementBody;
    	} else if(qName.equalsIgnoreCase(ReSpecTHXMLFileBase.majorS)) {
    		major = elementBody;
    	} else if(qName.equalsIgnoreCase(ReSpecTHXMLFileBase.fileVersionS)) {
    		experimentBase.setFileVersion(formVersion());
    	} else if(qName.equalsIgnoreCase(ReSpecTHXMLFileBase.ReSpecThVersionS)) {
    		experimentBase.setReSpecThVersion(formVersion());
    	} else if(qName.equalsIgnoreCase(ReSpecThXMLExperiment.kindS)) {
    		experiment.setKind(elementBody);
    	} else if(qName.equalsIgnoreCase(ReSpecThXMLExperiment.modeS)) {
    		experiment.setMode(elementBody);
    	} else if(qName.equalsIgnoreCase(ReSpecThXMLExperiment.experimentTypeS)) {
    		experiment.setExperimentType(elementBody);
    	} else if(qName.equalsIgnoreCase(ReSpecTHXMLFileBase.propertyS)) {
    		if(dataGroupProperty) {
    			dataGroupPoints.add(property.getId());
    		}
    		if(experiment != null) {
    			if(commonProperty) {
    				property.setValue(value);
    				experiment.addCommonProperty(property);
     			} else {
    				experiment.addDataGroupProperty(property);
    			}
    		} else if(kexperiment != null) {
    			if(commonProperty) {
    				property.setValue(value);
    				kexperiment.addCommonProperty(property);
    			} else {
    				kexperiment.addDataGroupProperty(property);
    			}
    			
    		}
    	} else if(qName.equalsIgnoreCase(ReSpecTHXMLFileBase.valueS)) {
    		value = elementBody;
    	} else if(qName.equalsIgnoreCase(ReSpecTHXMLFileBase.amountS)) {
    		component.setAmount(elementBody);
    	} else if(qName.equalsIgnoreCase(ReSpecTHXMLFileBase.componentS)) {
    		property.addComponent(component);
    	} else if(qName.equalsIgnoreCase(ReSpecTHXMLFileBase.commonPropertiesS)) {
    		commonProperty = false;
    	} else if(qName.equalsIgnoreCase(ReSpecTHXMLFileBase.dataGroupS)) {
    		dataGroupProperty = false;
    	} else if(qName.equalsIgnoreCase(ReSpecTHXMLFileBase.dataPointS)) {
    		dataPointProperty = false;
    		experimentBase.addDataPoint(point);
    	} else {
    		if(dataPointProperty) {
    			int i= 0;
    			for(String pointlabel : dataGroupPoints) {
    				if(qName.equalsIgnoreCase(pointlabel)) {
    					point.addValue(elementBody,i);
    				} else {
    					i++;
    				}
    				
    			}
    		}
    	}
    }
    
    public void characters(char ch[], int start, int length) throws SAXException {
    	elementBody = new String(ch,start,length);
    }
    
    protected String formVersion() {
    	String version = major + "." + minor;
    	return version;
    }
	public ReSpecTHXMLFileBase getExperimentBase() {
		return experimentBase;
	}
    
}
