package com.hirehawk.order_service.order.dao;

import com.hirehawk.order_service.order.entities.Order;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
public class OrderDAOImpl implements OrderDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save_order_without_payment(String advert, String userWhoGive,String userWhoGet,Date date,Date start, Date finish,Float price){
        Order order = new Order();
        order.setAdvert(advert);
        order.setUserWhoGet(userWhoGet);
        order.setUserWhoGive(userWhoGive);
        order.setDate(date);
        order.setIsreturn(false);
        order.setIstransfer(false);
        order.setPayment(false);
        order.setStart(start);
        order.setFinish(finish);
        order.setPrice(price);
        Session session=this.sessionFactory.getCurrentSession();
        session.persist(order);
        session.flush();
    }

   /* @Override
    public void save_order_with_payment(String advert, String userWhoGive,String userWhoGet,Date date,int payment,Date start, Date finish,float price){
        Order order = new Order();
        order.setAdvert(advert);
        order.setUserWhoGet(userWhoGet);
        order.setUserWhoGive(userWhoGive);
        order.setDate(date);
        order.setPayment(payment);
        order.setIsreturn(false);
        order.setIstransfer(false);
        order.setStart(start);
        order.setFinish(finish);
        order.setPrice(price);
        Session session=this.sessionFactory.getCurrentSession();
        session.persist(order);
        session.flush();
    }*/

    @Override
    public void update_istransfer(Integer id,String advert, String userWhoGive,String userWhoGet,Date date,Boolean payment,Boolean isreturn,Boolean istransfer,Date start,Date finish,Float price,Boolean istnew){
        Session session=this.sessionFactory.getCurrentSession();
        Order order=new Order();
        order.setId(id);
        order.setAdvert(advert);
        order.setUserWhoGive(userWhoGive);
        order.setUserWhoGet(userWhoGet);
        order.setDate(date);
        order.setPayment(payment);
        order.setIsreturn(isreturn);
        order.setIstransfer(istransfer);
        order.setStart(start);
        order.setFinish(finish);
        order.setPrice(price);
        session.evict(order);
        order.setIstransfer(istnew);
        session.update(order);
    }

    @Override
    public void update_isreturn(Integer id,String advert, String userWhoGive,String userWhoGet,Date date,Boolean payment,Boolean isreturn,Boolean istransfer,Date start,Date finish,Float price,Boolean isrnew){
        Session session=this.sessionFactory.getCurrentSession();
        Order order=new Order();
        order.setId(id);
        order.setAdvert(advert);
        order.setUserWhoGive(userWhoGive);
        order.setUserWhoGet(userWhoGet);
        order.setDate(date);
        order.setPayment(payment);
        order.setIsreturn(isreturn);
        order.setIstransfer(istransfer);
        order.setStart(start);
        order.setFinish(finish);
        order.setPrice(price);
        session.evict(order);
        order.setIsreturn(isrnew);
        session.update(order);
    }


    @Override
    public void update_giveagree(Integer id,String advert, String userWhoGive,String userWhoGet,Date date,Boolean payment,Boolean isreturn,Boolean istransfer,Date start,Date finish,Float price,Boolean newgiveagree){
        Session session=this.sessionFactory.getCurrentSession();
        Order order=new Order();
        order.setId(id);
        order.setAdvert(advert);
        order.setUserWhoGive(userWhoGive);
        order.setUserWhoGet(userWhoGet);
        order.setDate(date);
        order.setPayment(payment);
        order.setIsreturn(isreturn);
        order.setIstransfer(istransfer);
        order.setStart(start);
        order.setFinish(finish);
        order.setPrice(price);
        session.evict(order);
        order.setIsreturn(newgiveagree);
        session.update(order);
     }

    @Override
    public void del(Integer id){
        Session session = this.sessionFactory.getCurrentSession();
        Order order = new Order();
        order.setId(id);
        session.delete(order);
    }

    @Override
    public List<Order> show_all_give(String userWhoGive){
        Session session = this.sessionFactory.getCurrentSession();
        String sql = "SELECT f FROM " + Order.class.getName() + " f WHERE f.userWhoGive =:userWhoGive";
        Query<Order> query = session.createQuery(sql, Order.class);
        query.setParameter("userWhoGive", userWhoGive);
        List<Order> res = query.getResultList();
        res.sort(new DateComparator());
        return res;
    }


    @Override
    public List<Order> show_all_get(String userWhoGet){
        Session session = this.sessionFactory.getCurrentSession();
        String sql = "SELECT f FROM " + Order.class.getName() + " f WHERE f.userWhoGet =:userWhoGet";
        Query<Order> query = session.createQuery(sql, Order.class);
        query.setParameter("userWhoGet", userWhoGet);
        List<Order> res = query.getResultList();
        res.sort(new DateComparator());
        return res;
    }

    @Override
    public List<Order> show_n_give(String userWhoGive,Integer num){
        List<Order> result = show_all_give(userWhoGive);
        result.sort(new DateComparator());
        if (num < result.size()) {
            result = result.subList(result.size() - num, result.size() - 1);
        }
        return result;
    }


    @Override
    public List<Order> show_n_get(String userWhoGet,Integer num){
        List<Order> result = show_all_get(userWhoGet);
        result.sort(new DateComparator());
        if (num < result.size()) {
            result = result.subList(result.size() - num, result.size() - 1);
        }
        return result;
    }


    @Override
    public List<Order> show_depend_date_give(String userWhoGive,Date date) {
        List<Order> result_all = show_all_give(userWhoGive);
        result_all.sort(new DateComparator());
        List<Order> result = new ArrayList<Order>();
        for (int i = 0; i < result_all.size(); i++) {
            if (result_all.get(i).getFinish().compareTo(date)>=0)
                result.add(result_all.get(i));
            else
                break;
        }
        return result;
    }


    @Override
    public List<Order> show_depend_date_get(String userWhoGet,Date date) {
        List<Order> result_all = show_all_get(userWhoGet);
        result_all.sort(new DateComparator());
        List<Order> result = new ArrayList<Order>();
        for (int i = 0; i < result_all.size(); i++) {
            if (result_all.get(i).getFinish().compareTo(date)>=0)
                result.add(result_all.get(i));
            else
                break;
        }
        return result;
    }

    @Override
    public List<Order> show_not_isreturn(String userWhoGive){
        List<Order> result_all = show_all_give(userWhoGive);
        result_all.sort(new DateComparator());
        List<Order> result = new ArrayList<Order>();
        for (int i = 0; i < result_all.size(); i++) {
            if (!result_all.get(i).getIsreturn())
                result.add(result_all.get(i));
        }
        return result;
    }

    @Override
    public float getPrices(Integer id){
        Session session = this.sessionFactory.getCurrentSession();
        String sql = "SELECT f FROM " + Order.class.getName() + " f WHERE f.id =:id";
        Query<Order> query = session.createQuery(sql, Order.class);
        query.setParameter("id", id);
        List<Order> res = query.getResultList();
        return res.get(0).getPrice();
    }


    @Override
    public String show_userWhoGet(Integer id){
        Session session = this.sessionFactory.getCurrentSession();
        String sql = "SELECT f FROM " + Order.class.getName() + " f WHERE f.id =:id";
        Query<Order> query = session.createQuery(sql, Order.class);
        query.setParameter("id", id);
        List<Order> res = query.getResultList();
        return res.get(0).getUserWhoGet();
    }

    @Override
    public Order show_order(Integer id){
        Session session = this.sessionFactory.getCurrentSession();
        String sql = "SELECT f FROM " + Order.class.getName() + " f WHERE f.id =:id";
        Query<Order> query = session.createQuery(sql, Order.class);
        query.setParameter("id", id);
        List<Order> res = query.getResultList();
        return res.get(0);
    }


    @Override
    public String show_userWhoGive(Integer id){
        Session session = this.sessionFactory.getCurrentSession();
        String sql = "SELECT f FROM " + Order.class.getName() + " f WHERE f.id =:id";
        Query<Order> query = session.createQuery(sql, Order.class);
        query.setParameter("id", id);
        List<Order> res = query.getResultList();
        return res.get(0).getUserWhoGive();
    }


    @Override
    public void update_payment(Integer id,String advert, String userWhoGive,String userWhoGet,Date date,Boolean payment,Boolean isreturn,Boolean istransfer,Date start,Date finish,Float price,Boolean new_payment){
        Session session=this.sessionFactory.getCurrentSession();
        Order order=new Order();
        order.setId(id);
        order.setAdvert(advert);
        order.setUserWhoGive(userWhoGive);
        order.setUserWhoGet(userWhoGet);
        order.setDate(date);
        order.setPayment(payment);
        order.setIsreturn(isreturn);
        order.setIstransfer(istransfer);
        order.setStart(start);
        order.setFinish(finish);
        order.setPrice(price);
        session.evict(order);
        order.setPayment(new_payment);
        session.update(order);
    }



        private class DateComparator implements Comparator<Order> {

            @Override
            public int compare(Order f1, Order f2) {
                return f1.getDate().compareTo(f2.getDate());
            }

        }
    }
