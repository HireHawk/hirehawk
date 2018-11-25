package com.hirehawk.feedback_service.dao;

import com.hirehawk.feedback_service.entity.Feedback;

import java.util.Date;
import java.util.List;

public interface FeedbackDAO {

    public void saveFeedback(int mark, String comment, Date datetime, Integer userWhoLeft, Integer userAbout, USERROLE userAboutRole);

    public void saveFeedback(int mark, Date datetime, Integer userWhoLeft, Integer userAbout, USERROLE userAboutRole);

    public List<Feedback> getFeedbacks(int user, USERROLE role);

    public List<Feedback> getFeedbacks(int user, USERROLE role, int num);

    public List<Feedback> getFeedbacksWithoutComments(int user, USERROLE role);

    public List<Feedback> getFeedbacksWithoutComments(int user, USERROLE role, int num);

    public List<Feedback> getFeedbacksWithComments(int user, USERROLE role);

    public List<Feedback> getFeedbacksWithComments(int user, USERROLE role, int num);
}