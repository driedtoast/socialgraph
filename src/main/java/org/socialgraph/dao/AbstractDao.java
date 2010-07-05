package org.socialgraph.dao;

import org.apache.commons.lang.StringUtils;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.socialgraph.model.AbstractModel;
import org.socialgraph.neo4j.DatabaseManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Base dao for some general auto wiring and such
 * 
 * @author driedtoast
 *
 */
public class AbstractDao {

	@Autowired
	protected DatabaseManager databaseMgr;
	
	/**
	 * Setup the node
	 * 
	 * @param model
	 * @param node
	 */
	public void setupNode(AbstractModel model, Node node) {
		model.setId(node.getId());
		model.setBaseNode(node);
	}
	
	
	/**
	 * Creates a node to start with
	 * 
	 * @return
	 */
	public Node createNode() {
		GraphDatabaseService gds = databaseMgr.getDatabaseService();
		return gds.createNode();
	}

	/**
	 * Gets or creates a node if needed
	 * 
	 * @param model
	 * @return
	 */
	public Node getNode(AbstractModel model) {
		Node node = null;
		if(model.getId() == null || model.getId() < 0) {
			node = createNode();
		} else {
			GraphDatabaseService gds = databaseMgr.getDatabaseService();
			node = gds.getNodeById(model.getId());
		}
		return node;
	}
	
}
