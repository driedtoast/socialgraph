package org.socialgraph.api.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller( value = "apiUserController" )
public class UserController {


    private static final Logger logger = Logger.getLogger(UserController.class);
  

   @RequestMapping(value = "/api/user/add/{context}", method = RequestMethod.POST)
   @ResponseBody
   public String addUser(@PathVariable("context") String context, @RequestBody() String body, Model model) {
	// TODO add user based on JSON formats
        // use formats from opensocial
        // jackson for serializing and such
	return "{\"status\":\"none\"}";
   }

}
