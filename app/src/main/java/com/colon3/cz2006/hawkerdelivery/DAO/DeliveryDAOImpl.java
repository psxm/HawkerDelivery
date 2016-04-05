package com.colon3.cz2006.hawkerdelivery.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.colon3.cz2006.hawkerdelivery.Entity.DeliveryAccount;

import java.util.ArrayList;

/**
 * Created by Yeong How on 4/5/2016.
 */
public class DeliveryDAOImpl extends SQLiteOpenHelper implements DeliveryDAO {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "DeliveryAccountDB";

    public DeliveryDAOImpl(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create customer table
        String CREATE_DELIVERY_TABLE = "CREATE TABLE deliveries ( " +
                "id INTEGER PRIMARY KEY, " +
                "username TEXT, " +
                "password TEXT, " +
                "name TEXT, " +
                "sex TEXT )";

        // create deliveries table
        db.execSQL(CREATE_DELIVERY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older deliveries table if existed
        db.execSQL("DROP TABLE IF EXISTS deliveries");

        // create fresh deliveries table
        this.onCreate(db);
    }

    // deliveries table name
    private static final String TABLE_DELIVERIES = "deliveries";

    // deliveries Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_NAME = "name";
    private static final String KEY_SEX = "sex";

    private static final String[] COLUMNS = {KEY_ID, KEY_USERNAME,
            KEY_PASSWORD, KEY_NAME, KEY_SEX};

    public void addDeliveryAccount(DeliveryAccount delivery){
        //for logging
        Log.d("addDeliveryAccount", delivery.toString());

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_ID, delivery.getId());
        values.put(KEY_USERNAME, delivery.getUsername());
        values.put(KEY_PASSWORD, delivery.getPassword());
        values.put(KEY_NAME, delivery.getName());
        values.put(KEY_SEX, delivery.getSex());

        // 3. insert
        db.insert(TABLE_DELIVERIES, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public DeliveryAccount getDeliveryAccount(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_DELIVERIES, // a. table
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

        // 4. build delivery object
        DeliveryAccount delivery = new DeliveryAccount();
        delivery.setId(Integer.parseInt(cursor.getString(0)));
        delivery.setUsername(cursor.getString(1));
        delivery.setPassword(cursor.getString(2));
        delivery.setName(cursor.getString(3));
        delivery.setSex(cursor.getString(4));

        Log.d("getDeliveryAccount(" + id + ")", delivery.toString());

        // 5. return delivery
        return delivery;
    }

    // Get All deliveries
    public ArrayList<DeliveryAccount> getAllDeliveryAccounts() {
        ArrayList<DeliveryAccount> deliveries = new ArrayList<>();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_DELIVERIES;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build delivery and add it to list
        DeliveryAccount delivery;
        if (cursor.moveToFirst()) {
            do {
                delivery = new DeliveryAccount();
                delivery.setId(Integer.parseInt(cursor.getString(0)));
                delivery.setUsername(cursor.getString(1));
                delivery.setPassword(cursor.getString(2));
                delivery.setName(cursor.getString(3));
                delivery.setSex(cursor.getString(4));

                // Add delivery to deliveries
                deliveries.add(delivery);
            } while (cursor.moveToNext());
        }

        Log.d("getAllDeliveryAccounts()", deliveries.toString());

        // return deliveries
        return deliveries;
    }

    // Updating single delivery
    public int updateDeliveryAccount(DeliveryAccount delivery) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("id", delivery.getId());
        values.put("username", delivery.getUsername());
        values.put("password", delivery.getPassword());
        values.put("name", delivery.getName());
        values.put("sex", delivery.getSex());

        // 3. updating row
        int i = db.update(TABLE_DELIVERIES, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(delivery.getId()) }); //selection args

        // 4. close
        db.close();

        return i;

    }

    // Deleting single delivery
    public void deleteDeliveryAccount(DeliveryAccount delivery) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_DELIVERIES,
                KEY_ID + " = ?",
                new String[]{String.valueOf(delivery.getId())});

        // 3. close
        db.close();

        Log.d("deleteDeliveryAccount", delivery.toString());

    }

    public int getLastId() {
        String countQuery = "SELECT * FROM " + TABLE_DELIVERIES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }
}
