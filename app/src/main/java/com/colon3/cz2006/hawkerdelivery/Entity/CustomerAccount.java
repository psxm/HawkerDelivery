package com.colon3.cz2006.hawkerdelivery.Entity;

/**
 * Created by Hippo on 02/04/2016.
 */
public class CustomerAccount extends Account {
    private int id;
    private String name;
    private String address;

    public CustomerAccount() {
        super();
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
