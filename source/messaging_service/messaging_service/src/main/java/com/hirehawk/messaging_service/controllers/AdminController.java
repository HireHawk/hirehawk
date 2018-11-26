package com.hirehawk.messaging_service.controllers;

import com.hirehawk.messaging_service.dao.ChatDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@RequestMapping("/adminChat")
public class AdminController {
    @Autowired
    private ChatDAO chatDAO;

    @RequestMapping("/addChat")
    void addChat(String name, int icon, String additionalInfo) {
        chatDAO.addChat(name, icon, additionalInfo);
    }

    @RequestMapping("/deleteMessage")
    void deleteMessage(int id) {
        chatDAO.deleteMessage(id);
    }

    @RequestMapping("/setDialog")
    void setDialog(int firstId, int secondId) {
        chatDAO.setDialog(firstId, secondId);
    }

    @RequestMapping("/addChatUser")
    void addChatUser(int chatId, int userId) {
        chatDAO.addChatUser(chatId, userId);
    }

    @RequestMapping("/removeChatUser")
    void removeChatUser(int chatId, int userId) {
        chatDAO.removeChatUser(chatId, userId);
    }

    @RequestMapping("/deleteChat")
    public void deleteChat(int id) {
        chatDAO.deleteChat(id);
    }

}
