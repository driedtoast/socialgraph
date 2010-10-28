package org.socialgraph.model;

import org.neo4j.graphdb.Node;

public abstract class AbstractModel {
	
	public static final String MODEL="model";
	

	private Long id; // Object-Id Required. Unique identifier for the Model object
	private Node baseNode;
	
	public Node getBaseNode() {
		return baseNode;
	}

	public void setBaseNode(Node baseNode) {
		this.baseNode = baseNode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
