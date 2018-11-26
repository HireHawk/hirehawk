package com.hirehawk.search_service.module;

import com.hirehawk.search_service.models.Advert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdvertIndexServiceIml implements AdvertIndexService {

    @Resource
    private AdvertRepository repository;

    @Transactional
    @Override
    public void addToIndex(String id, String name, String category, String info,
                           String location, float price, long num_of_hours, String user, boolean rented) {
        Advert advert = new Advert(id, name, category, info, location, Float.valueOf(price), num_of_hours, user, rented);
        repository.save(advert);
    }

    @Transactional
    @Override
    public void updateIndex(String id, String name, String category, String info,
                            String location, String price, String num_of_hours, String user, String rented) {
        Advert advert = repository.findById(id).get();
        if (name != null) {
            advert.setName(name);
        }
        if (category != null) {
            advert.setCategory(category);
        }
        if (info != null) {
            advert.setInfo(info);
        }
        if (location != null) {
            advert.setLocation(location);
        }
        if (price != null) {
            advert.setPrice(Float.valueOf(price));
        }
        if (num_of_hours != null) {
            advert.setNum_of_hours(Long.valueOf(num_of_hours));
        }
        if (user != null) {
            advert.setUser_id(user);
        }
        if (rented != null) {
            advert.setRented(Boolean.valueOf(rented));
        }
        repository.save(advert);
    }

    @Transactional
    @Override
    public void updateRentStatus(String id, boolean rented) {
        Advert advert = repository.findById(id).get();
        advert.setRented(rented);
        repository.save(advert);
    }

    @Transactional
    @Override
    public void deleteFromIndex(String id) {
        repository.deleteById(id);
    }

    @Override
    public String[] search(String searchValue, String category, boolean info, String location, String minPrice,
                           String maxPrice, String num_of_hours, String user, String rented) {
        List<Advert> adverts = repository.search(searchValue, category, info, location, minPrice, maxPrice, num_of_hours, user, rented);
        String[] ids = new String[adverts.size()];
        for (int i = 0; i < adverts.size(); i++) {
            ids[i] = adverts.get(i).getAdvertId();
        }
        return ids;
    }
}
