package org.socialgraph.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.index.IndexHits;
import org.neo4j.index.IndexService;
import org.socialgraph.model.AbstractModel;
import org.socialgraph.model.Person;
import org.socialgraph.util.ObjectConverter;

public class PersonDao extends AbstractDao {

	private static final Logger logger = Logger.getLogger(PersonDao.class);
	

	/**
	 * Saves a person object
	 * Will create if required
	 * 
	 * 
	 * @param person
	 */
	public void savePerson(Person person) {
		Transaction tx = this.databaseMgr.startTransaction();
		Node node = this.getNode(person);
		ObjectConverter.convertToNode(person, node);
		this.setupNode(person, node);
		IndexService indexService = this.databaseMgr.getIndexService();
		indexService.index(node, Person.DISPLAY_NAME, person.getDisplayName());
		indexService.index(node,AbstractModel.MODEL, Person.class.getSimpleName());
		this.databaseMgr.endTransaction(tx);
	}
	
	
	/**
	 * Converts a node to a person
	 * 
	 * @param node
	 * @return
	 */
	public Person nodeToPerson(Node node) {
		Person person = null;
		if(node != null) {
			try {
				person = new Person();
				ObjectConverter.populate(person, node);
				this.setupNode(person, node);
			} catch (Exception e) {
				logger.warn("<dao> issue with converting object for node " + node.getId() , e);
			}
		}
		return person;
	}
	
	
	
	/**
	 * Get by name
	 * @param name
	 * @return person
	 */
	public Person getByDisplayName(String name) {
		Person person = null;
		IndexService indexService = this.databaseMgr.getIndexService();
		Node node = null;
		IndexHits<Node> nodes = indexService.getNodes(Person.DISPLAY_NAME, name);
		if(nodes != null && nodes.hasNext()) {
			node = nodes.next();
		}
		if(node != null) {
			person = this.nodeToPerson(node);
		}
		return person;
	}
	
	

	/**
	 * Get by id
	 * @param id
	 * @return person 
	 */
	public Person getById(Long id) {
		Person person = null;
		Node node = this.getNode(id);
		person = this.nodeToPerson(node);
		return person;
	}
	
	/**
	 * Get all users
	 * 
	 * @return
	 */
	public List<Person> getAllUsers() {
		List<Person> list = null;
		// TODO paging and such
		
		// TODO abstract into base dao
		IndexService indexService = this.databaseMgr.getIndexService();
		IndexHits<Node> nodes = indexService.getNodes(AbstractModel.MODEL, Person.class.getSimpleName());
		if(nodes != null ) {
			list = new ArrayList<Person>();
			while(nodes.hasNext()) {
				Node node = nodes.next();
				Person person = this.nodeToPerson(node);
				if(person != null) {
					list.add(person);
				}
			}
		}
		return list;
	}
	
	
}
