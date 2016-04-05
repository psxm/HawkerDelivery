package com.colon3.cz2006.hawkerdelivery.Controller;

import android.content.Context;

import com.colon3.cz2006.hawkerdelivery.DAO.AccountDAOImpl;
import com.colon3.cz2006.hawkerdelivery.Entity.Account;

import java.util.ArrayList;

/**
 * Created by Hippo on 02/04/2016.
 */
public class AccountController {
    private AccountDAOImpl accDAO;

    public AccountController(Context context){
        this.accDAO = new AccountDAOImpl(context);
    }

    public boolean isAuthenticated(String username,String password,String domain){
        ArrayList<Account> accountArrayList = accDAO.getAccountsByDomain(domain);
        for(Account acc: accountArrayList){
            if(acc.getUsername().equals(username)) {
                if (acc.getPassword().equals(password))
                    return true;
            }
        }
        return false;
    }
}
