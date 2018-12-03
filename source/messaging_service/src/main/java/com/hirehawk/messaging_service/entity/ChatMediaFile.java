package com.hirehawk.messaging_service.entity;



import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "chat_media_files")
public class ChatMediaFile implements Serializable {
    private static final long serialVersionUID = -2054386655979281969L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "filename", length = 65, nullable = false)
    private String filename;

    @Column(name = "extention", length = 11, nullable = false)
    private String extention;

    @Lob
    @Column(name = "media", columnDefinition="LONGBLOB")
    private byte[] media;

    @Column(name = "mimetype", length = 15)
    private String mimetype;

    @Lob
    @Column(name = "thumbnail", columnDefinition="LONGBLOB")
    private byte[] thumbnail;

    @OneToOne(mappedBy = "mediaFile", fetch = FetchType.LAZY)
    private ChatMessage message;

    public ChatMessage getMessage() {
        return message;
    }

    public void setMessage(ChatMessage message) {
        this.message = message;
    }

    public Integer getId() {

        return id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getExtention() {
        return extention;
    }

    public void setExtention(String extention) {
        this.extention = extention;
    }

    public byte[] getMedia() {
        return media;
    }

    public void setMedia(byte[] media) {
        this.media = media;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public byte[] getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }
}
