package com.hirehawk.basic_service.testmongo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;



// No need implementation, just one interface, and you have CRUD, thanks Spring Data
public interface CategoryRepository extends MongoRepository<Category, ObjectId> {

    Category findById(ObjectId id);
    Category findByCategory(String category);

}
