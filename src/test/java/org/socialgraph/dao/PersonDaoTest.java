package org.socialgraph.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.socialgraph.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/spring-context/socialgraph-context.xml"})
public class PersonDaoTest {

	@Autowired
	private PersonDao personDao;
	
	/**
	 * Tests a create
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPersonCreate() throws Exception {
		Person obj = new Person();
		obj.setDisplayName("bob ");
		obj.setAboutMe("who");
		personDao.savePerson(obj);
		Assert.assertNotNull(obj.getId());
		
	}

	/**
	 * Get a display name
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPersonGetByDisplayName() throws Exception {
		Person obj = new Person();
		obj.setDisplayName("bob");
		obj.setAboutMe("who");
		personDao.savePerson(obj);
		Long id = obj.getId();
		Assert.assertNotNull(id);
		
		Person person = personDao.getByDisplayName("bob");
		Assert.assertNotNull(person);
		Assert.assertTrue(id.equals(person.getId()));
		
	}
		
}
