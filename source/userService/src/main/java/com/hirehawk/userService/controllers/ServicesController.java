package com.hirehawk.userService.controllers;


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

import com.hirehawk.userService.dao.AverageMark;
import com.hirehawk.userService.dao.FullUser;
import com.hirehawk.userService.dao.MarkOperation;
import com.hirehawk.userService.dao.User;
import com.hirehawk.userService.services.UserService;
import com.hirehawk.userService.services.KeycloakService;




@RestController
public class ServicesController {
	@Autowired
	private UserService userService;

    @PostMapping(value = "/feedback/update_mark")
    public void updateMark(Principal principal, @RequestBody MarkOperation mo ) {
    	/*if(principal==null)return "you are not logined";*/
    	User user = userService.get(mo.getUserId());
    	AverageMark am = user.getAverageMark();
    	if(am.getEvalSum()==null)am.setEvalSum(0);
		if(am.getEvalNumber()==null)am.setEvalNumber(0);
    	if(mo.getOperation().equals("add")) {
    		am.setEvalNumber(am.getEvalNumber()+1);
    		if(mo.getNewMark()==null) throw new IllegalArgumentException("no new mark specified!");
    		am.setEvalSum(am.getEvalSum()+mo.getNewMark());
    	}else if (mo.getOperation().equals("update")) {
    		if(am.getEvalNumber()==0) throw new IllegalArgumentException("there are no feedbacks!");
    		if(mo.getNewMark()==null) throw new IllegalArgumentException("no new mark specified!");
    		if(mo.getPrevMark()==null) throw new IllegalArgumentException("no prev mark specified!");


    		am.setEvalSum(am.getEvalSum()+(mo.getNewMark()-mo.getPrevMark()));
    	} else if (mo.getOperation().equals("delete")) {
    		if(am.getEvalNumber()==0) throw new IllegalArgumentException("there are no feedbacks!");
    		if(mo.getPrevMark()==null) throw new IllegalArgumentException("no prev mark specified!");

    		am.setEvalNumber(am.getEvalNumber()-1);
    		am.setEvalSum(am.getEvalSum()-mo.getPrevMark());
    	} else throw new IllegalArgumentException("operation is incorrect!");
    	
    	user.setAverageMark(am);
        userService.update(user);
        return; 
    }
}

