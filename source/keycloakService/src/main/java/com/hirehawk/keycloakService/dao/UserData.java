 package com.hirehawk.keycloakService.dao;

import java.io.Serializable;

import javax.persistence.*;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "USER_ENTITY")
public class UserData implements Serializable {

 
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false)
    private String id;
   
	@Column(name = "EMAIL")
    private String email;

	@Column(name = "USERNAME")
    private String username;

	@Column(name = "FIRST_NAME")
    private String firstName;
	
	@Column(name = "LAST_NAME")
    private String lastName;
	
	@Column(name = "REALM_ID")
	private String realmId;
	
    public UserData() {

    }
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRealmId() {
		return realmId;
	}

	public void setRealmId(String realmId) {
		this.realmId = realmId;
	}

    public UserData(String id,String email, String username,String firstName, String lastName,String realmId) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.firstName = firstName;
        this.lastName =lastName;
        this.realmId = realmId;
    }
}

