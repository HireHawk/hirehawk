package com.hirehawk.search_service.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;


@SolrDocument(solrCoreName = "users")
public class User {

    @Id
    @Indexed(name = "id", type = "string")
    private String userId;

    @Indexed(name = "name", type = "string")
    private String name;

    @Indexed(name = "surname", type = "string")
    private String surname;

    @Indexed(name = "username", type = "string")
    private String username;

    @Indexed(name = "email", type = "string")
    private String email;

    @Indexed(name = "phone", type = "string")
    private String phone;

    public User(){}

    public User(String userId, String name, String surname, String username, String email, String phone) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.phone = phone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
