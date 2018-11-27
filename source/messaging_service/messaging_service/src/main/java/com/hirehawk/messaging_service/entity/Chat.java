package com.hirehawk.messaging_service.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "chats")
public class Chat implements Serializable {
    private static final long serialVersionUID = -2054386655979281969L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", length = 255, nullable = true)
    private String name;

    @Column(name = "icon", nullable = true)
    private int icon;

    @Column(name = "additional_info", columnDefinition="TEXT", nullable = true)
    private String additionalInfo;

    @OneToMany(mappedBy = "chat", fetch = FetchType.LAZY)
    private Set<ChatMessage> messages;

    @OneToMany(mappedBy = "chat", fetch = FetchType.LAZY)
    private Set<ChatUser> users;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Set<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(Set<ChatMessage> messages) {
        this.messages = messages;
    }

    public Set<ChatUser> getUsers() {
        return users;
    }

    public void setUsers(Set<ChatUser> users) {
        this.users = users;
    }
}

