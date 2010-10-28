package org.socialgraph.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.index.IndexHits;
import org.neo4j.index.IndexService;
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
		IndexService indexService = this.databaseMgr.getIndexService();
		indexService.index(node, AbstractModel.NAME, org.getName());
		indexService.index(node,AbstractModel.MODEL, Organization.class.getSimpleName());
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
		IndexService indexService = this.databaseMgr.getIndexService();
		Node node = null;
		IndexHits<Node> nodes = indexService.getNodes(AbstractModel.NAME, name);
		// TODO work through multiple models in same index
		if(nodes != null && nodes.hasNext()) {
			node = nodes.next();
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
		Node node = this.getNode(id);
		Organization org = this.convertToObject(node);
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
