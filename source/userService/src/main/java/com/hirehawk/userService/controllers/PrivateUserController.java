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
	    return "here is where all the PRIVATE endpoints are \n for example GET /get_mine and POST /update_mine {photo:string, status:string, ...} ";
	}
    //creates and updates user info
    @PostMapping(value = "/update_mine")
    public User UpdateUserInfo(Principal principal ,@RequestBody User user) {
    	KeycloakAuthenticationToken  token = (KeycloakAuthenticationToken) principal;
    	KeycloakPrincipal pr = (KeycloakPrincipal) token.getPrincipal();
    	KeycloakSecurityContext session = pr.getKeycloakSecurityContext();
    	AccessToken at = session.getToken();
    	String id = at.getOtherClaims().get("user_id").toString();
    	user.setId(id);
    	user.setAverageMark(null);
        return userService.update(user);
    }
    //
    @GetMapping(value = "/get_mine")
    public User GetUserInfo(Principal principal) {
    	System.out.println("get_mine!");
    	KeycloakAuthenticationToken  token = (KeycloakAuthenticationToken) principal;
    	KeycloakPrincipal pr = (KeycloakPrincipal) token.getPrincipal();
    	KeycloakSecurityContext session = pr.getKeycloakSecurityContext();
    	AccessToken at = session.getToken();
    	String id = at.getOtherClaims().get("user_id").toString();
    	User u = userService.get(id);
    	if(u==null)System.out.println("u is null@!!!!!!!");
    	System.out.println("what now");
        return userService.get(id);
    }
    
    @GetMapping(value = "/whoami")
    public String whoami(Principal principal ) {
    	KeycloakAuthenticationToken  token = (KeycloakAuthenticationToken) principal;
    	KeycloakPrincipal pr = (KeycloakPrincipal) token.getPrincipal();
    	KeycloakSecurityContext session = pr.getKeycloakSecurityContext();
    	AccessToken at = session.getToken();
    	String name = at.getGivenName();
    	String lastname = at.getFamilyName();
    	String id = at.getOtherClaims().get("user_id").toString();
        
        return "I am " +name+' '+lastname +" with ID "+ id; 
    }
}
