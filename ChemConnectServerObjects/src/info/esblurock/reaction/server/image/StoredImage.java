package info.esblurock.reaction.server.image;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Blob;

import info.esblurock.reaction.data.DatabaseObject;

@SuppressWarnings("serial")
@PersistenceCapable
public class StoredImage extends DatabaseObject {
    @Persistent
    private String name;

    @Persistent
    Blob image;

    public StoredImage() { }
    public StoredImage(String name, Blob image) {
        this.name = name; 
        this.image = image;
    }

    // JPA getters and setters and empty contructor
    // ...
    public Blob getImage() { 
    	return image; 
    }
    public void setImage(Blob image) { 
    	this.image = image; 
    }

}
