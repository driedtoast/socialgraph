package org.socialgraph.model;

import org.neo4j.graphdb.Node;

public abstract class AbstractModel {
	
	public static final String MODEL="model";


	public static final String NAME = "name";
	

	private Long id; // Object-Id Required. Unique identifier for the Model object
	private String name;  // Name The broken-out components and fully formatted
						// For person: version of the person's real name.
						// used for dup checking
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
