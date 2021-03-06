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
    private String info;
    private List<String> photo;
    private String location;
    private Float price;
    private long numb_of_hours;
    private String usersId;
    private Date date;

    public Advert() {}

    public Advert(ObjectId _id, String name, String category, String info, List<String> photo,
                  String location, Float price, long numb_of_hours) {
        this.id = _id;
        this.name = name;
        this.category = category;
        this.info = info;
        this.photo = photo;
        this.location = location;
        this.price = price;
        this.numb_of_hours = numb_of_hours;
    }

    public void update(String name, String category, String info, List<String> photo,
                  String location, Float price, long numb_of_hours){
        setName(name);
        setCategory(category);
        setInfo(info);
        setPhoto(photo);
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