package com.hirehawk.search_service.module.advertsearch;

import com.hirehawk.search_service.models.Advert;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface AdvertRepository extends AdvertQueriesRepository, SolrCrudRepository<Advert, String> {

}
