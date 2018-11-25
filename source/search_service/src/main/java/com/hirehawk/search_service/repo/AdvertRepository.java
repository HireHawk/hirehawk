package com.hirehawk.search_service.repo;

import com.hirehawk.search_service.models.Advert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

public interface AdvertRepository extends SolrCrudRepository<Advert, String> {

   // public List<Advert> findByName(String name);

    public List<Advert> findByAdvertId(String id);

    public List<Advert> findByCategory(String category);

    public List<Advert> findByNameContainingIgnoreCase(String name);

    public List<Advert> findByNameContainingAndCategory(String name, String category);

    public List<Advert> findByPriceIsBetweenAndCategory(float min, float max, String category);

    public List<Advert> findByPriceIsBetweenAndNameContainingAndCategory(float min, float max, String name, String category);

    public List<Advert> findByLocationAndNameContaining(String location, String name);

    public List<Advert> findByLocationAndCategory(String location, String category);

    public List<Advert> findByLocationAndNameContainingAndCategory(String location, String name, String categoty);

   // public List<Advert> findByN

   // public List<Advert> findByName

   /* @Query("id:*?0* OR name:*?0*")
    public Page<Advert> findByCustomQuery(String searchTerm, Pageable pageable);

    @Query(name = "Product.findByNamedQuery")
    public Page<Advert> findByNamedQuery(String searchTerm, Pageable pageable);*/
}
