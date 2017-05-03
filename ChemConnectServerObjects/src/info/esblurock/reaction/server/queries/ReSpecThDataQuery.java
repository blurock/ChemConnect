package info.esblurock.reaction.server.queries;

import java.io.IOException;
import java.util.List;

import info.esblurock.reaction.data.DatabaseObject;
import info.esblurock.reaction.data.chemical.respect.database.ExperimentCommonPropertyDatabaseObject;
import info.esblurock.reaction.data.chemical.respect.database.ExperimentDataPointPropertyDatabaseObject;
import info.esblurock.reaction.data.chemical.respect.database.ExperimentDescriptionExperiment;
import info.esblurock.reaction.data.chemical.respect.database.ExperimentDescriptionIgnitionConstants;
import info.esblurock.reaction.data.chemical.respect.database.ExperimentDescriptionKineticConstants;
import info.esblurock.reaction.data.chemical.respect.database.ExperimentDescriptionTimeProfile;
import info.esblurock.reaction.data.chemical.respect.database.ExperimentInitialCompositionDatabaseObject;
import info.esblurock.reaction.data.chemical.respect.database.ExperimentNumericalDataPointDatabaseObject;

public class ReSpecThDataQuery extends QueryBase {
	
	public static List<DatabaseObject> findExperimentDescriptionKineticConstants(String keyword) throws IOException {
		String respectKeyword = "dataset";
		String classname = ExperimentDescriptionKineticConstants.class.getName();
		return getDatabaseObjectsFromSingleProperty(classname, respectKeyword, keyword);
	}
	
	public static List<DatabaseObject> findExperimentDescriptionTimeProfile(String keyword) throws IOException {
		String respectKeyword = "dataset";
		String classname = ExperimentDescriptionTimeProfile.class.getName();
		return getDatabaseObjectsFromSingleProperty(classname, respectKeyword, keyword);
	}
	
	public static List<DatabaseObject> findExperimentDescriptionIgnitionConstants(String keyword) throws IOException {
		String respectKeyword = "dataset";
		String classname = ExperimentDescriptionIgnitionConstants.class.getName();
		return getDatabaseObjectsFromSingleProperty(classname, respectKeyword, keyword);
	}
	
	public static List<DatabaseObject> findExperimentDescriptionExperiment(String keyword) throws IOException {
		String respectKeyword = "dataset";
		String classname = ExperimentDescriptionExperiment.class.getName();
		return getDatabaseObjectsFromSingleProperty(classname, respectKeyword, keyword);
	}
	
	public static List<DatabaseObject> findExperimentNumericalDataPointDatabaseObject(String keyword) throws IOException {
		String respectKeyword = "dataset";
		String classname = ExperimentNumericalDataPointDatabaseObject.class.getName();
		return getDatabaseObjectsFromSingleProperty(classname, respectKeyword, keyword);
	}
	
	public static List<DatabaseObject> findExperimentInitialCompositionDatabaseObject(String keyword) throws IOException {
		String respectKeyword = "dataset";
		String classname = ExperimentInitialCompositionDatabaseObject.class.getName();
		return getDatabaseObjectsFromSingleProperty(classname, respectKeyword, keyword);
	}
	
	public static List<DatabaseObject> findExperimentCommonPropertyDatabaseObject(String keyword) throws IOException {
		String respectKeyword = "dataset";
		String classname = ExperimentCommonPropertyDatabaseObject.class.getName();
		return getDatabaseObjectsFromSingleProperty(classname, respectKeyword, keyword);
	}
	
	public static List<DatabaseObject> findExperimentDataPointPropertyDatabaseObject(String keyword) throws IOException {
		String respectKeyword = "dataset";
		String classname = ExperimentDataPointPropertyDatabaseObject.class.getName();
		return getDatabaseObjectsFromSingleProperty(classname, respectKeyword, keyword);
	}
	
}
