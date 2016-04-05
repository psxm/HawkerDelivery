package com.colon3.cz2006.hawkerdelivery.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.colon3.cz2006.hawkerdelivery.Boundary.ListProcessingOrderActivity;
import com.colon3.cz2006.hawkerdelivery.Entity.CustomerAccount;
import com.colon3.cz2006.hawkerdelivery.Entity.Dish;
import com.colon3.cz2006.hawkerdelivery.Entity.Order;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Yeong How on 3/31/2016.
 */
/*
public class OrderDAOImpl implements OrderDAO {
    private ArrayList<Order> orders;
    public OrderDAOImpl() {
        orders = new ArrayList<>();
        orders.add(new Order(1, 1, 1, 3, 5.6));
        orders.add(new Order(2, 2, 1, 10, 29.3));
        orders.add(new Order(3, 3,2,7,8.9));
        orders.add(new Order(4, 4,6,1,2.9));
        orders.add(new Order(5, 5,3,2,5.5));
        orders.add(new Order(6, 6,5,2,3.9));
        orders.add(new Order(7, 7,4,4,12.0));
        Order o = new Order(8, 8, 2, 3, 21.5);
        o.setStatus("No");
        orders.add(o);
        o = new Order(9, 9, 5, 3, 12.5);
        o.setStatus("No");
        orders.add(o);
        o = new Order(10, 10, 3, 2, 5.8);
        o.setStatus("No");
        orders.add(o);
    }

    public Order getOrderById(Integer id) {
        for (Order o: orders) {
            if (o.getOrderID() == id)
                return o;
        }
        return null;
    }

    @Override
    public ArrayList<Order> getOrdersByHawkerCenter(int hawkerID) {
        ArrayList<Order> filtered = new ArrayList<>();
        for (Order o: orders) {
            if (o.getHawkerID() == hawkerID)
                filtered.add(o);
        }
        return filtered;
    }

    public ArrayList<Order> getAllOrders() {
        return orders;
    }

    public ArrayList<Order> getAllOrdersByStatus(String s) {
        ArrayList<Order> filtered = new ArrayList<>();
        for (Order o: orders) {
            if (o.getStatus() == s)
                filtered.add(o);
        }
        return filtered;
    }

    public ArrayList<Order> getProcessingOrders() {
        ArrayList<Order> filtered = new ArrayList<>();
        for (Order o: orders) {
            if (o.getStatus() == "No")
                filtered.add(o);
        }
        return filtered;
    }

    public String getAddressFromOrder(Order order) {
        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        Customer customer = customerDAO.getCustomerById(order.getCustomerID());
        return customer.getAddress();
    }
} */

/**
 * Created by Yeong How on 4/1/2016.
 */
public class OrderDAOImpl extends SQLiteOpenHelper implements OrderDAO {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "OrderDB";

