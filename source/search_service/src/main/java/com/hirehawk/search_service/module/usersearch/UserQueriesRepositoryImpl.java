package com.hirehawk.search_service.module.usersearch;

import com.hirehawk.search_service.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class UserQueriesRepositoryImpl implements UserQueriesRepository {

    @Resource
    private SolrTemplate solrTemplate;

    @Override
    public List<User> search(String searchTerm) {
        Criteria criteria = new Criteria().expression("username:\"" + searchTerm + "\" OR email:\"" + searchTerm +
                "\" OR phone:\"" + searchTerm + "\"");
        Criteria sn = createSearchConditions(searchTerm);
        if (sn != null) {
            criteria = criteria.or(sn);
        }
        SimpleQuery search = new SimpleQuery(criteria);
        search.setRows(100000);
        Page results = solrTemplate.queryForPage("users", search, User.class);
        return results.getContent();
    }

    private Criteria createSearchConditions(String term) {
        String[] words = term.split(" ");
        Criteria conditions = null;

        if (words.length == 1) {
            conditions = new Criteria("name").contains(words[0])
                    .or(new Criteria("surname").contains(words[0]));
        }
        if (words.length > 1) {
            conditions = new Criteria("name").in(words)
                    .and(new Criteria("surname").in(words));
        }
        return conditions;
    }
}
