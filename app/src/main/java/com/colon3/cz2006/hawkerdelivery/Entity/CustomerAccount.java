package com.colon3.cz2006.hawkerdelivery.Entity;

import java.util.ArrayList;

/**
 * Created by Hippo on 02/04/2016.
 */
public class CustomerAccount extends Account {
    private ArrayList<Integer> favourites;

    public ArrayList<Integer> getFavourites() {
        return favourites;
    }

    public CustomerAccount(String password, String username,ArrayList<Integer> favourites) {
        super(password, username);
        this.setDomain("Customer");
        this.favourites = favourites;
    }


}
