package com.hirehawk.basic_service.controllers;


import com.hirehawk.basic_service.testmongo.Category;
import com.hirehawk.basic_service.testmongo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/manageCategories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value = "/createUpdateCategory", method = RequestMethod.POST)
    public Category createCategory(@RequestBody Category category){
        Category temp;
        temp = categoryRepository.findByCategory(category.getCategory());
        if(temp == null){
            temp = new Category();
            temp.setSubcategories(new ArrayList<String>());
            temp.getSubcategories().add(category.getSubcategories().get(0));

        }
        List<String> subcategories = temp.getSubcategories();
        for(String sub: category.getSubcategories()){
            if(subcategories.contains(sub)) continue;
            else subcategories.add(sub);
        }
        temp.setSubcategories(subcategories);
        temp.setCategory(category.getCategory());
        categoryRepository.save(temp);
        return category;
    }

    @RequestMapping(value = "/getAllCategories", method = RequestMethod.GET)
    public List<Category> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }

    @RequestMapping(value = "/dropDB", method = RequestMethod.DELETE)
    public void dropDB(){
        categoryRepository.deleteAll();
    }


}
