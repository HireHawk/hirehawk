package com.hirehawk.order_service.order.entities;



import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "[order]", schema = "[orders]")
@DynamicUpdate
public class Order implements Serializable{

    private static final long serialVersionUID = -2054386655979281969L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name="advert",length = 20, nullable = false)
    private String advert;

    @Column(name="userWhoGive",length = 20,nullable = false)
    private String userWhoGive;

    @Column(name="userWhoGet",length = 20,nullable = false)
    private  String userWhoGet;

    @Column(name = "date", columnDefinition="DATETIME",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name="payment")
    private Boolean payment;


    @Column(name="istransfer")
    private Boolean istransfer;

    @Column(name="isreturn")
    private Boolean isreturn;

    @Column(name="start",nullable = false)
    private Date start;

    @Column(name="finish",nullable = false)
    private Date finish;

    @Column(name="price",nullable = false)
    private Float price;

    @Column(name = "giveagree")
    private Boolean giveagree;


    public void setId(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getAdvert() {
        return advert;
    }

    public void setAdvert(String advert) {
        this.advert = advert;
    }

    public String getUserWhoGive() {
        return userWhoGive;
    }

    public void setUserWhoGive(String userWhoGive) {
        this.userWhoGive = userWhoGive;
    }

    public String  getUserWhoGet() {
        return userWhoGet;
    }

    public void setUserWhoGet(String  userWhoGet) {
        this.userWhoGet = userWhoGet;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getPayment(){return payment;}
    public void setPayment(Boolean payment){this.payment=payment;}


    public Boolean getIstransfer(){return istransfer;}
    public void setIstransfer(Boolean istransfer) {this.istransfer=istransfer;}

    public Boolean getIsreturn(){return isreturn;}
    public void setIsreturn(Boolean isreturn){this.isreturn=isreturn;}

    public Date getStart(){return start;}
    public void setStart(Date start){this.start=start;}

    public Date getFinish(){return finish;}
    public void setFinish(Date finish){this.finish=finish;}

    public Float getPrice() {
        return price;
    }
    public void setPrice(Float price){this.price=price;}

    public Boolean getGiveagree(){return giveagree;}
    public void setGiveagree(Boolean giveagree){this.giveagree=giveagree;}
}