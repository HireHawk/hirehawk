package com.hirehawk.search_service.module;

import com.hirehawk.search_service.models.Advert;

import java.util.List;

public interface AdvertQueriesRepository {

    public List<Advert> search(String searchTerm, String category, boolean info, String location, String minPrice,
                               String maxPrice, String num_of_hours, String user, String rented);
}
