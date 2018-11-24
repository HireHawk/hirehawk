 package com.hirehawk.userService.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import  com.hirehawk.userService.dao.User;
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    User findById(Integer id);
}
 
