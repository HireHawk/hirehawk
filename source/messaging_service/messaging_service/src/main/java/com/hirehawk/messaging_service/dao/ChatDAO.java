package com.hirehawk.messaging_service.dao;

import com.hirehawk.messaging_service.entity.Chat;
import com.hirehawk.messaging_service.entity.ChatMediaFile;
import com.hirehawk.messaging_service.entity.ChatMessage;
import com.hirehawk.messaging_service.entity.ChatUser;

import java.util.Date;
import java.util.List;

public interface ChatDAO {

    void addChat(String name, int icon, String additionalInfo);

    public void deleteChat(String id);

    void addMediaFile(String filename, String extention, byte[] media, String mimetype, byte[] thumbnail);

    void addMessage(String author, String receiver, String chat, String text, int mediaFile, Date stamp, Date editStamp, int deleted);

    Chat getChatById(String id);

    ChatMediaFile getMediaFileById(int id);

    ChatMessage getMessageById(int id);

    void editMessage(int id, String text, Date stamp);

    void deleteMessage(int id);

    void userDeleteMessage(int id, Date stamp);

    void userUndoDeleteMessage(int id, Date stamp);

    List<ChatMessage> getAllUserConversationMessages(String userId, String chatId);

    List<ChatMessage> getAllUserChatMessages(String userId, String chatId);

    void setNewUnreadMessage(int messageId, String authorId, String receiverId, String chatId);

    void setMessagesAsReaded(String receiverId, String authorId, String chatId, int lastId);

    void setDialog(String firstId, String secondId);

    List<ChatUser> getChatUsers(String chatId);

    void addChatUser(String chatId, String userId);

    void removeChatUser(String chatId, String userId);

    List<ChatMessage> getAllUnreadMessages(String userId);

    List getAllUnreadMessagesIds(String userId);

    List<ChatMessage> getAllUserUnreadMessages(String userId, String chatId);

    List<Object> getAllUserConversations(String userId);

    boolean checkParticipate(String userId, String dialogId, String chatId);

    boolean hasNewChats(String userId);
}
