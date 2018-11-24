package com.hirehawk.userService.controllers;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/*
import com.hirehawk.userService.dao.User;
import com.hirehawk.userService.services.UserService;
*/

import com.hirehawk.userService.dao.User;
import com.hirehawk.userService.services.UserService;


@RestController
@RequestMapping("/public")
public class PublicUserController {
	@Autowired
	private UserService userService;
/*
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showLoginPage(ModelMap model) {
        model.put("name", "in28Minutes");
        return "welcome";
    }
	@GetMapping("/user/{userId}/getUserInfo")
	public String getUserInfo(@PathVariable String userId) {
		User u = new User();
		u = userService.getUser(userId);
		if(u==null) return "sorry, no user";
		return u.getOpenInfo();
	}	*/
	@GetMapping(path = "/")
	public String index() {
	    return "here is where all the public endpoints are \n for example /list";
	}
	@GetMapping(path = "")
	public String index2Iguess() {
	    return "here is where all the public endpoints are \n for example /list";
	}
	
	@GetMapping(path = "/list")
	public String listClients(Model model){
		return "Not implemented yet, sorry";
	}
	@GetMapping(path = "/customers")
	public String customers(Model model) {
	  //  addCustomers();
	   // model.addAttribute("customers", customerDAO.findAll());
	    return "customers";
	}
    @GetMapping(value = "/saveDummy")
    public User createAdvert() {
    	User u = new User( "dummyName", "really,second name?", "dummySurname", "dummyMail@gmail.com", "photourl!", "+38012711891" );
        userService.save(u);
        return u;
    }
}
