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
import org.socialgraph.model.Person;
import org.socialgraph.util.ObjectConverter;

/**
 * Data management for users
 * 
 * @author driedtoast
 *
 */
public class PersonDao extends AbstractDao<Person> {

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
		this.databaseMgr.endTransaction(tx);
	}
	
	
	
	/**
	 * Get by name
	 * @param name
	 * @return person
	 */
	public Person getByDisplayName(String name) {
		Person person = null;
		
		GraphDatabaseService service = this.databaseMgr.getDatabaseService();
		Transaction tx = this.databaseMgr.startTransaction();
		Node node = null;
		ResourceIterable<Node> iterator = service.findNodesByLabelAndProperty(DynamicLabel.label(Person.class.getSimpleName()), Person.DISPLAY_NAME, name);
		Iterator<Node> iter = iterator.iterator();
		if(iter.hasNext()) {
			node = iter.next();
		}
		if(node != null) {
			person = this.convertToObject(node);
		}
		this.databaseMgr.endTransaction(tx);
		return person;
	}
	
	

	/**
	 * Get by id
	 * @param id
	 * @return person 
	 */
	public Person getById(Long id) {
		Transaction tx = this.databaseMgr.startTransaction();
		Node node = this.getNode(id);
		Person person = this.convertToObject(node);
		this.databaseMgr.endTransaction(tx);
		return person;
	}
	
	
	
	/**
	 * Get all users
	 * 
	 * @return
	 */
	public List<Person> getAllUsers() {
		// TODO paging and such		
		List<Person> list = this.listNodes(Person.class);
		return list;
	}


	/**
	 * Converts a node to a person
	 * 
	 * @param node
	 * @return
	 */
	public Person convertToObject(Node node) {
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
	
	
}
