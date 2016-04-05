package com.colon3.cz2006.hawkerdelivery.DAO;

import com.colon3.cz2006.hawkerdelivery.Entity.Account;

import java.util.ArrayList;

/**
 * Created by Hippo on 02/04/2016.
 */
public interface AccountDAO {

    void addAccount(Account account);
    Account getAccount(int id);
    ArrayList<Account> getAllAccounts();
    int updateAccount(Account account);
    void deleteAccount(Account account);
    int getLastId();
    ArrayList<Account> getAccountsByDomain(String domain);
}
