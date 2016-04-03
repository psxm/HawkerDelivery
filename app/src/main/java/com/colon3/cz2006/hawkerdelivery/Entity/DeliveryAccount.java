package com.colon3.cz2006.hawkerdelivery.Entity;

/**
 * Created by Yeong How on 4/2/2016.
 */
public class DeliveryAccount extends Account {
    public DeliveryAccount(String password, String username) {
        super(password, username);
        this.setDomain("Delivery Team");
    }
}
