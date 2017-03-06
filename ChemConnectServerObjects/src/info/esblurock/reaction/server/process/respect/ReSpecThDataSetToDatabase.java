package info.esblurock.reaction.server.process.respect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import info.esblurock.reaction.data.chemical.respect.ReSpecTHXMLFileBase;
import info.esblurock.reaction.data.chemical.respect.ReSpecThComponent;
import info.esblurock.reaction.data.chemical.respect.ReSpecThDataPoint;
import info.esblurock.reaction.data.chemical.respect.ReSpecThProperty;
import info.esblurock.reaction.data.chemical.respect.ReSpecThXMLExperiment;
import info.esblurock.reaction.data.chemical.respect.ReSpecThXMLKExperiment;
import info.esblurock.reaction.data.chemical.respect.database.ExperimentCommonPropertyDatabaseObject;
import info.esblurock.reaction.data.chemical.respect.database.ExperimentDataPointPropertyDatabaseObject;
import info.esblurock.reaction.data.chemical.respect.database.ExperimentDescriptionExperiment;
import info.esblurock.reaction.data.chemical.respect.database.ExperimentDescriptionIgnitionConstants;
import info.esblurock.reaction.data.chemical.respect.database.ExperimentDescriptionKineticConstants;
import info.esblurock.reaction.data.chemical.respect.database.ExperimentDescriptionTimeProfile;
import info.esblurock.reaction.data.chemical.respect.database.ExperimentInitialCompositionDatabaseObject;
import info.esblurock.reaction.data.chemical.respect.database.ExperimentNumericalDataPointDatabaseObject;
import info.esblurock.reaction.data.store.StoreObject;
import info.esblurock.reaction.data.transaction.respect.ReSpecThDataSetToDatabaseTransaction;
import info.esblurock.reaction.server.process.ProcessBase;
import info.esblurock.reaction.server.process.ProcessInputSpecificationsBase;

public class ReSpecThDataSetToDatabase extends ProcessBase {
	
	static public String HighPressureS = "HP";
	static public String LowPressureS = "LP";

	ReSpecThDataSetToDatabaseTransaction toDatabaseTransaction;
	String toDatabaseS;
	
	ArrayList<ReSpecTHXMLFileBase> experimentdata;
	ArrayList<String> fileNames;
	public ReSpecThDataSetToDatabase() {
		super();
	}
	
	public ReSpecThDataSetToDatabase(ProcessInputSpecificationsBase input) {
		super(input);
		ReSpecThDataBaseInputSpecification spec = (ReSpecThDataBaseInputSpecification) input;
		experimentdata = spec.getExperimentdata();
		fileNames = spec.getFileNames();
	}
	
	@Override
	public void initialization() {
		toDatabaseS = "info.esblurock.reaction.data.transaction.respect.ReSpecThDataSetToDatabaseTransaction";
	}

	@Override
	protected String getProcessName() {
		return "ReSpecThDataSetToDatabase";
	}

	@Override
	protected String getProcessDescription() {
		return "Store a parsed ReSpect XML file to the database";
	}

	@Override
	protected ArrayList<String> getInputTransactionObjectNames() {
		ArrayList<String> input = new ArrayList<String>();
		return input;
	}

