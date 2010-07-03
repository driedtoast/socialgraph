package org.socialgraph.neo4j;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.socialgraph.model.types.ConnectionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/spring-context/socialgraph-context.xml"})
public class DatabaseManagerTest {

	@Autowired
	private DatabaseManager databaseMgr;
	
	@Test
	public void testSetup() throws Exception {
		GraphDatabaseService gds = databaseMgr.getDatabaseService();
		Assert.assertNotNull(gds);
		Transaction tx = databaseMgr.startTransaction();
		Node firstNode = gds.createNode();
		
		Node secondNode = gds.createNode();
		Relationship relationship = firstNode.createRelationshipTo( secondNode, ConnectionType.KNOWS );
		 
		firstNode.setProperty( "message", "Hello, " );
		secondNode.setProperty( "message", "world!" );
		relationship.setProperty( "message", "brave Neo4j " );
		
		System.out.print( firstNode.getProperty( "message" ) );
		System.out.print( relationship.getProperty( "message" ) );
		System.out.print( secondNode.getProperty( "message" ) );

	}
	
}
