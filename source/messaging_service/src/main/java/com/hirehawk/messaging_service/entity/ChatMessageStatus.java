package com.hirehawk.messaging_service.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "chat_messages_status")
public class ChatMessageStatus implements Serializable {

    private static final long serialVersionUID = -2054386655979281969L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "message_id", nullable = false)
    private int message_id;

    @Column(name = "author_id", nullable = false)
    private String author_id;

    @Column(name = "reciever_id", nullable = false)
    private String reciever_id;

    @Column(name = "chat_id", nullable = false)
    private String chat_id;

    @ManyToOne(fetch = FetchType.LAZY,optional=true)
    @JoinTable(name = "chats", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "chat_id"))
    private Chat chat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getReciever_id() {
        return reciever_id;
    }

    public void setReciever_id(String reciever_id) {
        this.reciever_id = reciever_id;
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
}
