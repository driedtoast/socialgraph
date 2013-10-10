package org.socialgraph.neo4j;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.schema.IndexDefinition;
import org.neo4j.graphdb.schema.Schema;
import org.socialgraph.model.AbstractModel;
import org.socialgraph.model.Person;

/**
 * 
 * Used to wrap the graph database stuff
 * 
 * @author driedtoast
 *
 */
public class DatabaseManager {

	
	private GraphDatabaseService graphDb = null;
	
	private String dataDir = "/tmp/socialgraphdb";
	
	/**
	 * Initializes the database
	 * 
	 */
	public void init() {
		graphDb =  new GraphDatabaseFactory().newEmbeddedDatabase(dataDir);
		Transaction tx = startTransaction();
		createIndex(Person.class.getSimpleName(), Person.DISPLAY_NAME );
		createIndex(AbstractModel.class.getSimpleName(), AbstractModel.MODEL );
        endTransaction(tx);
	}

	
	/**
	 * Creates an index if one doesn't exist for the index name and attribute
	 * 
	 * @param index
	 * @param attribute
	 * @return
	 */
	private IndexDefinition createIndex(String index, String attribute ) {		
		Schema schema = graphDb.schema();
		Iterator<IndexDefinition> iterator = schema.getIndexes(DynamicLabel.label(index )).iterator();
		if ( !iterator.hasNext() )
		{
		  return schema.indexFor( DynamicLabel.label(index ) )
                    .on(attribute)
                    .create();
		} else {
		  while(iterator.hasNext()) {
			  IndexDefinition def = iterator.next();
			  if (def.getPropertyKeys().iterator().next().equals(attribute)) {
				  return def;
			  }				  
		  }
		  return schema.indexFor( DynamicLabel.label(index ) )
                  .on(attribute)
                  .create();
		}
	}
	
	
	/**
	 * Shutting down
	 */
	public void shutdown() {
		graphDb.shutdown();
	}
	
	public GraphDatabaseService getDatabaseService() {
		return this.graphDb;
	}

	public String getDataDir() {
		return dataDir;
	}

	public void setDataDir(String dataDir) {
		this.dataDir = dataDir;
	}
	
	
	/**
	 * Starts a transaction
	 * @return
	 */
	public Transaction startTransaction() {
		return graphDb.beginTx();
	}
	
	/**
	 * Ends the transaction
	 * 
	 * @param transaction
	 */
	public void endTransaction(Transaction transaction) {
		try {
			transaction.success();
		} finally {
			transaction.close();
		}
	}
}
