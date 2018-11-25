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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
    private String id;

   // @NotEmpty
	@Column(name = "name", nullable = false)
    private String name;

   // @NotEmpty
	@Column(name = "second_name")
    private String secondName;

  //  @NotEmpty
	@Column(name = "last_name", nullable = false)
    private String lastName;

   // @Email
	@Column(name = "email", unique = true, nullable = false)
    private String email;

	@Column(name = "photo", nullable = false)
    private String photo;

	@Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    public User() {

    }

    public User(String id, String name, String secondName, String lastName, String email, String photo, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.lastName = lastName;
        this.email = email;
        this.photo = photo;
        this.phoneNumber = phoneNumber;
    }
    public User(String name, String secondName, String lastName, String email, String photo, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.lastName = lastName;
        this.email = email;
        this.photo = photo;
        this.phoneNumber = phoneNumber;
    }
    public User(FullUser u) {
        this.id = u.getId();
        this.name = u.getName();
        this.secondName = u.getSecondName();
        this.lastName = u.getLastName();
        this.email = u.getEmail();
        this.photo = u.getPhoto();
        this.phoneNumber = u.getPhoneNumber();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    public String getOpenInfo() {
    	String res="{id:"+this.id+", name:"+this.name+"}";
    	
    	return res;
    }
}

