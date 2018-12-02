package com.hirehawk.keycloakService.controllers;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
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


import com.hirehawk.keycloakService.dao.UserData;
import com.hirehawk.keycloakService.services.UserService;


@RestController
@RequestMapping("/public")
public class PublicUserController {
	@Autowired
	private UserService keycloakService;
/*
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showLoginPage(ModelMap model) {
        model.put("name", "in28Minutes");
        return "welcome";
    }
	@GetMapping("/user/{userId}/getUserInfo")
	public String getUserInfo(@PathVariable String userId) {
		User u = new User();
		u = keycloakService.getUser(userId);
		if(u==null) return "sorry, no user";
		return u.getOpenInfo();
	}	*/
	@GetMapping(path = "/")
	public String index() {
	    return "here is where all the public endpoints are \n for example GET /public/get/{userID}";
	}
	@GetMapping(path = "")
	public String index2Iguess() {
	    return "here is where all the public endpoints are \n for example /list";
	}
	
    //register functionality is not working yet
    @GetMapping(value = "/get/{userId}")
    public UserData GetUserInfo(@PathVariable String userId) {
    	System.out.println(userId);
        return keycloakService.get(userId);
    }
    @GetMapping(value = "/list")
    public List<UserData> ListUserInfo() {
        return keycloakService.findAll();
    }
    @GetMapping(value = "/whoami")
    public String createAdvert(Principal principal ) {
    	if(principal==null)return "you are not logined";
    	KeycloakAuthenticationToken  token = (KeycloakAuthenticationToken) principal;
    	KeycloakPrincipal pr = (KeycloakPrincipal) token.getPrincipal();
    	KeycloakSecurityContext session = pr.getKeycloakSecurityContext();
    	AccessToken at = session.getToken();
    	String name = at.getGivenName();
    	String lastname = at.getFamilyName();
    	String id = at.getOtherClaims().get("user_id").toString();
        return "I am " +name+' '+lastname +" with ID "+ id ; 
    }
}
