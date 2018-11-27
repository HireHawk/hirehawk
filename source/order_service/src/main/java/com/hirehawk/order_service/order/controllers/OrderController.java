package com.hirehawk.order_service.order.controllers;

import com.hirehawk.order_service.order.dao.OrderDAO;
import com.hirehawk.order_service.order.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/manageOrders")
@Transactional
public class OrderController {


    @Autowired
    private OrderDAO orderDAO;

    @RequestMapping(value="/save_without_payment",method = RequestMethod.POST)
    public void save_without_payment(String advert, String userWhoGive, String userWhoGet, Date date,Date start,Date finish,float price)
    {
        orderDAO.save_order_without_payment(advert,userWhoGive,userWhoGet,date,start,finish,price);
    }

    @RequestMapping(value="/save_with_payment",method = RequestMethod.POST)
    public void save_with_payment(String advert, String userWhoGive, String userWhoGet, Date date, int payment,Date start,Date finish,float price)
    {
        orderDAO.save_order_with_payment(advert,userWhoGive,userWhoGet,date,payment,start,finish,price);
    }

    @RequestMapping(value="/update_istransfer",method = RequestMethod.POST)
    public void update_istransfer(Integer id,String advert, String userWhoGive,String userWhoGet,Date date,int payment,boolean isreturn,boolean istransfer,Date start,Date finish,float price,boolean istnew){
        orderDAO.update_istransfer(id,advert,userWhoGive,userWhoGet,date,payment,isreturn,istransfer,start,finish,price,istnew);
    }

    @RequestMapping(value="/update_isreturn",method = RequestMethod.POST)
    public void update_isreturn(Integer id,String advert, String userWhoGive,String userWhoGet,Date date,int payment,boolean isreturn,boolean istransfer,Date start,Date finish,float price,boolean isrnew){
        orderDAO.update_isreturn(id,advert,userWhoGive,userWhoGet,date,payment,isreturn,istransfer,start,finish,price,isrnew);
    }

    @RequestMapping(value = "/del",method = RequestMethod.POST)
    public void del(Integer id){
        orderDAO.del(id);
    }

    @RequestMapping(value = "/show_all_give",method = RequestMethod.GET)
    public List<Order> show_all_give(String userWhoGive){
        return orderDAO.show_all_give(userWhoGive);
    }

    @RequestMapping(value = "/show_all_get",method = RequestMethod.GET)
    public List<Order> show_all_get(String userWhoGet){
        return orderDAO.show_all_get(userWhoGet);
    }


    @RequestMapping(value="/show_n_give",method = RequestMethod.GET)
    public List<Order> show_n_give(String userWhoGive,int num){
        return orderDAO.show_n_give(userWhoGive,num);
    }

    @RequestMapping(value="/show_n_get",method = RequestMethod.GET)
    public List<Order> show_n_get(String userWhoGet,int num){
        return orderDAO.show_n_get(userWhoGet,num);
    }


    @RequestMapping(value = "/show_depend_date_give",method = RequestMethod.GET)
    public List<Order> show_depend_date_give(String userWhoGive,Date date) {
        return orderDAO.show_depend_date_give(userWhoGive,date);
    }

    @RequestMapping(value = "/show_depend_date_get",method = RequestMethod.GET)
    public List<Order> show_depend_date_get(String userWhoGet,Date date) {
        return orderDAO.show_depend_date_get(userWhoGet,date);
    }

    @RequestMapping(value="/show_not_isreturn",method = RequestMethod.GET)
    public List<Order> show_not_isreturn(String userWhoGive){
         return orderDAO.show_not_isreturn(userWhoGive);
    }

}