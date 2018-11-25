package com.hirehawk.messaging_service.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "chat_users")
public class ChatUser implements Serializable {

    private static final long serialVersionUID = -2054386655979281969L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "chat_id", nullable = false)
    private int chat_id;

    @ManyToOne(fetch = FetchType.LAZY,optional=true)
    @JoinTable(name = "chats", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "chat_id"))
    private Chat chat;

    @Column(name = "user_id", nullable = false)
    private int user_id;

    @Column(name = "private_user", nullable = false)
    private int privateUser;

    @Column(name = "is_new", nullable = false)
    private int isNew;

    public Integer getId() {
        return id;
    }

    public int getChat_id() {
        return chat_id;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPrivateUser() {
        return privateUser;
    }

    public void setPrivateUser(int privateUser) {
        this.privateUser = privateUser;
    }

    public int getIsNew() {
        return isNew;
    }

    public void setIsNew(int isNew) {
        this.isNew = isNew;
    }
}
