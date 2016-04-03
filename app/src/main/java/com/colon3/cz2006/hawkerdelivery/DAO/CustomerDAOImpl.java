package com.colon3.cz2006.hawkerdelivery.DAO;

import com.colon3.cz2006.hawkerdelivery.Entity.Customer;

import java.util.ArrayList;

/**
 * Created by Yeong How on 4/2/2016.
 */
public class CustomerDAOImpl implements CustomerDAO {
    private ArrayList<Customer> customers = new ArrayList<>();

    public CustomerDAOImpl() {
        customers.add(new Customer(8, "Bobo", "Pioneer"));
        customers.add(new Customer(9, "Tang", "Jurong East"));
        customers.add(new Customer(10, "Ina", "City Hall"));
        // customers.add(new Customer(11, "Joker", "Jurong West"));
    }

    public Customer getCustomerById(Integer id) {
        for (Customer c: customers) {
            if (c.getId() == id)
                return c;
        }
        return null;
    }

    public ArrayList<Customer> getAllCustomers() {
        return customers;
    }
}
