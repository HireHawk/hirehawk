package com.hirehawk.feedback_service.feedback.entities;



import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "comment", schema = "feedbacks")
public class Feedback implements Serializable{

    private static final long serialVersionUID = -2054386655979281969L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "mark", nullable = false)
    private Integer mark;

    @Column(name = "comment", length = 300)
    private String comment;

    @Column(name = "user_left", nullable = false)
    private String userLeft;

    @Column(name = "user_about", nullable = false)
    private String userAbout;

    @Column(name = "user_about_role", length = 20, nullable = false)
    private String userAboutRole;

    @Column(name = "date", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column (name = "advert")
    private String advert;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String  getUserLeft() {
        return userLeft;
    }

    public void setUserLeft(String  userLeft) {
        this.userLeft = userLeft;
    }

    public String getUserAbout() {
        return userAbout;
    }

    public void setUserAbout(String userAbout) {
        this.userAbout = userAbout;
    }

    public String getUserAboutRole() {
        return userAboutRole;
    }

    public void setUserAboutRole(String userAboutRole) {
        this.userAboutRole = userAboutRole;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String  getAdvert() {
        return advert;
    }

    public void setAdvert(String  advert) {
        this.advert = advert;
    }
}