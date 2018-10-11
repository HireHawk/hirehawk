package com.hirehawk.feedback_service.dao;

import com.hirehawk.feedback_service.entity.Feedback;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
public class FeedbackDAOImpl implements FeedbackDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveFeedback(int mark, String comment, Date datetime, Integer userWhoLeft, Integer userAbout, USERROLE userAboutRole) {
        Feedback feedback = new Feedback();
        feedback.setComment(comment);
        feedback.setMark(mark);
        feedback.setUserAbout(userAbout);
        feedback.setUserLeft(userWhoLeft);
        feedback.setUserAboutRole(userAboutRole.toString());
        feedback.setDate(datetime);

        Session session = this.sessionFactory.getCurrentSession();
        session.persist(feedback);
        session.flush();
    }

    @Override
    public void saveFeedback(int mark, Date datetime, Integer userWhoLeft, Integer userAbout, USERROLE userAboutRole) {
        Feedback feedback = new Feedback();
        feedback.setMark(mark);
        feedback.setUserAbout(userAbout);
        feedback.setUserLeft(userWhoLeft);
        feedback.setUserAboutRole(userAboutRole.toString());
        feedback.setDate(datetime);

        Session session = this.sessionFactory.getCurrentSession();
        session.persist(feedback);
        session.flush();
    }

    @Override
    public List<Feedback> getFeedbacks(int user, USERROLE role) {
        Session session = this.sessionFactory.getCurrentSession();
        String sql = "SELECT f FROM " + Feedback.class.getName() + " f WHERE f.userAboutRole =:role";
        Query<Feedback> query = session.createQuery(sql, Feedback.class);
        query.setParameter("role", role.toString());
        List<Feedback> res = query.getResultList();
        res.sort(new DateComparator());
        return res;
    }

    @Override
    public List<Feedback> getFeedbacks(int user, USERROLE role, int num) {
        Session session = this.sessionFactory.getCurrentSession();
        String sql = "SELECT f FROM " + Feedback.class.getName() + " f WHERE f.userAboutRole =:role";
        Query<Feedback> query = session.createQuery(sql, Feedback.class);
        query.setParameter("role", role.toString());
        List<Feedback> res = query.getResultList();
        res.sort(new DateComparator());
        if (num < res.size()) {
            res = res.subList(res.size() - num, res.size() - 1);
        }
        return res;
    }

    @Override
    public List<Feedback> getFeedbacksWithoutComments(int user, USERROLE role) {
        List<Feedback> resAll = getFeedbacks(user,role);
        List<Feedback> res = new ArrayList<Feedback>();
        for (int i = 0; i < resAll.size(); i++) {
            if (resAll.get(i).getComment() == null) {
                res.add(resAll.get(i));
            }
        }
        res.sort(new DateComparator());
        return res;
    }

    @Override
    public List<Feedback> getFeedbacksWithoutComments(int user, USERROLE role, int num) {
        List<Feedback> resAll = getFeedbacks(user,role);
        List<Feedback> res = new ArrayList<Feedback>();
        for (int i = 0; i < resAll.size(); i++) {
            if (resAll.get(i).getComment() == null) {
                res.add(resAll.get(i));
            }
        }
        res.sort(new DateComparator());
        if (num < res.size()) {
            res = res.subList(res.size() - num, res.size() - 1);
        }
        return res;
    }

    @Override
    public List<Feedback> getFeedbacksWithComments(int user, USERROLE role) {
        List<Feedback> resAll = getFeedbacks(user,role);
        List<Feedback> res = new ArrayList<Feedback>();
        for (int i = 0; i < resAll.size(); i++) {
            if (resAll.get(i).getComment() != null) {
                res.add(resAll.get(i));
            }
        }
        res.sort(new DateComparator());
        return res;
    }

    @Override
    public List<Feedback> getFeedbacksWithComments(int user, USERROLE role, int num) {
        List<Feedback> resAll = getFeedbacks(user,role);
        List<Feedback> res = new ArrayList<Feedback>();
        for (int i = 0; i < resAll.size(); i++) {
            if (resAll.get(i).getComment() != null) {
                res.add(resAll.get(i));
            }
        }
        res.sort(new DateComparator());
        if (num < res.size()) {
            res = res.subList(res.size() - num, res.size() - 1);
        }
        return res;
    }

    private class DateComparator implements Comparator<Feedback> {

        @Override
        public int compare(Feedback f1, Feedback f2) {
            return  f1.getDate().compareTo(f2.getDate());
        }
    }
}
