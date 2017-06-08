package info.esblurock.reaction.server.image;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;

import info.esblurock.reaction.data.UserDTO;
import info.esblurock.reaction.server.utilities.ContextAndSessionUtilities;
import info.esblurock.reaction.server.utilities.ManageDataSourceIdentification;

import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;

/**
 * @author Ikai Lan
 * 
 *         This is the servlet that handles the callback after the blobstore
 *         upload has completed. After the blobstore handler completes, it POSTs
 *         to the callback URL, which must return a redirect. We redirect to the
 *         GET portion of this servlet which sends back a key. GWT needs this
 *         Key to make another request to get the image serving URL. This adds
 *         an extra request, but the reason we do this is so that GWT has a Key
 *         to work with to manage the Image object. Note the content-type. We
 *         *need* to set this to get this to work. On the GWT side, we'll take
 *         this and show the image that was uploaded.
 * 
 */
@SuppressWarnings("serial")
public class UploadServlet extends HttpServlet {
	private static final Logger log = Logger.getLogger(UploadServlet.class.getName());

	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
		List<BlobKey> blobKey = blobs.get("image");

		HttpSession session = req.getSession();
		ContextAndSessionUtilities util = new ContextAndSessionUtilities(getServletContext(), session);
		UserDTO user = util.getUserInfo();

		if (blobKey == null) {
			System.out.println("blobkey is null");
		} else {

			ImagesService imagesService = ImagesServiceFactory.getImagesService();
			//ServletOutputStream out = res.getOutputStream();
			//res.setBufferSize(32768);
			//res.setContentType("text/html; charset=UTF-8");
			//res.setCharacterEncoding("UTF-8");
			String outputSourceCode = ManageDataSourceIdentification.getDataSourceIdentification(user.getName());

			//boolean notfirst = false;
			for (BlobKey key : blobKey) {
				ServingUrlOptions options = ServingUrlOptions.Builder.withBlobKey(key);
				// Get the image serving URL
				String imageUrl = imagesService.getServingUrl(options);
				/*
				if (notfirst) {
					out.print(",");
				} else {
					notfirst = true;
				}
				out.println(imageUrl);
*/
				// For the sake of clarity, we'll use low-level entities
				Entity uploadedImage = new Entity("UploadedImage");
				uploadedImage.setProperty("blobKey", key);
				// uploadedImage.setProperty(UploadedImage.CREATED_AT, new
				// Date());

				// Highly unlikely we'll ever filter on this property
				// uploadedImage.setUnindexedProperty(UploadedImage.SERVING_URL,
				// imageUrl);

				DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
				datastore.put(uploadedImage);

				System.out.println("servername: " + req.getServerName());
				//String url = "http://" + req.getServerName() + ":" + req.getServerPort() + "/upload?imageUrl="
				//		+ imageUrl;
				String url = "/upload?imageUrl=" + imageUrl;
				System.out.println("Redirect url: " + url);
				res.setHeader("Content-Type", "text/html");
				res.sendRedirect(url);
				System.out.println("UploadServlet doPost:   /upload?imageUrl=" + imageUrl);

				System.out.println("UploadServlet doPost: " + imageUrl);
				// res.getWriter().write(imageUrl);
				// res.getWriter().flush();
				/*
				 * ServletOutputStream out = null;
				 * res.setContentType("text/html; charset=UTF-8");
				 * res.setCharacterEncoding("UTF-8"); out =
				 * res.getOutputStream(); out.print(imageUrl); out.flush();
				 */

			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("UploadServlet doGet: ");
		String imageUrl = req.getParameter("imageUrl");
		//resp.setHeader("Content-Type", "text/html");
		// This is a bit hacky, but it'll work. We'll use this key in an Async
		// service to
		// fetch the image and image information
		//imageUrl = "<pre>" + imageUrl +  "</pre>";
		resp.setContentType("text/html");
		resp.setHeader("Pragma", "No-cache");
		resp.setDateHeader("Expires", 0);
		resp.setHeader("Cache-Control", "no-cache");
		resp.getWriter().println(imageUrl);
		System.out.println("UploadServlet doGet: " + imageUrl);

		//ServletOutputStream out = null;
		//resp.setContentType("text/html; charset=UTF-8");
		//resp.setCharacterEncoding("UTF-8");
		//out = resp.getOutputStream();
		//out.print(imageUrl);
		//out.flush();

	}
}