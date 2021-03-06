package info.esblurock.reaction.server.image;

import java.io.IOException;
import java.io.InputStream;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesServiceFactory;

import info.esblurock.reaction.data.PMF;

public class UploadImageService  extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Your upload handle would look like
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("UploadImageService do Post");
		try {
	    // Get the image representation
	    ServletFileUpload upload = new ServletFileUpload();
	    FileItemIterator iter = upload.getItemIterator(req);
	    FileItemStream imageItem = iter.next();
	    InputStream imgStream = imageItem.openStream();

	    
	    // construct our entity objects
	    Blob imageBlob = new Blob(IOUtils.toByteArray(imgStream));
	    StoredImage myImage = new StoredImage(imageItem.getName(), imageBlob);
	    
	    

	    // persist image
	    PersistenceManager pm = PMF.get().getPersistenceManager();
	    pm.makePersistent(myImage);
	    pm.close();

	    // respond to query
	    res.setContentType("text/plain");
	    res.getOutputStream().write("OK!".getBytes());
		} catch(IOException ex) {
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}
}
