package com.hirehawk.basic_service.testmongo;

import java.util.Date;
import java.util.List;

public class DummyAdvert {


    private String id;

    private String name;
    private String category;
    private String info;
    private List<String> photo;
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
        this.info = advert.getInfo();
        this.photo = advert.getPhoto();
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

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setPhoto(List<String> photo) {
        this.photo = photo;
    }

    public List<String> getPhoto() {
        return photo;
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