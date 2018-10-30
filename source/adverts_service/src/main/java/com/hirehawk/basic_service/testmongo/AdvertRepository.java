package com.hirehawk.basic_service.testmongo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

// No need implementation, just one interface, and you have CRUD, thanks Spring Data
public interface AdvertRepository extends MongoRepository<Advert, ObjectId> {

    Advert findById(ObjectId id);
    Advert findByName(String name);
    Advert findByCategory(String category);
    Advert findByInfo(String info);
    Advert findByPhoto(String photo);
    Advert findByLocation(String location);
}
