package com.colon3.cz2006.hawkerdelivery.DAO;

import com.colon3.cz2006.hawkerdelivery.Entity.Order;

import java.util.ArrayList;

/**
 * Created by Yeong How on 3/31/2016.
 */
public interface OrderDAO {
    ArrayList<Order> getOrdersByHawkerCenter(int hawkerID);
    Order getOrderById(Integer id);
    ArrayList<Order> getAllOrders();
    ArrayList<Order> getAllOrdersByStatus(String s);
    ArrayList<Order> getProcessingOrders();
}
