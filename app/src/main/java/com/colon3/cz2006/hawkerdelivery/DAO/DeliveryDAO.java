package com.colon3.cz2006.hawkerdelivery.DAO;

import com.colon3.cz2006.hawkerdelivery.Entity.DeliveryAccount;

import java.util.ArrayList;

/**
 * Created by Yeong How on 4/5/2016.
 */
public interface DeliveryDAO {
    void addDeliveryAccount(DeliveryAccount deliveryAccount);
    DeliveryAccount getDeliveryAccount(int id);
    int updateDeliveryAccount(DeliveryAccount deliveryAccount);
    void deleteDeliveryAccount(DeliveryAccount deliveryAccount);
    ArrayList<DeliveryAccount> getAllDeliveryAccounts();
    int getLastId();
}
