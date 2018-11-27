package com.hirehawk.messaging_service.controllers;

import com.hirehawk.messaging_service.dao.ChatDAO;
import com.hirehawk.messaging_service.entity.ChatMessage;
import com.hirehawk.messaging_service.entity.ChatUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Transactional
@RequestMapping("/updateChat")
public class UpdateController {
    @Autowired
    private ChatDAO chatDAO;

    /*@RequestMapping(value = "/test", produces = "application/json")
    public int home(int a, String s) {
        System.out.println(s);
        return a;
    }*/

    @RequestMapping(value = "/getAllUnreadMessages", produces = "application/json")
    List<ChatMessage> getAllUnreadMessages(@PathVariable("userId") String userId) {
        return chatDAO.getAllUnreadMessages(userId);
    }

    @RequestMapping(value = "/getAllUnreadMessagesIds", produces = "application/json")
    List getAllUnreadMessagesIds(String userId) {
        return chatDAO.getAllUnreadMessagesIds(userId);
    }

    @RequestMapping(value = "/getAllUserUnreadMessages", produces = "application/json")
    List<ChatMessage> getAllUserUnreadMessages(String userId, String chatId) {
        return chatDAO.getAllUserUnreadMessages(userId, chatId);
    }

    @RequestMapping(value = "/getAllUserConversations", produces = "application/json")
    List<Object> getAllUserConversations(String userId) {
        return chatDAO.getAllUserConversations(userId);
    }

    @RequestMapping(value = "/getAllUserConversationMessages", produces = "application/json")
    List<ChatMessage> getAllUserConversationMessages(String userId, String chatId) {
        return chatDAO.getAllUserConversationMessages(userId, chatId);
    }

    @RequestMapping(value = "/checkParticipate", produces = "application/json")
    boolean checkParticipate(String userId, String dialogId, String chatId) {
        return chatDAO.checkParticipate(userId, dialogId, chatId);
    }

    @RequestMapping(value = "/hasNewChats", produces = "application/json")
    boolean hasNewChats(String userId) {
        return chatDAO.hasNewChats(userId);
    }

    @RequestMapping(value = "/getChatUsers", produces = "application/json")
    List<ChatUser> getChatUsers(String chatId) {
        return chatDAO.getChatUsers(chatId);
    }
}
