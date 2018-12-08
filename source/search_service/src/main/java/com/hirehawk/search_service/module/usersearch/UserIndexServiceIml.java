package com.hirehawk.search_service.module.usersearch;

import com.hirehawk.search_service.models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserIndexServiceIml implements UserIndexService {

    @Resource
    private UserRepository repository;

    @Transactional
    @Override
    public void addToIndex(String id, String name, String surname, String username, String email, String phone) {
        User user = new User(id, name, surname, username, email, phone);
        repository.save(user);
    }

    @Transactional
    @Override
    public void updateIndex(String id, String name, String surname, String username, String email, String phone) {
        User user = repository.findById(id).get();
        if (name != null) {
            user.setName(name);
        }
        if (surname != null) {
            user.setSurname(surname);
        }
        if (username != null) {
            user.setUsername(username);
        }
        if (email != null) {
            user.setEmail(email);
        }
        if (phone != null) {
            user.setPhone(phone);
        }
        repository.save(user);
    }

    @Transactional
    @Override
    public void deleteFromIndex(String id) {
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public String[] search(String searchValue) {
        List<User> users = repository.search(searchValue);
        String[] ids = new String[users.size()];
        for (int i = 0; i < users.size(); i++) {
            ids[i] = users.get(i).getUserId();
        }
        return ids;
    }
}
