package org.socialgraph.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Initial UI Controller
 * First screens the app presents
 * 
 * @author driedtoast
 *
 */
@Controller( value = "welcomeController" )
public class WelcomeController {


    private static final Logger logger = Logger.getLogger(WelcomeController.class);
  

    /**
     * Initial page, returns welcome pointing to freemarker template.
     * 
     * @freemarkerTemplate src/main/webapp/templates/freemarker/welcome.ftl
     * 
     * @param context
     * @param body
     * @param model
     * @return
     */
   @RequestMapping(value = "/welcome/index.html")
   public String index(Model model) {
	     
	   
	   
	     return "welcome";
   }

}
