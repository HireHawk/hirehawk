package com.hirehawk.basic_service.controllers;


import com.hirehawk.basic_service.testmongo.Advert;
import com.hirehawk.basic_service.testmongo.AdvertRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/manageAdverts")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdvertController {

    @Autowired
    private AdvertRepository advertsRepository;

    @RequestMapping(value = "/")
    public String start(){
        return "<a href=\"/create\">Create Add</a> \\n" +
                "<a href=\"/logout\">Logout</a>\\";
    }

    @RequestMapping(value = "/logout")
    public void logout(HttpServletRequest request) throws ServletException {
        request.logout();

    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Advert createAdvert(@RequestBody Advert advert) {
        advertsRepository.save(advert);
        return advert;
    }


    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Advert getAdvertById(@PathVariable("id") ObjectId id) {
        return advertsRepository.findById(id);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PATCH)
    public Advert updateAdvert(@RequestBody Advert newAdvert, @PathVariable("id") ObjectId id) {
        Advert advert = advertsRepository.findById(id);
        advert.update(newAdvert.getName(), newAdvert.getCategory(), newAdvert.getInfo(), newAdvert.getPhoto(),
                newAdvert.getLocation(), newAdvert.getPrice(), newAdvert.getNumb_of_hours());
        advertsRepository.save(advert);
        return advert;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteAdvert(@PathVariable ObjectId id) {
        advertsRepository.delete(advertsRepository.findById(id));
    }
}
