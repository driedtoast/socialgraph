package org.socialgraph.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.socialgraph.model.Organization;
import org.socialgraph.model.Person;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Tests the organization dao
 * 
 * 
 * @author driedtoast
 *
 */
public class OrganizationDaoTest extends AbstractDaoTest {

	@Autowired
	private OrganizationDao organizationDao;
	
	/**
	 * Tests a create
	 * 
	 * @throws Exception
	 */
	@Test
	public void testOrganizationCreate() throws Exception {
		Organization obj = new Organization();
		obj.setName("New group1");
		organizationDao.saveOrganization(obj);
		Assert.assertNotNull(obj.getId());
		
	}

	
	/**
	 * Get a display name
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetAll() throws Exception {
		Organization obj = new Organization();
		obj.setName("New group2");
		organizationDao.saveOrganization(obj);
		Assert.assertNotNull(obj.getId());
		
		List<Organization> orgs = organizationDao.getAllOrgs();
		Assert.assertNotNull(orgs);
		Assert.assertTrue(orgs.size() > 0);
	}
	
	
}
