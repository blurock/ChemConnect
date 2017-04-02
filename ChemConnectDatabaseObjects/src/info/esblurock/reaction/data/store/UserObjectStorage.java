package info.esblurock.reaction.data.store;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import info.esblurock.reaction.data.DatabaseObject;

@SuppressWarnings("serial")
@PersistenceCapable
public class UserObjectStorage extends DatabaseObject {

	   @Persistent
	    String user;
	   @Persistent
	   String prefixPath;
	   @Persistent
	   String postfixPath;
		/** The stored object type (used in path). */
	   @Persistent
	    String type;
	   @Persistent
	    String shortdescription;
		/** The stored object key. */
		@Persistent
		String storedObjectKey;
		@Persistent
		String storedObjectType;
		
		public UserObjectStorage() {
		}
		public UserObjectStorage(String user, 
				String prefixPath, String type, String postfixPath,
				String shortdescription,
				String storedObjectKey,
				String storedObjectType) {
			super();
			this.user = user;
			this.prefixPath = prefixPath;
			this.postfixPath = postfixPath;
			this.type = type;
			this.storedObjectKey = storedObjectKey;
			this.shortdescription = shortdescription;
			this.storedObjectType = storedObjectType;
		}
		public void setUser(String user) {
			this.user = user;
		}
		public String getUser() {
			return user;
		}
		public String getPrefixPath() {
			return prefixPath;
		}
		public String getPostfixPath() {
			return postfixPath;
		}
		public String getType() {
			return type;
		}
		public String getStoredObjectKey() {
			return storedObjectKey;
		}
		public String getShortdescription() {
			return shortdescription;
		}
		public void setShortdescription(String shortdescription) {
			this.shortdescription = shortdescription;
		}
		public String getStoredObjectType() {
			return storedObjectType;
		}

		
}
