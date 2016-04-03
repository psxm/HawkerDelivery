package com.colon3.cz2006.hawkerdelivery.Entity;

/**
 * Created by Yeong How on 4/2/2016.
 */
public class Customer {
    private Integer id;
    private String name;
    private String address;

    public Customer(Integer id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
