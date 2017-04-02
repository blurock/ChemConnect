package info.esblurock.reaction.data.store;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import info.esblurock.reaction.data.DatabaseObject;

public class UserStorageObjectTreeNode implements Serializable {

	private static final long serialVersionUID = 1L;

	String nodeName;
	String shortdescription;
	UserObjectStorage object;
	ArrayList<UserStorageObjectTreeNode> children;

	public UserStorageObjectTreeNode() {
		children = null;
		nodeName = null;
		object = null;
	}

	public UserStorageObjectTreeNode(String key) {
		this.nodeName = key;
		this.shortdescription = null;
		this.object = null;
		children = new ArrayList<UserStorageObjectTreeNode>();
	}

	public UserStorageObjectTreeNode(UserObjectStorage object) {
		this.object = object;
		this.nodeName = null;
		this.shortdescription = object.getShortdescription();
	}

	public UserStorageObjectTreeNode findChild(String name) {
		UserStorageObjectTreeNode child = null;
		Iterator<UserStorageObjectTreeNode> iter = children.iterator();
		boolean notdone = iter.hasNext();
		while (notdone) {
			UserStorageObjectTreeNode next = iter.next();
			String nodename = next.getNodeName();
			if (nodename != null) {
				if (nodename.compareTo(name) == 0) {
					child = next;
					notdone = false;
				}
			}
			notdone = iter.hasNext();
		}
		return child;
	}

	public void addChild(UserStorageObjectTreeNode objectnode) {
		children.add(objectnode);
	}

	public boolean isNameNode() {
		return nodeName != null;
	}

	public boolean isObjectNode() {
		return object != null;
	}

	public void setObject(UserObjectStorage object) {
		this.object = object;
	}

	public UserObjectStorage getObject() {
		return object;
	}

	public String getNodeName() {
		return nodeName;
	}

	public ArrayList<UserStorageObjectTreeNode> getChildren() {
		return children;
	}

	public String getShortdescription() {
		return shortdescription;
	}

	public void setShortdescription(String shortdescription) {
		this.shortdescription = shortdescription;
	}

}
