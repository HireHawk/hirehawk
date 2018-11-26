package com.hirehawk.feedback_service.feedback.controllers;

import com.hirehawk.feedback_service.feedback.dao.FeedbackDAO;
import com.hirehawk.feedback_service.feedback.dao.USERROLE;
import com.hirehawk.feedback_service.feedback.entities.Feedback;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/manageFeedbacks")
@Transactional
public class FeedbackController {


    @Autowired
    private FeedbackDAO feedbackDAO;


    @RequestMapping(value = "/save_with_comment", method = RequestMethod.POST)
    public void create_with_comment(int mark, String comment, Date datetime, String userAbout, USERROLE userAboutRole,Principal userWhoLeft)
    {
        if(userWhoLeft==null) {System.out.println("gjgk"); return;} //"you are not logined";
        System.out.println("keycloack");
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) userWhoLeft;
        KeycloakPrincipal pr = (KeycloakPrincipal) token.getPrincipal();
        KeycloakSecurityContext session = pr.getKeycloakSecurityContext();
        AccessToken at = session.getToken();
        String id = at.getOtherClaims().get("user_id").toString();
        //System.out.println(id);
        feedbackDAO.saveFeedback(mark, comment, datetime, id, userAbout, userAboutRole);
    }

    @RequestMapping(value = "/save_without_comment", method = RequestMethod.POST)
    public void create_without_comment( int mark, Date datetime,  String  userAbout, USERROLE userAboutRole, Principal userWhoLeft)
    {
        if(userWhoLeft==null) {System.out.println("gjgk"); return;} //"you are not logined";
        System.out.println("keycloack");
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) userWhoLeft;
        KeycloakPrincipal pr = (KeycloakPrincipal) token.getPrincipal();
        KeycloakSecurityContext session = pr.getKeycloakSecurityContext();
        AccessToken at = session.getToken();
        String id = at.getId();
        feedbackDAO.saveFeedback(mark, datetime, id, userAbout, userAboutRole);
    }

    @RequestMapping(value="/get_feedbacks", method=RequestMethod.GET)
    public List<Feedback> get_feedbacks(String user, USERROLE role)
    {
        return feedbackDAO.getFeedbacks(user,role);
    }




    @RequestMapping(value="/get_feedbacks_n", method=RequestMethod.GET)
    public List<Feedback> get_feedbacks_n(String user,USERROLE role,int num)
    {
        return feedbackDAO.getFeedbacks(user,role,num);
    }



    @RequestMapping(value="/get_feedbacks_without_comments", method=RequestMethod.GET)
    public List<Feedback> get_feedbacks_without_comments( String user,  USERROLE role)
    {
        return feedbackDAO.getFeedbacksWithoutComments(user, role);
    }


    @RequestMapping(value="/get_feedbacks_without_comments_n", method=RequestMethod.GET)
    public List<Feedback> get_feedbacks_without_comments_n( String  user,  USERROLE role,  int num)
    {
        return feedbackDAO.getFeedbacksWithoutComments(user,role,num);
    }

    @RequestMapping(value="/get_feedbacks_with_comments",method=RequestMethod.GET)
    public List<Feedback> get_feedbacks_with_comments(String user,USERROLE role)
    {
        return feedbackDAO.getFeedbacksWithComments(user,role);
    }

    @RequestMapping(value="/get_feedbacks_with_comments_n",method=RequestMethod.GET)
    public List<Feedback> get_feedbacks_with_comments_n(String  user,USERROLE role, int num)
    {
        return feedbackDAO.getFeedbacksWithComments(user,role,num);
    }
}