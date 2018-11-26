package com.hirehawk.search_service.module.usersearch;

import com.hirehawk.search_service.models.User;

import java.util.List;

public interface UserQueriesRepository {
    public List<User> search(String searchTerm);
}
