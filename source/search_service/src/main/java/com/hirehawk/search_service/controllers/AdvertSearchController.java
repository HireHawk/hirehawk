package com.hirehawk.search_service.controllers;

import com.hirehawk.search_service.module.AdvertIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class AdvertSearchController {

    @Autowired
    AdvertIndexService indexService;

    @RequestMapping(value = "indexAdvert")
    public void indexAdvert(String id, String name, String category, String info,
                            String location, float price, long num_of_hours, String user, boolean rented) {

        System.out.println(id);
        indexService.addToIndex(id, name, category, info, location, price, num_of_hours, user, rented);
    }

    @RequestMapping(value = "updateAdvert")
    public void updateAdvert(String id, String name, String category, String info,
                            String location, String price, String num_of_hours, String user, String rented) {
        indexService.updateIndex(id, name, category, info, location, price, num_of_hours, user, rented);
    }

    @RequestMapping(value = "updateRentStatus")
    public void updateRentStatus(String id, boolean rented) {
        indexService.updateRentStatus(id, rented);
    }



    @RequestMapping(value = "deleteAdvert")
    public void deleteAdvert(String id) {
        indexService.deleteFromIndex(id);
    }

    @RequestMapping(value = "findAdvert")
    public String[] findAdvert(String searchValue, String category, boolean info, String location, String minPrice,
                           String maxPrice, String num_of_hours, String user, String rented) {
        String[] adverts = indexService.search(searchValue, category, info, location, minPrice, maxPrice,
                num_of_hours, user, rented);

        return adverts;
    }




























    /*    @RequestMapping(value = "findAdvertsInCategory")
    public List<Advert> findAdverts(String category) {
        List<Advert> adverts = advertRepository.findByCategory(category);
        System.out.println(adverts.toString());
        return adverts;
    }

    @RequestMapping(value = "findAdvertsByName")
    public List<Advert> findAdvertsByName(String name) {
        List<Advert> adverts = advertRepository.findByNameContainingIgnoreCase(name);
        System.out.println(adverts.toString());
        return adverts;
    }

    @RequestMapping(value = "findAdvertsByNameInCategory")
    public List<Advert> findAdvertsByNameInCategory(String name, String category) {
        List<Advert> adverts = advertRepository.findByNameContainingAndCategory(name, category);
        System.out.println(adverts.toString());
        return adverts;
    }

    @RequestMapping(value = "findAdvertsByNameAndLocation")
    public List<Advert> findAdvertsByNameAndLocation(String name, String location) {
        List<Advert> adverts = advertRepository.findByLocationAndNameContaining(location, name);
        System.out.println(adverts.toString());
        return adverts;
    }

    @RequestMapping(value = "findAdvertsByLocationAndCategory")
    public List<Advert> findAdvertsByLocationAndCategory(String category, String location) {
        List<Advert> adverts = advertRepository.findByLocationAndCategory(location, category);
        System.out.println(adverts.toString());
        return adverts;
    }

    @RequestMapping(value = "findAdvertsByNameAndLocationInCategory")
    public List<Advert> findAdvertsByNameAndLocationInCategory(String name, String location, String category) {
        List<Advert> adverts = advertRepository.findByLocationAndNameContainingAndCategory(location, name, category);
        System.out.println(adverts.toString());
        return adverts;
    }

    @RequestMapping(value = "findAdvertsByPriceInCategory")
    public List<Advert> findAdvertsByPriceInCategory(float min, float max, String category) {
        List<Advert> adverts = advertRepository.findByPriceIsBetweenAndCategory(min, max, category);
        System.out.println(adverts.toString());
        return adverts;
    }

    @RequestMapping(value = "findAdvertsByPriceAndNameInCategory")
    public List<Advert> findAdvertsByPriceInCategory(float min, float max, String name, String category) {
        List<Advert> adverts = advertRepository.findByPriceIsBetweenAndNameContainingAndCategory(min, max, name, category);
        System.out.println(adverts.toString());
        return adverts;
    }

    @RequestMapping(value = "updateAdvert")
    public void updateAdvert(String id, String name, String category, String info,
                             String location, String photo, float price, long num_of_hours, String date) {

        Advert advert = advertRepository.findByAdvertId(id).get(0);
        //advertRepository.delete(advert);
        System.out.println(advert.toString());
        if (advert != null) {
            advert.setCategory(category);
            advert.setDate(date);
            advert.setInfo(info);
            advert.setLocation(location);
            advert.setName(name);
            advert.setNum_of_hours(num_of_hours);
            advert.setPhoto(photo);
            advert.setPrice(price);
            System.out.println(advert.toString());
            advertRepository.save(advert);
        }
    }*/

}
