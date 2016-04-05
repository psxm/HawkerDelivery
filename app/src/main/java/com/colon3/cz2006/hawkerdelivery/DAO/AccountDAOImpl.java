package com.colon3.cz2006.hawkerdelivery.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.colon3.cz2006.hawkerdelivery.Entity.Account;

import java.util.ArrayList;

/**
 * Created by Hippo on 02/04/2016.
 */
public class AccountDAOImpl extends SQLiteOpenHelper implements AccountDAO {
/*
    public AccountDAOImpl(){
        accounts = new ArrayList<>();
<<<<<<< HEAD
        accounts.add(new Account("123","Hippo"));
=======
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
>>>>>>> 5df325eab44bc98226f3b59774aa164611683925
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
*/

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "AccountDB";

    public AccountDAOImpl(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create customer table
        String CREATE_ACCOUNT_TABLE = "CREATE TABLE accounts ( " +
                "id INTEGER PRIMARY KEY, " +
                "username TEXT, " +
                "password TEXT, " +
                "domain TEXT )";

        // create accounts table
        db.execSQL(CREATE_ACCOUNT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older accounts table if existed
        db.execSQL("DROP TABLE IF EXISTS accounts");

        // create fresh accounts table
        this.onCreate(db);
    }

    // accounts table name
    private static final String TABLE_ACCOUNTS = "accounts";

    // accounts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_DOMAIN = "domain";

    private static final String[] COLUMNS = {KEY_ID, KEY_USERNAME,
            KEY_PASSWORD, KEY_DOMAIN};

    public void addAccount(Account account){
        //for logging
        Log.d("addAccount", account.toString());

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_ID, account.getId());
        values.put(KEY_USERNAME, account.getUsername());
        values.put(KEY_PASSWORD, account.getPassword());
        values.put(KEY_DOMAIN, account.getDomain());

        // 3. insert
        db.insert(TABLE_ACCOUNTS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public Account getAccount(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_ACCOUNTS, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build account object
        Account account = new Account();
        account.setId(Integer.parseInt(cursor.getString(0)));
        account.setUsername(cursor.getString(1));
        account.setPassword(cursor.getString(2));
        account.setDomain(cursor.getString(3));

        Log.d("getAccount(" + id + ")", account.toString());

        // 5. return account
        return account;
    }

    // Get All accounts
    public ArrayList<Account> getAllAccounts() {
        ArrayList<Account> accounts = new ArrayList<>();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_ACCOUNTS;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build account and add it to list
        Account account;
        if (cursor.moveToFirst()) {
            do {
                account = new Account();
                account.setId(Integer.parseInt(cursor.getString(0)));
                account.setUsername(cursor.getString(1));
                account.setPassword(cursor.getString(2));
                account.setDomain(cursor.getString(3));

                // Add account to accounts
                accounts.add(account);
            } while (cursor.moveToNext());
        }

        Log.d("getAllAccounts()", accounts.toString());

        // return accounts
        return accounts;
    }

    // Updating single account
    public int updateAccount(Account account) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("id", account.getId());
        values.put("username", account.getUsername());
        values.put("password", account.getPassword());
        values.put("domain", account.getDomain());

        // 3. updating row
        int i = db.update(TABLE_ACCOUNTS, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(account.getId()) }); //selection args

        // 4. close
        db.close();

        return i;

    }

    // Deleting single account
    public void deleteAccount(Account account) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_ACCOUNTS,
                KEY_ID + " = ?",
                new String[]{String.valueOf(account.getId())});

        // 3. close
        db.close();

        Log.d("deleteAccount", account.toString());

    }

    public int getLastId() {
        String countQuery = "SELECT * FROM " + TABLE_ACCOUNTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    public ArrayList<Account> getAccountsByDomain(String domain) {
        ArrayList<Account> accounts = new ArrayList<>();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_ACCOUNTS + " WHERE " + KEY_DOMAIN + " = ? ";
        String[] whereArgs = new String[] {domain};

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, whereArgs);

        // 3. go over each row, build account and add it to list
        Account account;
        if (cursor.moveToFirst()) {
            do {
                account = new Account();
                account.setId(Integer.parseInt(cursor.getString(0)));
                account.setUsername(cursor.getString(1));
                account.setPassword(cursor.getString(2));
                account.setDomain(cursor.getString(3));

                // Add account to accounts
                accounts.add(account);
            } while (cursor.moveToNext());
        }

        Log.d("getAccountsByDomain()", accounts.toString());
        cursor.close();
        // return accounts
        return accounts;
    }
}
