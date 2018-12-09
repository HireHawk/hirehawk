package com.hirehawk.feedback_service.feedback.controllers;

import com.hirehawk.feedback_service.feedback.dao.FeedbackDAO;
import com.hirehawk.feedback_service.feedback.dao.USERROLE;
import com.hirehawk.feedback_service.feedback.entities.Feedback;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
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


@RestController
@RequestMapping("/manageFeedbacks")
@Transactional
public class FeedbackController {


    @Autowired
    private FeedbackDAO feedbackDAO;


    @RequestMapping(value = "/create_feedback", method = RequestMethod.POST)
    public void create_with_comment(Integer mark, String comment, String userAbout, USERROLE userAboutRole,String advert,Principal userWhoLeft)
    {
        Date datetime=new Date();
        if(userWhoLeft==null) {System.out.println("gjgk"); return;} //"you are not logined";
        System.out.println("keycloack");
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) userWhoLeft;
        KeycloakPrincipal pr = (KeycloakPrincipal) token.getPrincipal();
        KeycloakSecurityContext session = pr.getKeycloakSecurityContext();
        AccessToken at = session.getToken();
        String id = at.getOtherClaims().get("user_id").toString();
        if(id != userAbout)
        { if(comment==null)
            feedbackDAO.saveFeedback(mark, datetime, id, userAbout, userAboutRole,advert);
        else
            feedbackDAO.saveFeedback(mark,comment,datetime,id,userAbout,userAboutRole,advert);
        setMark(userAbout,"add",0,mark);
        }
    }

   public String setMark(String id,String operation,Integer old,Integer new1) {
        {
            String url ="http://176.37.65.30:8200/feedback/update_mark";
            //String url = "http://176.37.65.30:8213/advertSearch/indexAdvert?";
           // url += advert_id;
            url = url.replace(" ", "%20");
            System.out.println(url);
            String result=" ";
            try {
                HttpResponse<JsonNode> response = Unirest.post(url)
                        //.basicAuth("api", "Vac")
                        //.header("cache-control", "no-cache")
                        .header("accept", "application/json")
                        .body("{\"userid\":\""+id+"\",\"operation\":\""+operation+"\",\"prevmark\":\""+Integer.toString(old)+"\",\"newmark\":\""+Integer.toString(new1)+"\"}")
                        .asJson();
                //result= response.getBody();
               // result.toString();
                System.out.println(response.toString());
            } catch (UnirestException e) {
                e.printStackTrace();
            }
            return result;
        }
    }


    @RequestMapping(value = "/del_feedback",method = RequestMethod.POST)
    public void del(Integer id){
        Feedback feedback=feedbackDAO.getById(id);
        setMark("delete",feedback.getUserAbout(),feedback.getMark(),0);
        feedbackDAO.delFeedback(id);
    }





    @RequestMapping(value="/get_feedbacks", method=RequestMethod.GET)
    public List<Feedback> get_feedbacks_n(String user,USERROLE role,Integer num)
    {
        if(num==null)
        return feedbackDAO.getFeedbacks(user,role);
        else
            return feedbackDAO.getFeedbacks(user,role,num);
    }





    @RequestMapping(value="/get_feedbacks_without_comments", method=RequestMethod.GET)
    public List<Feedback> get_feedbacks_without_comments_n( String  user,  USERROLE role,  Integer num)
    {
        if (num==null)
        return feedbackDAO.getFeedbacksWithoutComments(user,role);
        else
            return feedbackDAO.getFeedbacksWithoutComments(user,role,num);
    }


    @RequestMapping(value="/get_feedbacks_with_comments",method=RequestMethod.GET)
    public List<Feedback> get_feedbacks_with_comments_n(String  user,USERROLE role, Integer num)
    {
        if (num==null)
        return feedbackDAO.getFeedbacksWithComments(user,role);
        else
            return feedbackDAO.getFeedbacksWithComments(user,role,num);
    }


    @RequestMapping(value="/get_feedbacks_by_advert",method=RequestMethod.GET)
    public List<Feedback> get_feedbacks_by_advert(String advert_id,Integer num){
        if(num==null)
            return feedbackDAO.getFeedbacksByAdvert(advert_id);
        else
            return feedbackDAO.getFeedbacksByAdvert(advert_id,num);
    }

    @RequestMapping(value="/get_feedbacks_by_advert_with_comment",method=RequestMethod.GET)
    public List<Feedback> get_feedbacks_by_advert_with_comment(String advert_id,Integer num){
        System.out.println("you are here");
        if(num==null)
            return feedbackDAO.getFeedbacksByAdvertWithComments(advert_id);
        else
            return feedbackDAO.getFeedbacksByAdvertWithComments(advert_id,num);
    }

    @RequestMapping(value="/get_feedbacks_by_advert_without_comment",method=RequestMethod.GET)
    public List<Feedback> get_feedbacks_by_advert_without_comment(String advert_id,Integer num){
        if(num==null)
            return feedbackDAO.getFeedbacksByAdvertWithoutComments(advert_id);
        else
            return feedbackDAO.getFeedbacksByAdvertWithoutComments(advert_id,num);
    }
}