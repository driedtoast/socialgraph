package org.socialgraph.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.EmbeddedGraphDatabase;

/**
 * 
 * Used to wrap the graph database stuff
 * 
 * @author driedtoast
 *
 */
public class DatabaseManager {

	
	private GraphDatabaseService graphDb = null;
	
	private String dataDir = "/tmp/graphdb";
	
	/**
	 * Initializes the database
	 * 
	 */
	public void init() {
		graphDb = new EmbeddedGraphDatabase(dataDir);
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
			transaction.finish();
		}
	}
}
