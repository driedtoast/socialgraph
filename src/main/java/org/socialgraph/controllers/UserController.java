package org.socialgraph.controllers;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.socialgraph.dao.PersonDao;
import org.socialgraph.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller(value = "userController")
public class UserController {

	private static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private PersonDao personDao;

	/**
	 * Main edit method, called by add user as well This is to display user
	 * information
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/user/edit/{id}")
	public String editUser(@PathVariable("id") String id, Model model) {
		Person person = null;
		if (StringUtils.isEmpty(id)) {
			person = new Person();
		} else {
			Long idValue = Long.parseLong(id);
			person = personDao.getById(idValue);
		}
		model.addAttribute(person);
		return "user/edit";
	}

	/**
	 * Used for new user
	 * 
	 * 
	 * @param body
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/user/edit")
	public String addUser(Model model) {
		return this.editUser(null, model);
	}

	/**
	 * Edits submit back to this save method
	 * 
	 * @param id
	 * @param body
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/user/save", method = RequestMethod.POST)
	public String saveUser(Model model, Person person) {
		//model.addAttribute("person", person);
		this.personDao.savePerson(person);
		//model.addAttribute("message", "User is saved");
		return "redirect:/ui/user/index";
	}

	/**
	 * Get a list of users
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/user/index")
	public String listUsers(Model model) {
		List<Person> people = personDao.getAllUsers(); // need paging
		model.addAttribute("list",people);
		return "user/index";
	}
	
	
}
