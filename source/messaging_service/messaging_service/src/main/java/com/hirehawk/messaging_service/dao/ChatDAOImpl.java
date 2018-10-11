package com.hirehawk.messaging_service.dao;


import com.hirehawk.messaging_service.entity.Chat;
import com.hirehawk.messaging_service.entity.ChatMediaFile;
import com.hirehawk.messaging_service.entity.ChatMessage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

@Transactional
@Repository
public class ChatDAOImpl implements ChatDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addChat(String name, int icon, String additionalInfo) {
        Chat chat = new Chat();
        chat.setAdditionalInfo(additionalInfo);
        chat.setName(name);
        chat.setIcon(icon);

        Session session = this.sessionFactory.getCurrentSession();
        session.persist(chat);
        session.flush();
    }

    @Override
    public void addMediaFile(String filename, String extention, byte[] media, String mimetype, byte[] thumbnail) {
        ChatMediaFile mediaFile = new ChatMediaFile();
        mediaFile.setFilename(filename);
        mediaFile.setExtention(extention);
        mediaFile.setMedia(media);
        mediaFile.setMimetype(mimetype);
        mediaFile.setThumbnail(thumbnail);

        Session session = this.sessionFactory.getCurrentSession();
        session.persist(mediaFile);
        session.flush();
    }

    @Override
    public void addMessage(int author, int receiver, int chat, String text, int mediaFile, Date stamp, Date editStamp, int deleted) {
        ChatMessage message = new ChatMessage();
        message.setAuthor_id(author);
        message.setReciever_id(receiver);
        message.setChatId(chat);
        message.setText(text);
        message.setMediaFile(mediaFile);
        message.setStamp(stamp);
        message.setEditStamp(editStamp);
        message.setDeleted(deleted);
        message.setChat(getChatById(chat));


        Session session = this.sessionFactory.getCurrentSession();
        session.persist(message);
        session.flush();
    }

    @Override
    public Chat getChatById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.find(Chat.class, id);
    }

    @Override
    public ChatMediaFile getMediaFileById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.find(ChatMediaFile.class, id);
    }
}
