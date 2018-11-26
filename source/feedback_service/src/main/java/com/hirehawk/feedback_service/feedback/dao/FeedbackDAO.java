package com.hirehawk.feedback_service.feedback.dao;

import com.hirehawk.feedback_service.feedback.entities.Feedback;

import java.util.Date;
import java.util.List;

public interface FeedbackDAO {

    public void saveFeedback(int mark, String comment, Date datetime, String userWhoLeft, String userAbout, USERROLE userAboutRole);

    public void saveFeedback(int mark, Date datetime, String userWhoLeft, String userAbout, USERROLE userAboutRole);

    public List<Feedback> getFeedbacks(String user, USERROLE role);

    public List<Feedback> getFeedbacks(String user, USERROLE role, int num);

    public List<Feedback> getFeedbacksWithoutComments(String user, USERROLE role);

    public List<Feedback> getFeedbacksWithoutComments(String user, USERROLE role, int num);

    public List<Feedback> getFeedbacksWithComments(String user, USERROLE role);

    public List<Feedback> getFeedbacksWithComments(String user, USERROLE role, int num);
}