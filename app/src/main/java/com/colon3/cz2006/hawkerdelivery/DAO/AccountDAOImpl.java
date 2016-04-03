package com.colon3.cz2006.hawkerdelivery.DAO;

import com.colon3.cz2006.hawkerdelivery.Entity.Account;
import com.colon3.cz2006.hawkerdelivery.Entity.CustomerAccount;
import com.colon3.cz2006.hawkerdelivery.Entity.VendorAccount;

import java.util.ArrayList;

/**
 * Created by Hippo on 02/04/2016.
 */
public class AccountDAOImpl implements AccountDAO {
    ArrayList<Account> accounts;
    public AccountDAOImpl(){
        accounts = new ArrayList<>();
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(3);
        list1.add(5);
        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(4);
        list2.add(6);
        accounts.add(new CustomerAccount("123","Hippo",list1));
        accounts.add(new CustomerAccount("1","1",list2));
        accounts.add(new VendorAccount("234","Yolo"));
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
