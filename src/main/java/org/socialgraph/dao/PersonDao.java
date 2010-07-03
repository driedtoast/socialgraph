package org.socialgraph.dao;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.socialgraph.model.Person;
import org.socialgraph.util.ObjectConverter;

public class PersonDao extends AbstractDao {

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
		person.setId(node.getId());
		person.setBaseNode(node);
		this.databaseMgr.endTransaction(tx);
	}
	
}
