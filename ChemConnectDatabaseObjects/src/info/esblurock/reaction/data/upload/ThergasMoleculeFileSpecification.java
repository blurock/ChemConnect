package info.esblurock.reaction.data.upload;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
@Inheritance(customStrategy = "complete-table")
public class ThergasMoleculeFileSpecification extends FileSourceSpecification {
	public ThergasMoleculeFileSpecification() {
		super();
	}
}
