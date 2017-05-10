package info.esblurock.reaction.data.network;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class BaseGraphWithNodesAndLinks implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String standardLinkNameDelimitor = ":";
	public static final String simpleLinkType = "Simple";
	
	String linkNameDelimitor = standardLinkNameDelimitor;
	
	ArrayList<GraphNodeBaseData> listOfNodes;
	ArrayList<GraphLinkBaseData> listOfLinks;
	HashMap<String,GraphNodeBaseData> nodeMap;
	HashMap<String,GraphLinkBaseData> linkMap;
	
	HashMap<String,ArrayList<String>> linksForNodes;
	
	String dataSet;
	String fileCode;
	public BaseGraphWithNodesAndLinks(String dataSet, String fileCode) {
		super();
		this.dataSet = dataSet;
		this.fileCode = fileCode;
		listOfNodes = new ArrayList<GraphNodeBaseData>();
		listOfLinks = new ArrayList<GraphLinkBaseData>();
		nodeMap = new HashMap<String,GraphNodeBaseData>();
		linkMap = new HashMap<String,GraphLinkBaseData>();
		linksForNodes = new HashMap<String,ArrayList<String>>();
	}
	
	public void addNode(GraphNodeBaseData node) {
		listOfNodes.add(node);
		nodeMap.put(node.getNodeName(), node);
	}
	public void addLink(GraphLinkBaseData link) {
		listOfLinks.add(link);
		linkMap.put(link.getLinkName(),link);
		addLinkedNode(link.getNodeBegin(),link.getNodeEnd());
		addLinkedNode(link.getNodeEnd(),link.getNodeBegin());
	}
	private void addLinkedNode(String node, String linked) {
		ArrayList<String> linkednodes = linksForNodes.get(node);
		if(linkednodes == null) {
			linkednodes = new ArrayList<String>();
			linksForNodes.put(node,linkednodes);
		}
		linkednodes.add(linked);
	}
	
	public void addBaseNode(String nodename) {
		GraphNodeBaseData node = new GraphNodeBaseData(nodename, dataSet, fileCode);
		addNode(node);
	}
	public void addSimpleLink(String node1, String node2) {
		String linkname = node1 + linkNameDelimitor + node2;
		addSimpleLink(linkname, node1, node2, simpleLinkType);
	}
	public void addSimpleLink(String linkname, String node1, String node2, String linktype) {
		GraphLinkBaseData link = new GraphLinkBaseData(linkname, node1, node2, linktype, dataSet, fileCode);
		addLink(link);
	}
		
}
