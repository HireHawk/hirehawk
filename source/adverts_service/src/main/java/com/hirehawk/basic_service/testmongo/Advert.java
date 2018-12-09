package com.hirehawk.basic_service.testmongo;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "differentAdds")
public class Advert {

    @Id
    private ObjectId id;

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

    public Advert() {
    }

    public Advert(ObjectId _id, String name, String category, String subcategory, String info, List<String> photo,
                  String mainPhoto, String location, Float price, long numb_of_hours) {
        this.id = _id;
        this.name = name;
        this.category = category;
        this.subcategory = subcategory;
        this.info = info;
        this.imageLinks = photo;
        this.mainLink = mainPhoto;
        this.location = location;
        this.price = price;
        this.numb_of_hours = numb_of_hours;
    }

    public void update(String name, String category, String subcategory, String info, List<String> photo, String mainPhoto,
                       String location, Float price, long numb_of_hours) {
        setName(name);
        setCategory(category);
        setSubcategory(subcategory);
        setInfo(info);
        setImageLinks(photo);
        setMainLink(mainPhoto);
        setLocation(location);
        setPrice(price);
        setNumb_of_hours(numb_of_hours);

    }


    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getId() {
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