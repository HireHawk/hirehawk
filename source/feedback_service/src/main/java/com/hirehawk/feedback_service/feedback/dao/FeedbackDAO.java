package com.hirehawk.feedback_service.feedback.dao;

import com.hirehawk.feedback_service.feedback.entities.Feedback;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

public interface FeedbackDAO {

    public void saveFeedback(Integer mark, String comment, Date datetime, String userWhoLeft, String userAbout, USERROLE userAboutRole,String advert);

    public void saveFeedback(Integer mark, Date datetime, String userWhoLeft, String userAbout, USERROLE userAboutRole,String advert);

    public List<Feedback> getFeedbacks(String user, USERROLE role);

    public List<Feedback> getFeedbacks(String user, USERROLE role, Integer num);

    public List<Feedback> getFeedbacksWithoutComments(String user, USERROLE role);

    public List<Feedback> getFeedbacksWithoutComments(String user, USERROLE role, Integer num);

    public List<Feedback> getFeedbacksWithComments(String user, USERROLE role);

    public List<Feedback> getFeedbacksWithComments(String user, USERROLE role, Integer num);

    public void delFeedback(Integer id);

    public List<Feedback> getFeedbacksByAdvert(String advert_id,Integer num);

    public List<Feedback> getFeedbacksByAdvert(String advert_id);

    public List<Feedback> getFeedbacksByAdvertWithComments(String advert_id, Integer num);

    public List<Feedback> getFeedbacksByAdvertWithComments(String advert_id);

    public List<Feedback> getFeedbacksByAdvertWithoutComments(String advert_id, Integer num);

    public List<Feedback> getFeedbacksByAdvertWithoutComments(String advert_id);
}