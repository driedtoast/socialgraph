package org.socialgraph.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.NotFoundException;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.index.IndexHits;
import org.neo4j.index.IndexService;
import org.socialgraph.model.AbstractModel;
import org.socialgraph.neo4j.DatabaseManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Base dao for some general auto wiring and such
 * 
 * @author driedtoast
 *
 */
public abstract class AbstractDao<T> {

	private static final Logger logger = Logger.getLogger(AbstractDao.class);
	
	
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
			node = getNode(model.getId());
		}
		return node;
	}
	
	
	/**
	 * Deletes a node
	 * 
	 * @param id
	 * @return
	 */
	public void deleteNode(Long id) {
		Transaction tx = this.databaseMgr.startTransaction();
		GraphDatabaseService gds = databaseMgr.getDatabaseService();
		Node node =  gds.getNodeById(id);
		if(node != null) {
			// remove relationships
			Iterable<Relationship> relationships = node.getRelationships();
			if(relationships != null) {
				Iterator<Relationship> iter = relationships.iterator();
				while(iter.hasNext()) {
					Relationship relationship = iter.next();
					relationship.delete();
				}
			}
			node.delete();
		}
		this.databaseMgr.endTransaction(tx);
		
	}
	

	/**
	 * Gets or creates a node if needed
	 * 
	 * @param model
	 * @return
	 */
	public Node getNode(Long id) {
		GraphDatabaseService gds = databaseMgr.getDatabaseService();
		Node node = null;
		try {
			node =gds.getNodeById(id);
		} catch(NotFoundException nfe) {
			logger.info(id + " is not found in db ", nfe);
		}
		return node;
	}
	
	
	public abstract T convertToObject(Node node);
	
	/**
	 * Gets list of nodes based on class
	 * 
	 * @param clazz
	 * @return
	 */
	public List<T> listNodes(Class clazz) {
		List<T> list = null;
		// TODO paging and such
		
		IndexService indexService = this.databaseMgr.getIndexService();
		IndexHits<Node> nodes = indexService.getNodes(AbstractModel.MODEL, clazz.getSimpleName());
		if(nodes != null ) {
			list = new ArrayList<T>();
			while(nodes.hasNext()) {
				Node node = nodes.next();
				T org = this.convertToObject(node);
				if(org != null) {
					list.add(org);
				}
			}
		}
		return list;
	}
	
}
