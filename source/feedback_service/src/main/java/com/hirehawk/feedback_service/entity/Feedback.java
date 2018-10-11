package com.hirehawk.feedback_service.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Feedback implements Serializable{

    private static final long serialVersionUID = -2054386655979281969L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "mark", nullable = false)
    private int mark;

    @Column(name = "comment", length = 300)
    private String comment;

    @Column(name = "user_left", nullable = false)
    private Integer userLeft;

    @Column(name = "user_about", nullable = false)
    private Integer userAbout;

    @Column(name = "user_about_role", length = 20, nullable = false)
    private String userAboutRole;

    @Column(name = "date", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getUserLeft() {
        return userLeft;
    }

    public void setUserLeft(Integer userLeft) {
        this.userLeft = userLeft;
    }

    public Integer getUserAbout() {
        return userAbout;
    }

    public void setUserAbout(Integer userAbout) {
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
}
