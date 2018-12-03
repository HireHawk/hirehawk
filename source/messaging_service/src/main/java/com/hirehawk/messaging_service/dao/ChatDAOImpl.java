package com.hirehawk.messaging_service.dao;


import com.hirehawk.messaging_service.entity.Chat;
import com.hirehawk.messaging_service.entity.ChatMediaFile;
import com.hirehawk.messaging_service.entity.ChatMessage;
import com.hirehawk.messaging_service.entity.ChatMessageStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public void deleteChat(String id) {
        Session session = this.sessionFactory.getCurrentSession();
        Chat chat = this.getChatById(id);

        session.delete(chat);
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
    public void addMessage(String author, String receiver, String chat, String text, int mediaFile, Date stamp, Date editStamp, boolean deleted) {
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
    public Chat getChatById(String id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.load(Chat.class,  3);
    }

    @Override
    public ChatMediaFile getMediaFileById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.load(ChatMediaFile.class, id);
    }

    @Override
    public  ChatMessage getMessageById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.load(ChatMessage.class, id);
    }

    @Override
    public void editMessage(int id, String text, Date stamp) {
        Session session = this.sessionFactory.getCurrentSession();
        ChatMessage oldMess = this.getMessageById(id);
        oldMess.setText(text);
        oldMess.setEditStamp(stamp);

        session.update(oldMess);
        session.flush();
    }

    @Override
    public void deleteMessage(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        ChatMessage mess = this.getMessageById(id);

        session.delete(mess);
        session.flush();
    }

    @Override
    public void userDeleteMessage(int id, Date stamp) {
        Session session = this.sessionFactory.getCurrentSession();
        ChatMessage mess = this.getMessageById(id);
        mess.setDeleted(true);
        mess.setEditStamp(stamp);

        session.update(mess);
        session.flush();
    }

    @Override
    public void userUndoDeleteMessage(int id, Date stamp) {
        Session session = this.sessionFactory.getCurrentSession();
        ChatMessage mess = this.getMessageById(id);
        mess.setDeleted(false);
        mess.setEditStamp(stamp);

        session.update(mess);
        session.flush();
    }

    @Override
    public List getAllUserConversationMessages(String userId, String chatId) {
        Session session = this.sessionFactory.getCurrentSession();

        String sql = "FROM ChatMessage cm " +
                "WHERE (cm.reciever_id = :userId AND cm.author_id = :chatId) " +
                    "OR (cm.reciever_id = :chatId AND cm.author_id = :userId)";
        Query query = session.createQuery(sql)
                .setParameter("userId", userId)
                .setParameter("chatId", chatId);

        return query.list();
    }

    @Override
    public List getAllUserChatMessages(String userId, String chatId) {
        Session session = this.sessionFactory.getCurrentSession();

        String sqlUpd = "UPDATE ChatUser cu SET cu.isNew = 0 " +
                "WHERE cu.chat_id = :chatId AND cu.user_id = :userId";
        Query queryUpd = session.createQuery(sqlUpd)
                .setParameter("chatId", chatId)
                .setParameter("userId", userId);
        int result = queryUpd.executeUpdate();

        if (result != 1) {
            System.out.println("Incorrect update in getAllUserChatMessages, result: " + result);
        }

        String sql = "FROM ChatMessage cm WHERE cm.chat_id = :chatId";
        Query query = session.createQuery(sql)
                .setParameter("chatId", chatId);

        return query.list();
    }

    @Override
    public void setNewUnreadMessage(int messageId, String authorId, String receiverId, String chatId) {
        ChatMessageStatus status = new ChatMessageStatus();
        status.setMessage_id(messageId);
        status.setAuthor_id(authorId);
        status.setReciever_id(receiverId);
        status.setChat_id(chatId);

        Session session = this.sessionFactory.getCurrentSession();
        session.persist(status);
        session.flush();
    }

    @Override
    public void setMessagesAsReaded(String receiverId, String authorId, String chatId, int lastId) {
        Session session = this.sessionFactory.getCurrentSession();

        String sql = "DELETE ChatMessageStatus " +
                "WHERE reciever_id = :receiverId " +
                "AND (author_id = :authorId OR chat_id = :chatId) AND message_id <= :lastId;";

        Query query = session.createQuery(sql)
                .setParameter("lastId", lastId)
                .setParameter("chatId", chatId)
                .setParameter("authorId", authorId);

        query.executeUpdate();
    }

    @Override
    public void setDialog(String firstId, String secondId) {
        Session session = this.sessionFactory.getCurrentSession();

        String sql = "INSERT INTO ChatUser(user_id, private_user)" +
                "SELECT b.v1, b.v2 " +
                "FROM " +
                    "(SELECT IF(a.num = 0, $firstId, NULL) AS v1, IF(a.num = 0, $secondId, NULL) AS v2 " +
                    "FROM " +
                        "(SELECT COUNT(*) AS num " +
                        "FROM chats_users " +
                        "WHERE (chat_id = '' AND ((user_id = $firstId AND private_user = $secondId) " +
                            "OR (user_id = $secondId AND private_user = $firstId)))" +
                        ") AS a" +
                    ") AS b " +
                "WHERE (b.v1 IS NOT NULL) AND (b.v2 IS NOT NULL)";
        Query query = session.createQuery(sql)
                .setParameter("firstId", firstId)
                .setParameter("secondId", secondId);

        query.executeUpdate();
    }

    @Override
    public List getChatUsers(String chatId) {
        Session session = this.sessionFactory.getCurrentSession();

        String sql = "FROM ChatUser WHERE chat_id = :chatId";

        Query query = session.createQuery(sql)
                .setParameter("chatId", chatId);

        return query.list();
    }

    @Override
    public void addChatUser(String chatId, String userId) {
        Session session = this.sessionFactory.getCurrentSession();

        String sql = "INSERT INTO ChatUser (chat_id, user_id) " +
                "SELECT b.v1, b.v2 " +
                "FROM " +
                    "(SELECT IF(a.num = 0, :chatId, NULL) AS v1, IF(a.num = 0, :userId, NULL) AS v2 " +
                    "FROM " +
                        "(SELECT COUNT(*) AS num " +
                        "FROM ChatUser " +
                        "WHERE (chat_id = :chatId AND user_id = :userId)" +
                        ") AS a" +
                    ") AS b " +
                "WHERE (b.v1 IS NOT NULL) AND (b.v2 IS NOT NULL)";

        Query query = session.createQuery(sql)
                .setParameter("chatId", chatId)
                .setParameter("userId", userId);

        query.executeUpdate();
    }

    @Override
    public void removeChatUser(String chatId, String userId) {
        Session session = this.sessionFactory.getCurrentSession();

        String sql = "DELETE FROM ChatUser " +
                "WHERE chat_id = :chatId AND user_id = :userId";

        Query query = session.createQuery(sql)
                .setParameter("chatId", chatId)
                .setParameter("userId", userId);

        query.executeUpdate();
    }

    @Override
    public List getAllUnreadMessages(String userId) {
        Session session = this.sessionFactory.getCurrentSession();

        String sql = "FROM ChatMessage cm " +
                "INNER JOIN ChatMessageStatus " +
                "WHERE message_id = cm.id" +
                        "AND reciever_id = :userId)";

        Query query = session.createQuery(sql)
                .setParameter("userId", userId);

        return query.list();
    }

    @Override
    public List getAllUnreadMessagesIds(String userId) {
        Session session = this.sessionFactory.getCurrentSession();

        String sql = "SELECT cm.id " +
                "FROM ChatMessage cm " +
                    "INNER JOIN ChatMessageStatus as cms " +
                    "WHERE cms.message_id = cm.id AND cms.reciever_id = :userId";

        Query query = session.createQuery(sql)
                .setParameter("userId", userId);

        return query.list();
    }

    @Override
    public List getAllUserUnreadMessages(String userId, String chatId) {
        Session session = this.sessionFactory.getCurrentSession();

        String sql = "FROM ChatMessage cm " +
                "INNER JOIN ChatMessageStatus " +
                "WHERE message_id = cm.id AND reciever_id = :userId " +
                    "AND author_id = :chatId)";

        Query query = session.createQuery(sql)
                .setParameter("chatId", chatId)
                .setParameter("userId", userId);

        return query.list();
    }

    @Override
    public List<Object> getAllUserConversations(String userId) {
        Session session = this.sessionFactory.getCurrentSession();

        String sqlChat = "SELECT cu.chat_id " +
                "FROM ChatUser cu " +
                "WHERE cu.user_id = :userId AND cu.chat_id <> ''";
        String sqlDialog = "SELECT IF(cu.user_id = :userId, cu.private_user, cu.user_id) AS private " +
                "FROM ChatUser cu " +
                "WHERE cu.chat_id = '' AND (cu.user_id = :userId OR cu.private_user = :userId)";

        Query queryChat = session.createQuery(sqlChat)
                .setParameter("userId", userId);

        Query queryDialog = session.createQuery(sqlDialog)
                .setParameter("userId", userId);


        // TODO: separate
        List<Object> both = new ArrayList<Object>(queryChat.list());
        both.addAll(queryDialog.list());
        return both;
    }

    @Override
    public boolean checkParticipate(String userId, String dialogId, String chatId) {
        Session session = this.sessionFactory.getCurrentSession();

        String sql = "SELECT COUNT(cu) " +
                "FROM ChatUser cu " +
                "WHERE cu.chat_id = :chatId " +
                    "AND ((cu.user_id = :userId AND cu.private_user = :dialogId) " +
                    "OR (cu.chat_id = '' AND cu.user_id = :dialogId AND cu.private_user = :userId))";

        Query query = session.createQuery(sql)
                .setParameter("chatId", chatId)
                .setParameter("userId", userId)
                .setParameter("dialogId", dialogId);

        Long count = (Long)query.uniqueResult();
        return count > 0;
    }

    @Override
    public boolean hasNewChats(String userId) {
        Session session = this.sessionFactory.getCurrentSession();

        String sql = "SELECT count(cu) FROM ChatUser cu " +
                "WHERE cu.isNew = 1 AND cu.user_id = :userId";

        Query query = session.createQuery(sql)
                .setParameter("userId", userId);

        Long count = (Long)query.uniqueResult();
        return count != 0;
    }
}
