package com.hirehawk.search_service.module.usersearch;

import com.hirehawk.search_service.models.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserIndexService {

    public void addToIndex(String id, String name, String surname, String username, String email, String phone);

    public void updateIndex(String id, String name, String surname, String username, String email, String phone);

    public void deleteFromIndex(String id);

    public void deleteAll();

    public String[] search(String searchValue);
}
