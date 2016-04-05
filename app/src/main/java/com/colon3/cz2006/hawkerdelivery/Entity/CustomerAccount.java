package com.colon3.cz2006.hawkerdelivery.Entity;

import java.util.ArrayList;

/**
 * Created by Hippo on 02/04/2016.
 */
public class CustomerAccount extends Account {

    private int id;
    private String name;
    private String address;
    private ArrayList<Integer> favourites;

    public CustomerAccount() {
        super();
    }

    public ArrayList<Integer> getFavourites() {
        return favourites;
    }

    public CustomerAccount(String password, String username,ArrayList<Integer> favourites) {
        super(password, username);
        this.setDomain("Customer");
        this.favourites = favourites;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
