package com.colon3.cz2006.hawkerdelivery.Entity;

/**
 * Created by Hippo on 02/04/2016.
 */
public abstract class Account {
    private String username;
    private String password;
    private String domain;

    public Account(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDomain() {

        return domain;
    }
}
