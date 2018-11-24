package com.hirehawk.userService.controllers;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
import com.hirehawk.userService.dao.User;
import com.hirehawk.userService.services.UserService;
*/

import com.hirehawk.userService.dao.User;
import com.hirehawk.userService.services.UserService;

@RestController
@RequestMapping("/private")
public class PrivateUserController {
	@Autowired
	private UserService userService;
	
	@GetMapping(path = "")
	public String index() {
	    return "here is where all the PRIVATE endpoints are \n for example /list";
	}
    @PostMapping(value = "/addUser")
    public User createAdvert(Principal principal, @RequestBody User user) {
    	KeycloakAuthenticationToken  token = (KeycloakAuthenticationToken) principal;
    	KeycloakPrincipal pr = (KeycloakPrincipal) token.getPrincipal();
        userService.save(user);
        return user;
    }
    @GetMapping(value = "/whoami")
    public String createAdvert(Principal principal ) {
    	KeycloakAuthenticationToken  token = (KeycloakAuthenticationToken) principal;
    	KeycloakPrincipal pr = (KeycloakPrincipal) token.getPrincipal();
    	KeycloakSecurityContext session = pr.getKeycloakSecurityContext();
    	AccessToken at = session.getToken();
    	String name = at.getGivenName();
    	String lastname = at.getFamilyName();
    	String id = at.getId();
        
        return "I am " +name+' '+lastname +" with ID "+ id; 
    }
}
