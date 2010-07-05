package org.socialgraph.dao;

import org.apache.log4j.Logger;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.index.IndexHits;
import org.neo4j.index.IndexService;
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
		this.databaseMgr.endTransaction(tx);
	}
	
	
	
	
	/**
	 * Get by name
	 * @param name
	 * @return
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
			try {
				person = new Person();
				ObjectConverter.populate(person, node);
				this.setupNode(person, node);
			} catch (Exception e) {
				logger.warn("<dao> issue with converting object for display name " + name , e);
			}
			
		}
		return person;
	}
	
	
}
