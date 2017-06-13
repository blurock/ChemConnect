package info.esblurock.reaction.client.async;

import com.google.gwt.user.client.rpc.AsyncCallback;

import info.esblurock.reaction.data.image.ImageServiceInformation;


public interface UserImageServiceAsync {

	void getBlobstoreUploadUrl(String keywordName, boolean uploadService,
			AsyncCallback<ImageServiceInformation> callback);

}
