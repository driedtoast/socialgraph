package org.socialgraph.model;

import org.neo4j.graphdb.Node;

public abstract class AbstractModel {

	private String id;
	private Node baseNode;
	
	public Node getBaseNode() {
		return baseNode;
	}

	public void setBaseNode(Node baseNode) {
		this.baseNode = baseNode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
