package com.colon3.cz2006.hawkerdelivery.Entity;

/**
 * Created by Yeong How on 4/2/2016.
 */
public class DeliveryAccount extends Account {
    private int id;
    private String name;
    private String sex;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
