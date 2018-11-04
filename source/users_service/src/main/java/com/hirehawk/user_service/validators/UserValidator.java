package com.hirehawk.user_service.validators;

import com.hirehawk.user_service.dao.User;
import com.hirehawk.user_service.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class UserValidator implements Validator {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        if (userRepository.findByEmail(user.getEmail()) != null) {
            errors.reject("Email not unique");
        }

    }
}