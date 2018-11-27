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
    private String chat_id;

    @ManyToOne(fetch = FetchType.LAZY,optional=true)
    @JoinTable(name = "chats", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "chat_id"))
    private Chat chat;

    @Column(name = "user_id", nullable = false)
    private String user_id;

    @Column(name = "private_user", nullable = false)
    private String privateUser;

    @Column(name = "is_new", nullable = false)
    private boolean isNew;

    public Integer getId() {
        return id;
    }

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPrivateUser() {
        return privateUser;
    }

    public void setPrivateUser(String privateUser) {
        this.privateUser = privateUser;
    }

    public boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }
}
