package com.hirehawk.search_service.module.usersearch;

import com.hirehawk.search_service.models.User;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface UserRepository extends UserQueriesRepository, SolrCrudRepository<User, String> {
}
