package com.hirehawk.messaging_service.controllers;

import com.hirehawk.messaging_service.dao.ChatDAO;
import com.hirehawk.messaging_service.dao.ChatDAOImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@Transactional
@RequestMapping("/adminChat")
public class AdminController {
    @Autowired
    private ChatDAOImpl chatDAO;

    @PostMapping("/addChat")
    void addChat(String name, Integer icon, String additionalInfo) {
        chatDAO.addChat(name, (icon==null)?-1:icon, additionalInfo);
    }

    @PostMapping("/deleteMessage")
    void deleteMessage(int id) {
        chatDAO.deleteMessage(id);
    }

    @PostMapping("/setDialog")
    void setDialog(String firstId, String secondId) {
        chatDAO.setDialog(firstId, secondId);
    }

    @PostMapping("/addChatUser")
    void addChatUser(String chatId, String userId) {
        chatDAO.addChatUser(chatId, userId);
    }

    @PostMapping("/removeChatUser")
    void removeChatUser(String chatId, String userId) {
        chatDAO.removeChatUser(chatId, userId);
    }

    @PostMapping("/deleteChat")
    public void deleteChat(String id) {
        chatDAO.deleteChat(id);
    }

}
