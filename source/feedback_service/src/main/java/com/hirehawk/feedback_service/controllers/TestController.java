package com.hirehawk.feedback.controllers;

import com.hirehawk.feedback.dao.FeedbackDAO;
import com.hirehawk.feedback.dao.USERROLE;
import com.hirehawk.feedback.entities.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Transactional
public class TestController {


    @Autowired
    private FeedbackDAO feedbackDAO;


    @RequestMapping(value = "/save_with_comment", method = RequestMethod.POST)
    public void create( int mark, String comment, Date datetime, Integer userWhoLeft,  Integer userAbout, USERROLE userAboutRole)
    {
        feedbackDAO.saveFeedback(mark, comment, datetime, userWhoLeft, userAbout, userAboutRole);
    }

    @RequestMapping(value = "/save_without_comment", method = RequestMethod.POST)
    public void create_with_comment( int mark, Date datetime, Integer userWhoLeft,  Integer userAbout, USERROLE userAboutRole)
    {
        feedbackDAO.saveFeedback(mark, datetime, userWhoLeft, userAbout, userAboutRole);
    }

    @RequestMapping(value="/get_feedbacks", method=RequestMethod.GET)
    public List<Feedback> get_feedbacks(int user, USERROLE role)
    {
        return feedbackDAO.getFeedbacks(user,role);
    }




    @RequestMapping(value="/get_feedbacks_n", method=RequestMethod.GET)
    public List<Feedback> get_feedbacks_n(int user,USERROLE role,int num)
    {
        return feedbackDAO.getFeedbacks(user,role,num);
    }



    @RequestMapping(value="/get_feedbacks_without_comments", method=RequestMethod.GET)
    public List<Feedback> get_feedbacks_without_comments( int user,  USERROLE role)
    {
        return feedbackDAO.getFeedbacksWithoutComments(user, role);
    }


    @RequestMapping(value="/get_feedbacks_without_comments_n", method=RequestMethod.GET)
    public List<Feedback> get_feedbacks_without_comments_n( int user,  USERROLE role,  int num)
    {
        return feedbackDAO.getFeedbacksWithoutComments(user,role,num);
    }

    @RequestMapping(value="/get_feedbacks_with_comments",method=RequestMethod.GET)
    public List<Feedback> get_feedbacks_with_comments(int user,USERROLE role)
    {
        return feedbackDAO.getFeedbacksWithComments(user,role);
    }

    @RequestMapping(value="/get_feedbacks_with_comments_n",method=RequestMethod.GET)
    public List<Feedback> get_feedbacks_with_comments_n(int user,USERROLE role, int num)
    {
        return feedbackDAO.getFeedbacksWithComments(user,role,num);
    }




}