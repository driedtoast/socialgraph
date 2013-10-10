package org.socialgraph.dao;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.Transaction;
import org.socialgraph.model.AbstractModel;
import org.socialgraph.model.Organization;
import org.socialgraph.util.ObjectConverter;

/**
 * Organization base management 
 * 
 * @author driedtoast
 *
 */
public class OrganizationDao extends AbstractDao<Organization> {

	private static final Logger logger = Logger.getLogger(OrganizationDao.class);
	

	/**
	 * Saves a org object
	 * Will create if required
	 * 
	 * 
	 * @param org
	 */
	public void saveOrganization(Organization org) {
		Transaction tx = this.databaseMgr.startTransaction();
		Node node = this.getNode(org);
		
		ObjectConverter.convertToNode(org, node);
		this.setupNode(org, node);
		this.databaseMgr.endTransaction(tx);
	}
	
	
	/**
	 * Converts a node to a org
	 * 
	 * @param node
	 * @return
	 */
	public Organization convertToObject(Node node) {
		Organization org = null;
		if(node != null) {
			try {
				org = new Organization();
				ObjectConverter.populate(org, node);
				this.setupNode(org, node);
			} catch (Exception e) {
				org = null;
				logger.warn("<dao> issue with converting object for node " + node.getId() , e);
			}
		}
		return org;
	}
	
	
	
	/**
	 * Get by name
	 * @param name
	 * @return org
	 */
	public Organization getByName(String name) {
		Organization org = null;
		
		GraphDatabaseService service = this.databaseMgr.getDatabaseService();
		Node node = null;
		ResourceIterable<Node> iterator = service.findNodesByLabelAndProperty(DynamicLabel.label(Organization.class.getSimpleName()), AbstractModel.NAME, name);
		Iterator<Node> iter = iterator.iterator();
		if(iter.hasNext()) {
			node = iter.next();
		}
		if(node != null) {
			org = this.convertToObject(node);
		}
		return org;
	}
	
	

	/**
	 * Get by id
	 * @param id
	 * @return org 
	 */
	public Organization getById(Long id) {
		Transaction tx = databaseMgr.startTransaction();
		Node node = this.getNode(id);
		Organization org = this.convertToObject(node);
		databaseMgr.endTransaction(tx);
		return org;
	}
	
	/**
	 * Get all orgs
	 * 
	 * @return
	 */
	public List<Organization> getAllOrgs() {
		// TODO paging and such
		List<Organization> list = this.listNodes(Organization.class);
		return list;
	}
	
	
}
