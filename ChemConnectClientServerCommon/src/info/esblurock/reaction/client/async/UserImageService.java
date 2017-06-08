package info.esblurock.reaction.client.async;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("images")
public interface UserImageService  extends RemoteService  {
    public String getBlobstoreUploadUrl();
}
