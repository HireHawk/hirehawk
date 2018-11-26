package com.hirehawk.search_service.controllers;

import com.hirehawk.search_service.models.User;
import com.hirehawk.search_service.module.usersearch.UserIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/userSearch/")
public class UserSearchController {
    @Autowired
    UserIndexService indexService;

    @RequestMapping(value = "indexUser")
    public void indexAdvert(String id, String name, String surname, String username, String email, String phone) {
        indexService.addToIndex(id, name, surname, username, email, phone);
    }

    @RequestMapping(value = "updateUser")
    public void updateAdvert(String id, String name, String surname, String username, String email, String phone) {
        indexService.updateIndex(id, name, surname, username, email, phone);
    }

    @RequestMapping(value = "deleteUser")
    public void deleteAdvert(String id) {
        indexService.deleteFromIndex(id);
    }

    @RequestMapping(value = "findUser")
    public String[] findUser(String searchValue) {
        String[] users = indexService.search(searchValue);
        return users;
    }
}
