package com.colon3.cz2006.hawkerdelivery.DAO;

import com.colon3.cz2006.hawkerdelivery.Entity.Account;

import java.util.ArrayList;

/**
 * Created by Hippo on 02/04/2016.
 */
public interface AccountDAO {
    ArrayList<Account> getAllAccount();
    ArrayList<Account> getAccountByDomain(String domain);


}
