package org.socialgraph.controllers;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.socialgraph.dao.OrganizationDao;
import org.socialgraph.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller(value = "groupController")
public class GroupController {

	private static final Logger logger = Logger.getLogger(GroupController.class);

	@Autowired
	private OrganizationDao organizationDao;

	/**
	 * Main edit method, called by add group as well This is to display org
	 * information
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/group/edit/{id}")
	public String editOrg(@PathVariable("id") Long id, Model model) {
		Organization org = null;
		if (id  == null || id == -1) {
			org = new Organization();
		} else {
			org = organizationDao.getById(id);
		}
		model.addAttribute("org",org);
		return "group/edit";
	}

	/**
	 * Used for new org
	 * 
	 * 
	 * @param body
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/group/edit")
	public String addOrg(Model model) {
		return this.editOrg(null, model);
	}

	/**
	 * Edits submit back to this save method
	 * 
	 * @param id
	 * @param body
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/group/save", method = RequestMethod.POST)
	public String saveOrg(Model model, Organization org) {
		//model.addAttribute("org", org);
		this.organizationDao.saveOrganization(org);
		// model.addAttribute("message", "Organization is saved");
		return "redirect:/ui/group/index";
	}

	/**
	 * Delete org
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/group/delete/{id}")
	public String deleteOrg(@PathVariable("id") Long id, Model model) {
		organizationDao.deleteNode(id);
		return "redirect:/ui/group/index";
	}
	
	
	/**
	 * Get a list of groups
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/group/index")
	public String listOrgs(Model model) {
		List<Organization> orgs = organizationDao.getAllOrgs(); // need paging
		model.addAttribute("list", orgs);
		return "group/index";
	}
	
	
}
