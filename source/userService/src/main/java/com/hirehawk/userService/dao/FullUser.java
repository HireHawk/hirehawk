package com.hirehawk.userService.dao;


import java.io.Serializable;

//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotEmpty;

public class FullUser implements Serializable {

 
	private static final long serialVersionUID = 1L;
	private String login;
	private String password;
    private String id;
    private String name;
    private String secondName;
    private String lastName;
    private String email;
    private String photo;

    private String phoneNumber;

    public FullUser() {
    	
    }


    public FullUser(String login, String password, String name, String secondName, String lastName, String email, String photo, String phoneNumber) {
    	//these will be stored in keycloak
    	this.login = login;
    	this.password = password;
    	this.email = email;
        this.name = name;
        this.secondName = secondName;
        this.lastName = lastName;
      
        this.photo = photo;
        this.phoneNumber = phoneNumber;
    }


    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String pass) {
        this.password=pass;
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
