package info.esblurock.reaction.data;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class DataNumericalConstant extends DatabaseObject {

	@Persistent
	String constantName;
	@Persistent
	Double constantValue;
	@Persistent
	String constantUnits;
	
	
}
