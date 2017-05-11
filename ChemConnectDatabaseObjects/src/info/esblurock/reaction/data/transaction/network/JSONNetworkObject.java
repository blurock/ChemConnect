package info.esblurock.reaction.data.transaction.network;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gwt.core.client.JavaScriptObject;

import info.esblurock.reaction.data.network.BaseGraphWithNodesAndLinks;
import info.esblurock.reaction.data.network.GraphLinkBaseData;

public class JSONNetworkObject {
	public static String sourceS = "source";
	public static String targetS = "target";
	public static String typeS = "type";
	public static String networkS = "network";
	
	JSONObject networkAsJSON = null;
	
	public JSONNetworkObject(BaseGraphWithNodesAndLinks basegraph) {
		ArrayList<GraphLinkBaseData> links = basegraph.getListOfLinks();
		JSONArray networkAsArray = new JSONArray();
		int count = 0;
		for(GraphLinkBaseData link: links) {
			JSONObject jsonlink = new JSONObject();
			jsonlink.put(sourceS,link.getNodeBegin());
			jsonlink.put(targetS,link.getNodeEnd());
			jsonlink.put(typeS, link.getLinkType());
			networkAsArray.put(count++,jsonlink);
		}
		networkAsJSON = new JSONObject();
		networkAsJSON.put(networkS, networkAsArray);
	}
	public String toString() {
		return networkAsJSON.toString();
	}
}
