 package com.hirehawk.userService.dao;

import java.io.Serializable;

import javax.persistence.*;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "user")
public class User implements Serializable {

 
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
    private String id;
   

	@Column(name = "photo")
    private String photo;

	@Column(name = "phone_number")
    private String phoneNumber;
	
	@Column(name = "status")
    private String status;
	
	@Column(name = "average_mark")
    private Float averageMark;

    public User() {

    }

    public User(String id,String photo, String phoneNumber,String status, Float averageMark) {
        this.id = id;

        this.photo = photo;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.averageMark = averageMark;
    }

    public User(FullUser u) {
        this.id = u.getId();
        this.photo = u.getPhoto();
        this.phoneNumber = u.getPhoneNumber();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
	


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Float getAverageMark() {
        return this.averageMark;
    }

    public void setAverageMark(Float mark) {
        this.averageMark = mark;
    }

}

