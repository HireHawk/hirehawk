package com.hirehawk.search_service.module.advertsearch;

import com.hirehawk.search_service.models.Advert;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class AdvertQueriesRepositoryImpl implements AdvertQueriesRepository {

    @Resource
    private SolrTemplate solrTemplate;

    @Override
    public List<Advert> search(String searchTerm, String category, boolean info, String location, String minPrice,
                               String maxPrice, String num_of_hours, String user) {
        Query search;
      //  searchTerm = searchTerm.replace(" ", "~ ");
        if (searchTerm != null && location != null && info) {
            search = new SimpleQuery("(name:" + searchTerm + " OR info:" + searchTerm +
                                      ") AND location:" + location);
        } else {
            if (searchTerm != null && info) {
                search = new SimpleQuery("name:" + searchTerm + " OR info:" + searchTerm);
            } else {
                if (searchTerm != null && location != null) {
                    search = new SimpleQuery("name:" + searchTerm + " AND location:" + location);
                } else {
                    if (location != null) {
                        search = new SimpleQuery("location:" + location);
                    } else {
                        if (searchTerm != null) {
                            search = new SimpleQuery("name:" + searchTerm);
                        } else {
                            search = new SimpleQuery("*:*");
                        }
                    }
                }
            }
        }
        if (category != null) {
            FilterQuery fq = new SimpleFilterQuery(new Criteria("category").is(category));
            search.addFilterQuery(fq);
        }
        if (minPrice != null || maxPrice != null) {
            String min, max;
            FilterQuery fq;
            if (minPrice == null) {
                min = "0";
            } else {
                min = minPrice;
            }
            if (maxPrice != null) {
                max = maxPrice;
                fq = new SimpleFilterQuery(new Criteria("price").between(min, max));
            } else {
                fq = new SimpleFilterQuery(new Criteria("price").greaterThan(min));
            }
            search.addFilterQuery(fq);
        }
        if (num_of_hours != null) {
            FilterQuery fq = new SimpleFilterQuery(new Criteria("num_of_hours").is(Long.valueOf(num_of_hours)));
            search.addFilterQuery(fq);
        }
        if (user != null) {
            FilterQuery fq = new SimpleFilterQuery(new Criteria("user_id").is(user));
            search.addFilterQuery(fq);
        }

        search.setRows(100000);
        Page results = solrTemplate.query("adverts", search, Advert.class);
        return results.getContent();
    }
}
