package com.hirehawk.messaging_service.controllers;

import com.hirehawk.messaging_service.dao.ChatDAO;
import com.hirehawk.messaging_service.entity.Chat;
import com.hirehawk.messaging_service.entity.ChatMediaFile;
import com.hirehawk.messaging_service.entity.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;


@RestController
@Transactional
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatDAO chatDAO;

    @GetMapping(value = "/test", produces = "application/json")
    public Integer home(Integer a, String s) {
        System.out.println(s);
        return a;
    }

    @PostMapping("/addMediaFile")
    void addMediaFile(String extention, String media, String mimetype, String thumbnail) {
    	chatDAO.addMediaFile(media.toString(), extention, media.getBytes(), mimetype, thumbnail.getBytes());
    }

    @PostMapping("/addMessage")
    void addMessage(String author, String receiver, String chat, String text, Integer mediaFile, Date stamp, Boolean deleted) {
        chatDAO.addMessage(author, receiver, chat, text, (mediaFile==null)?-1:mediaFile, stamp, new Date(), (deleted==null)?false:deleted);
    }

    @GetMapping(value = "/getChatById", produces = "application/json")
    Chat getChatById(String id) {
        return chatDAO.getChatById(id);
    }

    @GetMapping(value = "/getMediaFileById", produces = "application/json")
    ChatMediaFile getMediaFileById(Integer id) {
        return chatDAO.getMediaFileById((id==null)?-1:id);
    }

    @GetMapping(value = "/getMessageById", produces = "application/json")
    ChatMessage getMessageById(Integer id) {
        return chatDAO.getMessageById((id==null)?-1:id);
    }

    @PostMapping("/editMessage")
    void editMessage(Integer id, String text) {
        chatDAO.editMessage((id==null)?-1:id, text, new Date());
    }

    @PostMapping("/userDeleteMessage")
    void userDeleteMessage(Integer id) {
        chatDAO.userDeleteMessage((id==null)?-1:id, new Date());
    }

    @PostMapping("/userUndoDeleteMessage")
    void userUndoDeleteMessage(Integer id) {
        chatDAO.userUndoDeleteMessage((id==null?-1:id), new Date());
    }

    @PostMapping("/setNewUnreadMessage")
    void setNewUnreadMessage(Integer messageId, String authorId, String receiverId, String chatId) {
        chatDAO.setNewUnreadMessage((messageId==null)?-1:messageId, authorId, receiverId, chatId);
    }

    @PostMapping("/setMessagesAsReaded")
    void setMessagesAsReaded(String receiverId, String authorId, String chatId, Integer lastId) {
        chatDAO.setMessagesAsReaded(receiverId, authorId, chatId, (lastId==null?-1:lastId));
    }

}
