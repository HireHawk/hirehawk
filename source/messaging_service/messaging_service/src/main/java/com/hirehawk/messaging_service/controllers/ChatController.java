package com.hirehawk.messaging_service.controllers;

import com.hirehawk.messaging_service.dao.ChatDAO;
import com.hirehawk.messaging_service.entity.Chat;
import com.hirehawk.messaging_service.entity.ChatMediaFile;
import com.hirehawk.messaging_service.entity.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;


@RestController
@Transactional
public class ChatController {

    @Autowired
    private ChatDAO chatDAO;

    /*@RequestMapping("/test")
    public String home() {
        chatDAO.addChat("testChat", 1, "success");
        chatDAO.addMediaFile("mediafile 1", "png", null, null, null);
        Chat chat = chatDAO.getChatById(17);
        System.out.println(chat.getName());
        chatDAO.addMessage(1, 2, chat.getId(), "some message", 0, new Date(2018,10,8,17,10,20),
               null, 0);
        return "index";
    }*/

    @RequestMapping("/addMediaFile")
    void addMediaFile(String extention, byte[] media, String mimetype, byte[] thumbnail) {
        chatDAO.addMediaFile(media.toString(), extention, media, mimetype, thumbnail);
    }

    @PostMapping("/addMessage")
    void addMessage(int author, int receiver, int chat, String text, int mediaFile, Date stamp, int deleted) {
        chatDAO.addMessage(author, receiver, chat, text, mediaFile, stamp, new Date(), deleted);
    }

    @RequestMapping(value = "/getChatById", produces = "application/json")
    Chat getChatById(int id) {
        return chatDAO.getChatById(id);
    }

    @RequestMapping(value = "/getMediaFileById", produces = "application/json")
    ChatMediaFile getMediaFileById(int id) {
        return chatDAO.getMediaFileById(id);
    }

    @RequestMapping(value = "/getMessageById", produces = "application/json")
    ChatMessage getMessageById(int id) {
        return chatDAO.getMessageById(id);
    }

    @RequestMapping("/editMessage")
    void editMessage(int id, String text) {
        chatDAO.editMessage(id, text, new Date());
    }

    @RequestMapping("/userDeleteMessage")
    void userDeleteMessage(int id) {
        chatDAO.userDeleteMessage(id, new Date());
    }

    @RequestMapping("/userUndoDeleteMessage")
    void userUndoDeleteMessage(int id) {
        chatDAO.userUndoDeleteMessage(id, new Date());
    }

    @RequestMapping(value = "/getAllUserConversationMessages", produces = "application/json")
    List<ChatMessage> getAllUserConversationMessages(int userId, int chatId) {
        return chatDAO.getAllUserConversationMessages(userId, chatId);
    }

    @RequestMapping(value = "/getAllUserChatMessages", produces = "application/json")
    List<ChatMessage> getAllUserChatMessages(int userId, int chatId) {
        return chatDAO.getAllUserConversationMessages(userId, chatId);
    }

    @RequestMapping("/setNewUnreadMessage")
    void setNewUnreadMessage(int messageId, int authorId, int receiverId, int chatId) {
        chatDAO.setNewUnreadMessage(messageId, authorId, receiverId, chatId);
    }

    @RequestMapping("/setMessagesAsReaded")
    void setMessagesAsReaded(int receiverId, int authorId, int chatId, int lastId) {
        chatDAO.setMessagesAsReaded(receiverId, authorId, chatId, lastId);
    }

}
