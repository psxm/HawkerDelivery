package com.colon3.cz2006.hawkerdelivery.DAO;

import com.colon3.cz2006.hawkerdelivery.Entity.CustomerAccount;

import java.util.ArrayList;

/**
 * Created by Yeong How on 4/4/2016.
 */
public interface CustomerDAO {
    void addCustomerAccount(CustomerAccount customerAccount);
    CustomerAccount getCustomerAccount(int id);
    int updateCustomerAccount(CustomerAccount customerAccount);
    void deleteCustomerAccount(CustomerAccount customerAccount);
    ArrayList<CustomerAccount> getAllCustomers();
    int getLastId();
}
