package com.colon3.cz2006.hawkerdelivery.DAO;

import com.colon3.cz2006.hawkerdelivery.Entity.Customer;

import java.util.ArrayList;

/**
 * Created by Yeong How on 4/2/2016.
 */
public interface CustomerDAO {
    Customer getCustomerById(Integer id);
    ArrayList<Customer> getAllCustomers();
}
