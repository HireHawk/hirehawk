package com.hirehawk.search_service.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;


@SolrDocument(solrCoreName = "adverts")
public class Advert {

    @Id
    @Indexed(name = "id", type = "string")
    private String advertId;

    @Indexed(name = "name", type = "text_general")
    private String name;

    @Indexed(name = "category", type = "string")
    private String category;

    @Indexed(name = "info", type = "text_general")
    private String info;

    @Indexed(name = "location", type = "text_general")
    private String location;

    @Indexed(name = "price", type = "float")
    private float price;

    @Indexed(name = "num_of_hours", type = "long")
    private long num_of_hours;

    @Indexed(name = "user_id", type = "string")
    private String user_id;


    public Advert() {}

    public Advert(String advertId, String name, String category, String info, String location, float price,
                  long num_of_hours, String user_id) {
        this.advertId = advertId;
        this.name = name;
        this.category = category;
        this.info = info;
        this.location = location;
        this.price = price;
        this.num_of_hours = num_of_hours;
        this.user_id = user_id;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Advert[" +
                "id=" + advertId +
                ", name=" + name +
                ", category=" + category +
                ", info=" + info +
                ", location=" + location +
                ", price=" + price +
                ", num_of_hours=" + num_of_hours +
                ", user_id=" + user_id +
                ']';
    }
}
