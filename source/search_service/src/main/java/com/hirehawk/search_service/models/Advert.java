package com.hirehawk.search_service.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(solrCoreName = "advert")
public class Advert {

    @Id
    @Indexed(name = "advertId", type = "string")
    private String advertId;

    @Indexed(name = "name", type = "string")
    private String name;

    @Indexed(name = "category", type = "string")
    private String category;

    @Indexed(name = "info", type = "string")
    private String info;

    @Indexed(name = "location", type = "string")
    private String location;

    @Indexed(name = "photo", type = "string")
    private String photo;

    @Indexed(name = "price", type = "float")
    private float price;

    @Indexed(name = "num_of_hours", type = "long")
    private long num_of_hours;

    @Indexed(name = "date", type = "string")
    private String date;

    public Advert() {}

    public Advert(String advertId, String name, String category, String info, String location, String photo, float price, long num_of_hours, String date) {
        this.advertId = advertId;
        this.name = name;
        this.category = category;
        this.info = info;
        this.location = location;
        this.photo = photo;
        this.price = price;
        this.num_of_hours = num_of_hours;
        this.date = date;
    }

    public String getAdvertId() {
        return advertId;
    }

    public void setAdvertId(String advertId) {
        this.advertId = advertId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public long getNum_of_hours() {
        return num_of_hours;
    }

    public void setNum_of_hours(long num_of_hours) {
        this.num_of_hours = num_of_hours;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Advert[" +
                "id=" + advertId +
                ", name=" + name +
                ", category=" + category +
                ", info=" + info +
                ", location=" + location +
                ", photo=" + photo +
                ", price=" + price +
                ", num_of_hours=" + num_of_hours +
                ", date=" + date +
                ']';
    }
}
