package com.colon3.cz2006.hawkerdelivery.DAO;

import com.colon3.cz2006.hawkerdelivery.Entity.Order;

import java.util.ArrayList;

/**
 * Created by Yeong How on 3/31/2016.
 */
public interface OrderDAO {
    void addOrder(Order order);
    Order getOrder(int id);
    int updateOrder(Order order);
    void deleteOrder(Order order);
    ArrayList<Order> getAllOrders();
    ArrayList<Order> getAllOrdersByStatus(String s);
    int getLastId();
}
