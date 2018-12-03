package com.hirehawk.messaging_service.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "chat_messages")
public class ChatMessage implements Serializable {
    private static final long serialVersionUID = -2054386655979281969L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "author_id", nullable = false)
    private String author_id;

    @Column(name = "reciever_id", nullable = false)
    private String reciever_id;

    @Column(name = "chat_id", nullable = false)
    private String chat_id;

    @ManyToOne(fetch = FetchType.LAZY,optional=true)
    @JoinTable(name = "chats", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "chat_id"))
    private Chat chat;

    @Column(name = "mediafile_id", nullable = false)
    private int mediafile_id;

    @OneToOne(fetch = FetchType.LAZY,optional=true)
    @JoinTable(name = "chat_media_files", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "mediafile_id"))
    private ChatMediaFile mediaFile;

    @Column(name = "text", columnDefinition="TEXT")
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "stamp", nullable = false)
    private Date stamp;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "edit_stamp")
    private Date editStamp;

    @Column(name = "deleted")
    private boolean deleted;

    public String getId() {
        return id;
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

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public void setChatId(String chat_id) {
        this.chat_id = chat_id;
    }

    public ChatMediaFile getMediaFile() {
        return mediaFile;
    }

    public void setMediaFile(int mediaFile) {
        this.mediafile_id = mediaFile;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getStamp() {
        return stamp;
    }

    public void setStamp(Date stamp) {
        this.stamp = stamp;
    }

    public Date getEditStamp() {
        return editStamp;
    }

    public void setEditStamp(Date editStamp) {
        this.editStamp = editStamp;
    }


    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
