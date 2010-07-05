package org.socialgraph.dao;

import java.io.File;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.socialgraph.neo4j.DatabaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/spring-context/socialgraph-context.xml"})
public abstract class AbstractDaoTest {

	@Autowired
	 DatabaseManager databaseMgr;
	
	@Before
	public void removeDb() {
		File dataDir = new File(databaseMgr.getDataDir());
		if(dataDir.exists()) {
			dataDir.delete();
		}
	}
	
}
