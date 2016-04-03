package com.colon3.cz2006.hawkerdelivery.DAO;

import com.colon3.cz2006.hawkerdelivery.Entity.Account;
import com.colon3.cz2006.hawkerdelivery.Entity.CustomerAccount;
import com.colon3.cz2006.hawkerdelivery.Entity.DeliveryAccount;
import com.colon3.cz2006.hawkerdelivery.Entity.VendorAccount;

import java.util.ArrayList;

/**
 * Created by Hippo on 02/04/2016.
 */
public class AccountDAOImpl implements AccountDAO {
    ArrayList<Account> accounts;
    public AccountDAOImpl(){
        accounts = new ArrayList<>();
        accounts.add(new CustomerAccount("123","Hippo"));
        accounts.add(new VendorAccount("234","Yolo"));
        accounts.add(new DeliveryAccount("111", "aaa"));
    }

    @Override
    public ArrayList<Account> getAccountByDomain(String domain) {
        ArrayList<Account> result = new ArrayList<>();
        for(Account acc: accounts){
            if (acc.getDomain().equals(domain))
                result.add(acc);
        }

        return result;
    }

    @Override
    public ArrayList<Account> getAllAccount() {
        return accounts;
    }
}
