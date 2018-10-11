package com.hirehawk.messaging_service.controllers;

import com.hirehawk.messaging_service.dao.ChatDAO;
import com.hirehawk.messaging_service.entity.Chat;
import com.hirehawk.messaging_service.entity.ChatMediaFile;
import com.hirehawk.messaging_service.entity.ChatUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;


@Controller
@Transactional
public class TestController {

    @Autowired
    private ChatDAO chatDAO;

    @RequestMapping("/")
    public String home() {
        chatDAO.addChat("testChat",1, "success");
        chatDAO.addMediaFile("mediafile 1", "png", null, null, null);
        /*Chat chat = chatDAO.getChatById(17);
        System.out.println(chat.getName());
        chatDAO.addMessage(1, 2, chat.getId(), "some message", 0, new Date(2018,10,8,17,10,20),
               null, 0);*/
        return "index";
    }
}
