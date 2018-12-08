package com.hirehawk.search_service.controllers;

import com.hirehawk.search_service.module.advertsearch.AdvertIndexService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/advertSearch/")
public class AdvertSearchController {

    @Autowired
    AdvertIndexService indexService;

    @RequestMapping(value = "indexAdvert")
    public void indexAdvert(String id, String name, String category, String info,
                            String location, float price, long num_of_hours, String usersId) {

        System.out.println(id);
        indexService.addToIndex(id, name, category, info, location, price, num_of_hours, usersId);
    }

    @RequestMapping(value = "updateAdvert")
    public void updateAdvert(String id, String name, String category, String info,
                            String location, String price, String num_of_hours, String usersId) {
        indexService.updateIndex(id, name, category, info, location, price, num_of_hours, usersId);
    }

    @RequestMapping(value = "deleteAdvert")
    public void deleteAdvert(String id) {
        indexService.deleteFromIndex(id);
    }

    @RequestMapping(value = "findAdvert")
    public String[] findAdvert(String searchValue, String category, boolean info, String location, String minPrice,
                           String maxPrice, String num_of_hours, String user) {
        String[] adverts = indexService.search(searchValue, category, info, location, minPrice, maxPrice,
                num_of_hours, user);

        System.out.println("search");
        return adverts;
    }
}
