package com.colon3.cz2006.hawkerdelivery.Controller;

import com.colon3.cz2006.hawkerdelivery.DAO.AccountDAO;
import com.colon3.cz2006.hawkerdelivery.DAO.AccountDAOImpl;
import com.colon3.cz2006.hawkerdelivery.Entity.Account;

import java.util.ArrayList;

/**
 * Created by Hippo on 02/04/2016.
 */
public class AccountController {
    AccountDAO accDAO = new AccountDAOImpl();
    public AccountController(){

    }
    public boolean isAuthenticated(String username,String password,String domain){
        ArrayList<Account> accountArrayList = accDAO.getAccountByDomain(domain);
        for(Account acc:accountArrayList){
            if(acc.getUsername().equals(username)&&acc.getPassword().equals(password))
                return true;
        }
        return false;
    }
}
