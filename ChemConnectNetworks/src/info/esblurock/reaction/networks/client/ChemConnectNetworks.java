package info.esblurock.reaction.networks.client;

import info.esblurock.reaction.client.async.LoginService;
import info.esblurock.reaction.client.async.LoginServiceAsync;
import info.esblurock.reaction.networks.client.network.ForceGraph;
import info.esblurock.reaction.networks.client.network.JavaScriptCallEvent;
import info.esblurock.reaction.client.async.LoginServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ChemConnectNetworks implements EntryPoint {


	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		JavaScriptCallEvent.exportStaticMethod();
		//Element parent = RootPanel.getBodyElement();
		ForceGraph graph = new ForceGraph("Mine");
		RootPanel.get().add(graph);
		
		LoginServiceAsync async = LoginService.Util.getInstance();
		SimpleLoginCallback callback = new SimpleLoginCallback();
		async.loginServer("Administration", "laguna", callback);

		
	}
}