    public OrderDAOImpl(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create order table
        String CREATE_ORDER_TABLE = "CREATE TABLE orders ( " +
                "id INTEGER PRIMARY KEY, " +
                "customerId INTEGER, " +
                "hawkerId INTEGER, " +
                "dishes TEXT, " +
                "totalPrice DOUBLE, " +
                "status TEXT, " +
                "deliveryId INTEGER, " +
                "dateTime DATE )";

        // create orders table
        db.execSQL(CREATE_ORDER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older orders table if existed
        db.execSQL("DROP TABLE IF EXISTS orders");

        // create fresh orders table
        this.onCreate(db);
    }

    // Orders table name
    private static final String TABLE_ORDERS = "orders";

    // Orders Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_CUSTOMERID = "customerId";
    private static final String KEY_HAWKERID = "hawkerId";
    private static final String KEY_DISHES = "dishes";
    private static final String KEY_TOTALPRICE = "totalPrice";
    private static final String KEY_STATUS = "status";
    private static final String KEY_DATETIME = "dateTime";
    private static final String KEY_DELIVERYID = "deliveryId";

    private static final String[] COLUMNS = {KEY_ID, KEY_CUSTOMERID,
            KEY_HAWKERID, KEY_STATUS, KEY_DISHES, KEY_DELIVERYID, KEY_DATETIME, KEY_TOTALPRICE};

    public void addOrder(Order order){
        //for logging
        Log.d("addOrder", order.toString());

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_ID, order.getOrderID());
        values.put(KEY_CUSTOMERID, order.getCustomerID());
        values.put(KEY_HAWKERID, order.getHawkerID());
        values.put(KEY_STATUS, order.getStatus());
        ArrayList<Dish> dishes = order.getDishes();
        Gson gson = new Gson();
        String dishes_SQL = gson.toJson(dishes);
        values.put(KEY_DISHES, dishes_SQL);
        values.put(KEY_DATETIME, order.getDateTime().toString());
        values.put(KEY_TOTALPRICE, order.getTotalPrice());
        values.put(KEY_DELIVERYID, order.getDeliveryId());

        // 3. insert
        db.insert(TABLE_ORDERS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public Order getOrder(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_ORDERS, // a. table
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

        // 4. build order object
        Order order = new Order();
        order.setOrderID(Integer.parseInt(cursor.getString(0)));
        order.setCustomerID(Integer.parseInt(cursor.getString(1)));
        order.setHawkerID(Integer.parseInt(cursor.getString(2)));
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        Gson gson = new Gson();
        ArrayList<Dish> finalOutputString = gson.fromJson(cursor.getString(3), type);
        order.setDishes(finalOutputString);
        order.setTotalPrice(Double.parseDouble(cursor.getString(4)));
        order.setStatus(cursor.getString(5));
        order.setDeliveryId(Integer.parseInt(cursor.getString(6)));
        order.setDateTime(new Date(Date.parse(cursor.getString(7))));

        Log.d("getOrder(" + id + ")", order.toString());

        // 5. return order
        return order;
    }

    // Get All Orders
    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> orders = new ArrayList<>();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_ORDERS;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build order and add it to list
        Order order;
        if (cursor.moveToFirst()) {
            do {
                order = new Order();
                order.setOrderID(Integer.parseInt(cursor.getString(0)));
                order.setCustomerID(Integer.parseInt(cursor.getString(1)));
                order.setHawkerID(Integer.parseInt(cursor.getString(2)));
                Type type = new TypeToken<ArrayList<Dish>>() {}.getType();
                Gson gson = new Gson();
                ArrayList<Dish> finalOutputString = gson.fromJson(cursor.getString(3), type);
                order.setDishes(finalOutputString);
                order.setTotalPrice(Double.parseDouble(cursor.getString(4)));
                order.setStatus(cursor.getString(5));
                order.setDeliveryId(Integer.parseInt(cursor.getString(6)));
                order.setDateTime(new Date(Date.parse(cursor.getString(7))));

                // Add order to orders
                orders.add(order);
            } while (cursor.moveToNext());
        }

        Log.d("getAllOrders()", orders.toString());

        // return orders
        return orders;
    }

    // Updating single order
    public int updateOrder(Order order) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("id", order.getOrderID());
        values.put("customerId", order.getCustomerID());
        values.put("hawkerId", order.getHawkerID());
        values.put("status", order.getStatus());
        ArrayList<Dish> dishes = order.getDishes();
        Gson gson = new Gson();
        String dishes_SQL = gson.toJson(dishes);
        values.put("dishes", dishes_SQL);
        values.put("dateTime", order.getDateTime().toString());
        values.put("deliveryId", order.getDeliveryId());
        values.put("totalPrice", order.getTotalPrice());


        // 3. updating row
        int i = db.update(TABLE_ORDERS, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(order.getOrderID()) }); //selection args

        // 4. close
        db.close();

        return i;

    }

    // Deleting single order
    public void deleteOrder(Order order) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_ORDERS,
                KEY_ID + " = ?",
                new String[]{String.valueOf(order.getOrderID())});

        // 3. close
        db.close();

        Log.d("deleteOrder", order.toString());

    }

    public ArrayList<Order> getAllOrdersByStatus(String status) {
        ArrayList<Order> orders = new ArrayList<>();
        // 1. build the query
        String query = "SELECT * FROM " + TABLE_ORDERS + " WHERE " + KEY_STATUS + " = ? ";
        String[] whereArgs = new String[] {status};

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, whereArgs);

        // 3. go over each row, build order and add it to list
        Order order;
        if (cursor.moveToFirst()) {
            do {
                order = new Order();
                order.setOrderID(Integer.parseInt(cursor.getString(0)));
                order.setCustomerID(Integer.parseInt(cursor.getString(1)));
                order.setHawkerID(Integer.parseInt(cursor.getString(2)));
                Type type = new TypeToken<ArrayList<Dish>>() {}.getType();
                Gson gson = new Gson();
                ArrayList<Dish> finalOutputString = gson.fromJson(cursor.getString(3), type);
                order.setDishes(finalOutputString);
                order.setTotalPrice(Double.parseDouble(cursor.getString(4)));
                order.setStatus(cursor.getString(5));
                order.setDeliveryId(Integer.parseInt(cursor.getString(6)));
                order.setDateTime(new Date(Date.parse(cursor.getString(7))));

                // Add order to orders
                orders.add(order);
            } while (cursor.moveToNext());
        }

        Log.d("getAllOrdersByStatus()", orders.toString());

        // return orders
        return orders;
    }

    public int getLastId() {
        String countQuery = "SELECT * FROM " + TABLE_ORDERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }
}



