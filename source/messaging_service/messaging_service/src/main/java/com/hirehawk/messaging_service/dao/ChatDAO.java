package com.hirehawk.messaging_service.dao;

import com.hirehawk.messaging_service.entity.Chat;
import com.hirehawk.messaging_service.entity.ChatMediaFile;
import com.hirehawk.messaging_service.entity.ChatMessage;
import com.hirehawk.messaging_service.entity.ChatUser;

import java.util.Date;
import java.util.List;

public interface ChatDAO {

    void addChat(String name, int icon, String additionalInfo);

    public void deleteChat(int id);

    void addMediaFile(String filename, String extention, byte[] media, String mimetype, byte[] thumbnail);

    void addMessage(int author, int receiver, int chat, String text, int mediaFile, Date stamp, Date editStamp, int deleted);

    Chat getChatById(int id);

    ChatMediaFile getMediaFileById(int id);

    ChatMessage getMessageById(int id);

    void editMessage(int id, String text, Date stamp);

    void deleteMessage(int id);

    void userDeleteMessage(int id, Date stamp);

    void userUndoDeleteMessage(int id, Date stamp);

    List<ChatMessage> getAllUserConversationMessages(int userId, int chatId);

    List<ChatMessage> getAllUserChatMessages(int userId, int chatId);

    void setNewUnreadMessage(int messageId, int authorId, int receiverId, int chatId);

    void setMessagesAsReaded(int receiverId, int authorId, int chatId, int lastId);

    void setDialog(int firstId, int secondId);

    List<ChatUser> getChatUsers(int chatId);

    void addChatUser(int chatId, int userId);

    void removeChatUser(int chatId, int userId);

    List<ChatMessage> getAllUnreadMessages(int userId);

    List getAllUnreadMessagesIds(int userId);

    List<ChatMessage> getAllUserUnreadMessages(int userId, int chatId);

    List<Object> getAllUserConversations(int userId);

    boolean checkParticipate(int userId, int dialogId, int chatId);

    boolean hasNewChats(int userId);
}
