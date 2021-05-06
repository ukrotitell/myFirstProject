package com.gonchcharov.ru.EPAMFinalProject.controller;

import com.gonchcharov.ru.EPAMFinalProject.entity.User;
import com.gonchcharov.ru.EPAMFinalProject.service.RoleService;
import com.gonchcharov.ru.EPAMFinalProject.service.StudentService;
import com.gonchcharov.ru.EPAMFinalProject.service.UserService;
import com.gonchcharov.ru.EPAMFinalProject.user.CrmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
    @Autowired
    private UserService userService;




	
    private Logger logger = Logger.getLogger(getClass().getName());

    static List<String> rolesList = null;

    static {
    	rolesList = new ArrayList<>();
    	rolesList.add("ROLE_TEACHER");
    	rolesList.add("ROLE_STUDENT");
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	@GetMapping("/showRegistrationForm")
	public String showMyLoginPage(Model theModel) {
		
		theModel.addAttribute("crmUser", new CrmUser());

		return "registration-form";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
				@Valid @ModelAttribute("crmUser") CrmUser theCrmUser,
				BindingResult theBindingResult, 
				Model theModel) {

		String userName = theCrmUser.getUserName();
		logger.info("Processing registration form for: " + userName);
		
		// form validation
		 if (theBindingResult.hasErrors()){
			 return "registration-form";
	        }

		// check the database if user already exists
        User existing = userService.findByUserName(userName);
        if (existing != null){
        	theModel.addAttribute("crmUser", new CrmUser());
			theModel.addAttribute("registrationError", "User name already exists.");

			logger.warning("User name already exists.");
        	return "registration-form";
        }

        userService.save(theCrmUser);
        logger.info("Successfully created user: " + userName);
        
        return "registration-confirmation";		
	}
}
