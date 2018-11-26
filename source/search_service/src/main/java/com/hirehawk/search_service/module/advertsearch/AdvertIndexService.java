package com.hirehawk.search_service.module.advertsearch;

public interface AdvertIndexService {

    public void addToIndex(String id, String name, String category, String info,
                           String location, float price, long num_of_hours, String user);

    public void updateIndex(String id, String name, String category, String info,
                     String location, String price, String num_of_hours, String user);

    public void deleteFromIndex(String id);

    public String[] search(String searchValue, String category, boolean info, String location, String minPrice,
                    String maxPrice, String num_of_hours, String user);
}
