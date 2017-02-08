package info.esblurock.reaction.client.async;

import com.google.gwt.user.client.rpc.AsyncCallback;

import info.esblurock.reaction.data.chemical.respect.ReSpecTHXMLFileBase;

public interface ProcessReSpecThAsync {

	void parseReSpecThXML(String xml, AsyncCallback<ReSpecTHXMLFileBase> callback);

}
