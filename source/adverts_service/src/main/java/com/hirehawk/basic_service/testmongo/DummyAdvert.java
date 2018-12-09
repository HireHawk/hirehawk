package com.hirehawk.basic_service.testmongo;

import java.util.Date;
import java.util.List;

public class DummyAdvert {


    private String id;

    private String name;
    private String category;
    private String subcategory;
    private String info;
    private List<String> imageLinks;
    private String mainLink;
    private String location;
    private Float price;
    private long numb_of_hours;
    private String usersId;
    private Date date;

    public DummyAdvert() {}

    public DummyAdvert(Advert advert) {
        this.id = advert.getId().toString();
        this.name = advert.getName();
        this.category = advert.getCategory();
        this.subcategory = advert.getSubcategory();
        this.info = advert.getInfo();
        this.imageLinks = advert.getImageLinks();
        this.mainLink =advert.getMainLink();
        this.location = advert.getLocation();
        this.price = advert.getPrice();
        this.numb_of_hours = advert.getNumb_of_hours();
        this.date = advert.getDate();
        this.usersId = advert.getUsersId();
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setImageLinks(List<String> photo) {
        this.imageLinks = photo;
    }

    public List<String> getImageLinks() {
        return imageLinks;
    }

    public void setMainLink(String photo) {
        this.mainLink = photo;
    }

    public String getMainLink() {
        return mainLink;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Float getPrice() {
        return price;
    }

    public void setNumb_of_hours(long numb_of_hours) {
        this.numb_of_hours = numb_of_hours;
    }

    public long getNumb_of_hours() {
        return numb_of_hours;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public String getUsersId() {
        return usersId;
    }

    public void setUsersId(String usersId) {
        this.usersId = usersId;
    }
}