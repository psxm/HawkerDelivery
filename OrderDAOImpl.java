package com.colon3.cz2006.hawkerdelivery.DAO;

import com.colon3.cz2006.hawkerdelivery.Entity.Customer;
import com.colon3.cz2006.hawkerdelivery.Entity.Order;

import java.util.ArrayList;

/**
 * Created by Yeong How on 3/31/2016.
 */
public class OrderDAOImpl implements OrderDAO {
    private ArrayList<Order> orders;
    public OrderDAOImpl() {
        orders = new ArrayList<>();
        orders.add(new Order(1, 1, 1, 3, 5.6));
        orders.add(new Order(2, 2, 1, 10, 29.3));
        orders.add(new Order(3, 3,2,7,8.9));
        orders.add(new Order(4, 4,6,1,2.9));
        orders.add(new Order(5, 5,3,2,5.5));
        orders.add(new Order(6, 6,5,2,3.9));
        orders.add(new Order(7, 7,4,4,12.0));
        Order o = new Order(8, 8, 2, 3, 21.5);
        o.setStatus("No");
        orders.add(o);
        o = new Order(9, 9, 5, 3, 12.5);
        o.setStatus("No");
        orders.add(o);
        o = new Order(10, 10, 3, 2, 5.8);
        o.setStatus("No");
        orders.add(o);
    }

    public Order getOrderById(Integer id) {
        for (Order o: orders) {
            if (o.getOrderID() == id)
                return o;
        }
        return null;
    }

    @Override
    public ArrayList<Order> getOrdersByHawkerCenter(int hawkerID) {
        ArrayList<Order> filtered = new ArrayList<>();
        for (Order o: orders) {
            if (o.getHawkerID() == hawkerID)
                filtered.add(o);
        }
        return filtered;
    }

    public ArrayList<Order> getAllOrders() {
        return orders;
    }

    public ArrayList<Order> getAllOrdersByStatus(String s) {
        ArrayList<Order> filtered = new ArrayList<>();
        for (Order o: orders) {
            if (o.getStatus() == s)
                filtered.add(o);
        }
        return filtered;
    }

    public ArrayList<Order> getProcessingOrders() {
        ArrayList<Order> filtered = new ArrayList<>();
        for (Order o: orders) {
            if (o.getStatus() == "No")
                filtered.add(o);
        }
        return filtered;
    }

    public String getAddressFromOrder(Order order) {
        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        Customer customer = customerDAO.getCustomerById(order.getCustomerID());
        return customer.getAddress();
    }
}
