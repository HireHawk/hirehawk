package com.hirehawk.basic_service.controllers;


import com.hirehawk.basic_service.testmongo.Advert;
import com.hirehawk.basic_service.testmongo.AdvertRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/")
public class AdvertController {

    @Autowired
    private AdvertRepository advertsRepository;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Advert createAdvert(@Valid @RequestBody Advert advert) {
        advert.setId(ObjectId.get());
        advertsRepository.save(advert);
        return advert;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Advert getAdvertById(@PathVariable("id") ObjectId id) {
        return advertsRepository.findById(id);
    }

    @RequestMapping(value = "", method = RequestMethod.PATCH)
    public Advert updateAdvertById(@Valid @RequestBody Advert advert) {
        advertsRepository.save(advert);
        return advert;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteAdvert(@PathVariable ObjectId id) {
        advertsRepository.delete(advertsRepository.findById(id));
    }
}
