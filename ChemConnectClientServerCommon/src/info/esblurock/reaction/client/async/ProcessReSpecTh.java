package info.esblurock.reaction.client.async;

import java.io.IOException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import info.esblurock.reaction.data.chemical.respect.ReSpecTHXMLFileBase;

@RemoteServiceRelativePath("processrespecth")
public interface ProcessReSpecTh  extends RemoteService {

	   public static class Util
	   {
	       private static ProcessReSpecThAsync instance;
	       public static ProcessReSpecThAsync getInstance() {
	           if (instance == null) {
	               instance = GWT.create(ProcessReSpecTh.class);
	           }
	           return instance;
	       }
	   }

	
	ReSpecTHXMLFileBase parseReSpecThXML(String xml) throws IOException;
	
}
