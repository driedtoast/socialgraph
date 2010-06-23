package org.socialgraph.model.types;

import org.neo4j.graphdb.RelationshipType;

/**
 * Initial connection type
 * 
 * @author driedtoast
 *
 */
public enum ConnectionType implements RelationshipType {
	PENDING,
	CONNECTED_TO,
	KNOWS,
	
}
