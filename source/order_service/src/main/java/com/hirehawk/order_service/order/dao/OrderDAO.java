package com.hirehawk.order_service.order.dao;

import com.hirehawk.order_service.order.entities.Order;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;


public interface OrderDAO {

    //public void save_order(String advert, String userWhoGive,String userWhoGet,Date date,int payment);
    public void save_order_without_payment(String advert, String userWhoGive,String userWhoGet,Date date,Date start, Date finish,Float price);
    //public void save_order_with_payment(String advert, String userWhoGive,String userWhoGet,Date date,int payment,Date start, Date finish,float price);
   public void update_istransfer(Integer id,String advert, String userWhoGive,String userWhoGet,Date date,Boolean payment,Boolean isreturn,Boolean istransfer,Date start,Date finish,Float price,Boolean istnew);
   public void  update_isreturn(Integer id,String advert, String userWhoGive,String userWhoGet,Date date,Boolean payment,Boolean isreturn,Boolean istransfer,Date start,Date finish,Float price,Boolean isrnew);
   public void del(Integer id);
   public List<Order> show_all_give(String userWhoGive);
    public List<Order> show_all_get(String userWhoGet);
    public List<Order> show_n_give(String userWhoGive,Integer num);
    public List<Order> show_n_get(String userWhoGet,Integer num);
   public List<Order> show_depend_date_give(String userWhoGive,Date date);
    public List<Order> show_depend_date_get(String userWhoGet,Date date);
     public List<Order> show_not_isreturn(String userWhoGive);
    //public List<Order> show_on_istransfer(String userWhoGet);
    public float getPrices(Integer id);
    public void update_payment(Integer id,String advert, String userWhoGive,String userWhoGet,Date date,Boolean payment,Boolean isreturn,Boolean istransfer,Date start,Date finish,Float price,Boolean new_payment);
    public Order show_order(Integer id);
    public String show_userWhoGet(Integer id);
    public String show_userWhoGive(Integer id);
    public void update_giveagree(Integer id,String advert, String userWhoGive,String userWhoGet,Date date,Boolean payment,Boolean isreturn,Boolean istransfer,Date start,Date finish,Float price,Boolean newgiveagree);
}