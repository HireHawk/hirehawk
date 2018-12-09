package com.hirehawk.basic_service.testmongo;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "differentCategories")
public class Category {

    @Id
    private ObjectId id;

    private String category;
    private List<String> subcategories;

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getId() {
        return id;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setSubcategories(List<String>subcategories) {
        this.subcategories = subcategories;
    }

    public List<String> getSubcategories() {
        return subcategories;
    }

}
