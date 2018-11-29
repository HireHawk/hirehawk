package com.hirehawk.userService.services;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.hirehawk.userService.dao.User;
import com.hirehawk.userService.dao.UserRepository;

@Service
public class UserService {

	@Autowired
    private UserRepository repository;

	static {
		// Initialize Data

		//User tapok = new User("111001", "Only","One","Man","some@gmail.com","photouri,photouri!", "+38067275832");
		//users.add(tapok);
	}

    public List<User> findAll() {

        List<User> users = (List<User>) repository.findAll();
        return users;
    }
    public Boolean save(User user) {
        repository.save(user);
        return true;
    }
    public User update(User user) {
    	User u = user.withMissingResolved(repository);
    	if(u==null)return null;
        return repository.save(user);
        
    }
    public User get(String id) {
    	User u = repository.findById(id);
    	if(u==null)u=new User(id,null,null,null,null);
        return u;
        
    }
	
}