	@Override
	protected ArrayList<String> getOutputTransactionObjectNames() {
		ArrayList<String> output = new ArrayList<String>();
		output.add(toDatabaseS);
		return output;
	}
	protected void initializeOutputObjects() throws IOException {
		super.initializeOutputObjects();
		System.out.println("user:              " + user);
		System.out.println("outputSourceCode:  " + outputSourceCode);
		System.out.println("keyword:           " + keyword);
		System.out.println("fileNames:         " + fileNames);
		toDatabaseTransaction = (ReSpecThDataSetToDatabaseTransaction)
				new ReSpecThDataSetToDatabaseTransaction(user, outputSourceCode, keyword,fileNames);
		objectOutputs.add(toDatabaseTransaction);
		System.out.println("objectOutputs: " + objectOutputs.size());
	}
	@Override
	protected void createObjects() throws IOException {
		StoreObject store = new StoreObject(user, keyword, outputSourceCode);
		Iterator<String> iter = fileNames.iterator();
		for(ReSpecTHXMLFileBase parsed : experimentdata) {
			String filename = iter.next();
			int dataCount = parsed.getDataPoints().size();
			Integer dataCountI = new Integer(dataCount);	
			System.out.println("XmlExperimentCategory:  '" + parsed.getXmlExperimentCategory() + 
					"' (" + ReSpecThXMLExperiment.experimentS + ")");
			if(parsed.getXmlExperimentCategory().compareTo(ReSpecThXMLKExperiment.kexperimentS) == 0) {
				System.out.println("Category: " + ReSpecThXMLKExperiment.kexperimentS);
				ReSpecThXMLKExperiment exp = (ReSpecThXMLKExperiment) parsed;
				String reaction = exp.getReaction();
				String information = "";
				if(reaction.startsWith(HighPressureS)) {
					information = HighPressureS;
					int index = reaction.indexOf(" ");
					reaction.substring(index+1);
				} else if(reaction.startsWith(LowPressureS)) {
					information = LowPressureS;
					int index = reaction.indexOf(" ");
					reaction.substring(index+1);
				}
				
				ExperimentDescriptionKineticConstants obj = new ExperimentDescriptionKineticConstants(
						keyword,filename,outputSourceCode,
						exp.getBibliographyLink(),
						exp.getXmlExperimentCategory(),reaction,
						information,exp.getBulkgas(),exp.getOrder());
				System.out.println("Category: ExperimentDescriptionKineticConstants");
				obj.setNumberOfRows(dataCountI);
				store.store(obj);
			} else if(parsed.getXmlExperimentCategory().compareTo(ReSpecThXMLExperiment.experimentS) == 0) {
				ReSpecThXMLExperiment exp = (ReSpecThXMLExperiment) parsed;
				System.out.println("Category: " + ReSpecThXMLExperiment.experimentS);
				if(exp.getTimeshiftTarget() != null) {
					Double amount = new Double(exp.getTimeshiftAmount());
					ExperimentDescriptionTimeProfile obj = new ExperimentDescriptionTimeProfile(
							keyword, filename, outputSourceCode,
							exp.getBibliographyLink(),
							exp.getXmlExperimentCategory(),
							exp.getKind(), exp.getMode(),
							exp.getTimeshiftTarget(),exp.getTimeshiftType(),amount);
					obj.setNumberOfRows(dataCountI);
					System.out.println("Category: ExperimentDescriptionTimeProfile");
					store.store(obj);
				}
				if(exp.getIgnitionTarget() != null) {
					ExperimentDescriptionIgnitionConstants obj = new ExperimentDescriptionIgnitionConstants(
							keyword, filename, outputSourceCode,
							exp.getBibliographyLink(),
							exp.getXmlExperimentCategory(),
							exp.getKind(), exp.getMode(),
							exp.getIgnitionTarget(), exp.getIgnitionType(), 
							exp.getIgnitionAmount(),exp.getIgnitionUnits(), exp.getIgnitionOperation());
					obj.setNumberOfRows(dataCountI);
					System.out.println("Category: ExperimentDescriptionIgnitionConstants");
					store.store(obj);
				}
				if(exp.getIgnitionTarget() == null && exp.getTimeshiftTarget() == null) {
					ExperimentDescriptionExperiment obj = new ExperimentDescriptionExperiment(
							keyword, filename, outputSourceCode,
							exp.getBibliographyLink(),
							exp.getXmlExperimentCategory(),
							exp.getKind(), exp.getMode());
					obj.setNumberOfRows(dataCountI);
					System.out.println("Category: ExperimentDescriptionExperiment");
					store.store(obj);
				}
			}
	
			ArrayList<ReSpecThDataPoint> dataPoints = parsed.getDataPoints();
			int count = 0;
			for(ReSpecThDataPoint datapoint : dataPoints) {
				Integer rowI = new Integer(count++);
				Iterator<String> valueiter = datapoint.getValues().iterator();
				for(ReSpecThProperty prop : parsed.getDataGroupProperties()) {
					Double valueD = new Double(valueiter.next());
					ExperimentNumericalDataPointDatabaseObject obj = new
							ExperimentNumericalDataPointDatabaseObject(keyword,filename,outputSourceCode,
									prop.getLabel(),valueD,rowI);
					store.store(obj);
				}
			}
			ArrayList<ReSpecThProperty> commonProperties = parsed.getCommonProperties();
			for(ReSpecThProperty property : commonProperties) {
				if(property.getName().compareTo(ReSpecTHXMLFileBase.initialCompositionS) == 0) {
					for(ReSpecThComponent component : property.getComponents()) {
						Double value = new Double(component.getAmount());
					ExperimentInitialCompositionDatabaseObject obj = new
							ExperimentInitialCompositionDatabaseObject(
									keyword,filename,outputSourceCode,
									component.getSpeciesLink(),value,component.getAmountunits());
					store.store(obj);
					}
				} else {
				ExperimentCommonPropertyDatabaseObject obj = new 
						ExperimentCommonPropertyDatabaseObject(keyword,filename,outputSourceCode,property);
				store.store(obj);
				}
			}
			for(ReSpecThProperty prop : parsed.getDataGroupProperties()) {
				ExperimentDataPointPropertyDatabaseObject obj = new ExperimentDataPointPropertyDatabaseObject(
						keyword,filename,outputSourceCode,
						prop.getId(),prop.getLabel(),
						prop.getPlotAxis(),prop.getPlotScale(),
						prop.getName(), prop.getUnits(),prop.getDescription());
				store.store(obj);
			}

		}
		store.finish();
	}

}
