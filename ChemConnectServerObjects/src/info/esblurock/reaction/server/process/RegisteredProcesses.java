package info.esblurock.reaction.server.process;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import info.esblurock.reaction.server.process.DataProcesses;
import info.esblurock.reaction.data.DatabaseObject;
import info.esblurock.reaction.data.transaction.ProcessInProgress;
import info.esblurock.reaction.data.transaction.TransactionInfo;
import info.esblurock.reaction.server.queries.QueryBase;
import info.esblurock.reaction.server.queries.TransactionInfoQueries;

/**
 * RegisteredProcesses
 * 
 * @author edwardblurock
 *
 */
public class RegisteredProcesses {

	/**
	 * toBeProcessed Uses the {@link TransactionInfo} information to determine
	 * which data structures are present. Then, cycling through the
	 * {@link DataProcesses} input and output requirements the list of processes
	 * that be run are returned.
	 * 
	 * A process is valid if:
	 * <ul>
	 * <li>The set of input data structures are complete
	 * <li>The set of output data structures are NOT complete
	 * <ul>
	 * 
	 * @param user
	 *            The name of the user
	 * @param keyword:
	 *            The keyword of the dataset
	 * @return The list of process names
	 * @throws IOException:
	 */
	static public ArrayList<String> toBeProcessed(String user, String keyword) throws IOException {
		ArrayList<String> completed = getCompletedTransactions(user, keyword);
		System.out.println("Completed: \n" + completed);
		ArrayList<String> processes = toBeProcessed(user, completed);
		return processes;
	}

	/**
	 * 
	 * @param completed:
	 *            The set of completed data structures
	 * @return The set of processes
	 * @throws IOException
	 */
	static private ArrayList<String> toBeProcessed(String user, ArrayList<String> completed) throws IOException {
/*
		String progressclassname = ProcessInProgress.class.getCanonicalName();
		List<DatabaseObject> dataset = QueryBase.getUserDatabaseObjectsFromSingleProperty(
				progressclassname, user, "user", user);
		for (DatabaseObject object : dataset) {

		}
*/
		ArrayList<String> valid = new ArrayList<String>();
		DataProcesses[] processes = DataProcesses.values();
		for (int i = 0; i < processes.length; i++) {
			DataProcesses processEnum = processes[i];
			ProcessBase process = processEnum.getEmptyProcess();
			ArrayList<String> input = process.getInputTransactionObjectNames();
			if (input.size() > 0) {
				if (completed.containsAll(input)) {
					ArrayList<String> output = process.getOutputTransactionObjectNames();
					if (!completed.containsAll(output)) {
						valid.add(process.getClass().getName());
					}
				}
			}
		}
		//valid = removeInProgress(dataset, valid);
		return valid;
	}

	private static ArrayList<String> removeInProgress(List<DatabaseObject> dataset, ArrayList<String> valid) {
		if (dataset.size() > 0) {
			ArrayList<String> inprogressS = new ArrayList<String>();
			for (DatabaseObject object : dataset) {
				ProcessInProgress inprogress = (ProcessInProgress) object;
				String process = inprogress.getProcessName();
				inprogressS.add(process);
			}
			System.out.println("removeInProgress: \n" + inprogressS + "\n" + valid);
			valid.removeAll(inprogressS);
		}
		return null;
	}

	/**
	 * getCompletedTransactions The set of data structures that have been
	 * created for the user and keyword
	 * 
	 * The user and the keyword are used to find the set of data structures that
	 * have been created thusfar.
	 * 
	 * @param user
	 *            The user name (creator)
	 * @param keyword:
	 *            The string representing the entire data set
	 * @return The names of the data structures
	 * @throws IOException
	 */
	static private ArrayList<String> getCompletedTransactions(String user, String keyword) throws IOException {
		ArrayList<String> completed = new ArrayList<String>();
		List<DatabaseObject> active = TransactionInfoQueries.getTransactionFromKeywordAndUser(user, keyword);
		for (DatabaseObject obj : active) {
			TransactionInfo info = (TransactionInfo) obj;
			if (info.getStoredObjectKey() != null) {
				String trans = info.getTransactionObjectType();
				completed.add(trans);
			}
		}
		return completed;
	}
}
