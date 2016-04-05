package com.colon3.cz2006.hawkerdelivery.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.colon3.cz2006.hawkerdelivery.Entity.CustomerAccount;

import java.util.ArrayList;

/**
 * Created by Yeong How on 4/4/2016.
 */
public class CustomerDAOImpl extends SQLiteOpenHelper implements CustomerDAO {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "CustomerDB";

    public CustomerDAOImpl(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create customer table
        String CREATE_CUSTOMER_TABLE = "CREATE TABLE customers ( " +
                "id INTEGER PRIMARY KEY, " +
                "username TEXT, " +
                "password TEXT, " +
                "name TEXT, " +
                "address TEXT )";

        // create customers table
        db.execSQL(CREATE_CUSTOMER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older customers table if existed
        db.execSQL("DROP TABLE IF EXISTS customers");

        // create fresh customers table
        this.onCreate(db);
    }

    // customers table name
    private static final String TABLE_customers = "customers";

    // customers Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_NAME = "name";
    private static final String KEY_ADDRESS = "address";

    private static final String[] COLUMNS = {KEY_ID, KEY_USERNAME,
            KEY_PASSWORD, KEY_NAME, KEY_ADDRESS};

    public void addCustomerAccount(CustomerAccount customerAccount){
        //for logging
        Log.d("addCustomerAccount", customerAccount.toString());

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_ID, customerAccount.getId());
        values.put(KEY_USERNAME, customerAccount.getUsername());
        values.put(KEY_PASSWORD, customerAccount.getPassword());
        values.put(KEY_NAME, customerAccount.getName());
        values.put(KEY_ADDRESS, customerAccount.getAddress());

        // 3. insert
        db.insert(TABLE_customers, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public CustomerAccount getCustomerAccount(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_customers, // a. table
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

        // 4. build customerAccount object
        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setId(Integer.parseInt(cursor.getString(0)));
        customerAccount.setUsername(cursor.getString(1));
        customerAccount.setPassword(cursor.getString(2));
        customerAccount.setName(cursor.getString(3));
        customerAccount.setAddress(cursor.getString(4));

        Log.d("getCustomerAccount(" + id + ")", customerAccount.toString());

        // 5. return customerAccount
        return customerAccount;
    }

    // Get All customers
    public ArrayList<CustomerAccount> getAllCustomers() {
        ArrayList<CustomerAccount> customers = new ArrayList<>();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_customers;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build customerAccount and add it to list
        CustomerAccount customerAccount;
        if (cursor.moveToFirst()) {
            do {
                customerAccount = new CustomerAccount();
                customerAccount.setId(Integer.parseInt(cursor.getString(0)));
                customerAccount.setUsername(cursor.getString(1));
                customerAccount.setPassword(cursor.getString(2));
                customerAccount.setName(cursor.getString(3));
                customerAccount.setAddress(cursor.getString(4));

                // Add customerAccount to customers
                customers.add(customerAccount);
            } while (cursor.moveToNext());
        }

        Log.d("getAllCustomers()", customers.toString());

        // return customers
        return customers;
    }

    // Updating single customerAccount
    public int updateCustomerAccount(CustomerAccount customerAccount) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("id", customerAccount.getId());
        values.put("username", customerAccount.getUsername());
        values.put("password", customerAccount.getPassword());
        values.put("name", customerAccount.getName());
        values.put("address", customerAccount.getAddress());

        // 3. updating row
        int i = db.update(TABLE_customers, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(customerAccount.getId()) }); //selection args

        // 4. close
        db.close();

        return i;

    }

    // Deleting single customerAccount
    public void deleteCustomerAccount(CustomerAccount customerAccount) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_customers,
                KEY_ID + " = ?",
                new String[]{String.valueOf(customerAccount.getId())});

        // 3. close
        db.close();

        Log.d("deleteCustomerAccount", customerAccount.toString());

    }

    public int getLastId() {
        String countQuery = "SELECT * FROM " + TABLE_customers;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }
}
