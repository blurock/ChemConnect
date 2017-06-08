package info.esblurock.reaction.client.async;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserImageServiceAsync {

	void getBlobstoreUploadUrl(AsyncCallback<String> callback);

}
