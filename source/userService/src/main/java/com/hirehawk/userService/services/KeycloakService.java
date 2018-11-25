package com.hirehawk.userService.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.Response;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.hirehawk.userService.dao.FullUser;
import com.hirehawk.userService.dao.User;
import com.hirehawk.userService.dao.UserRepository;

@Service
public class KeycloakService {

	@Autowired
    private UserRepository repository;

	static {
		// Initialize Data

		//User tapok = new User("111001", "Only","One","Man","some@gmail.com","photouri,photouri!", "+38067275832");
		//users.add(tapok);
	}

    public List<User> findAll() {

        List<User> users = (List<User>) repository.findAll();
        return users;
    }
    public Boolean addRole(String role, String userID) {
    	String serverUrl = "http://176.37.65.30:8666/auth";
    	String realm = "hirehawk";
    	String clientId = "hirehawk_web";
    	String rootRealm = "master";
    	String rootClientId = "security-admin-console";
    	String rootUsername = "root";
    	String rootPassword = "7um2u86tnv";
    	String userRole ="user";

    	Keycloak keycloak = KeycloakBuilder.builder() //
    			.serverUrl(serverUrl)
    		.realm(rootRealm)
    		.grantType(OAuth2Constants.PASSWORD)
    		.clientId(rootClientId)
    		.username(rootUsername)
    		.password(rootPassword)
    		//.clientSecret(clientSecret)
    		.build();
   
    	return true;
    }
    public Boolean register(FullUser fullUser) {
    	String serverUrl = "http://176.37.65.30:8666/auth";
    	String realm = "hirehawk";
    	String clientId = "hirehawk_web";
    	String rootRealm = "master";
    	String rootClientId = "security-admin-console";
    	String rootUsername = "root";
    	String rootPassword = "7um2u86tnv";
    	String userRole ="user";

    	Keycloak keycloak = KeycloakBuilder.builder() //
    			.serverUrl(serverUrl)
    		.realm(rootRealm)
    		.grantType(OAuth2Constants.PASSWORD)
    		.clientId(rootClientId)
    		.username(rootUsername)
    		.password(rootPassword)
    		//.clientSecret(clientSecret)
    		.build();
   
    	UserRepresentation user = new UserRepresentation();
	    user.setEnabled(true);
	    user.setUsername(fullUser.getLogin());
	    user.setEmail(fullUser.getEmail());
	    user.setAttributes(Collections.singletonMap("origin",
    Arrays.asList("demo")));
           
        RealmResource realmResource = keycloak.realm(realm);
        UsersResource userRessource = realmResource.users();
        Response response = userRessource.create(user);
        if(response == null) {
        	System.out.println("can't create user");
        	return false;
        }

        System.out.println("Created User" + response.toString());
        System.out.println(response.getLocation());
        
       String userId =
       		response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
        
       RoleRepresentation userRealmRole = // the role of the user
        		realmResource.roles().get("user").toRepresentation();
       
        userRessource.get(userId).roles().realmLevel().add(Arrays.asList(userRealmRole));
          
        ClientRepresentation app1Client =
        		realmResource.clients().findByClientId(clientId).get(0);
        RoleRepresentation userClientRole =
        		realmResource.clients().get(app1Client.getId()).roles().get(userRole).toRepresentation();
           
        userRessource.get(userId).roles().clientLevel(app1Client.getId()).add(Arrays.asList(userClientRole));
           /*
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(fullUser.getPassword());
        userRessource.get(userId).resetPassword(passwordCred);
        */
        
    	return true;
    }
    public Boolean registerDummy() {
    	FullUser f = new FullUser("someuser", "undefined", "myname", "mySecondName", "MyLastName", "someone@gmail.com", "photouri", "someThing");
    	f.getEmail();
    	f.getLogin();
    	return register(new FullUser("some12user", "undefined", "myname", "mySecondName", "MyLastName", "some12one@gmail.com", "photouri", "someThing"));
    }
	
}