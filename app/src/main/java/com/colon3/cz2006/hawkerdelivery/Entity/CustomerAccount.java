package com.colon3.cz2006.hawkerdelivery.Entity;

/**
 * Created by Hippo on 02/04/2016.
 */
public class CustomerAccount extends Account {

    public CustomerAccount(String password, String username) {
        super(password, username);
        this.setDomain("Customer");
    }


}
