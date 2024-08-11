
package com.example.my_health;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Database extends SQLiteOpenHelper {
    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "create table users(username text ,email text,password text,phone text ) ";
        db.execSQL(query1);

        String query2 = "create table cart(username text ,product text,price float,type text ) ";
        db.execSQL(query2);

        String query3 = "create table orders(id integer primary key autoincrement, cstmr_name text, c_phone text, address text, trnx_id text, product_name text, final_total_amount text) ";
        db.execSQL(query3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public long register(String username, String email, String phone, String password) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("phone", phone);
        cv.put("password", password);
        SQLiteDatabase db = getWritableDatabase();
        return db.insert("users", null, cv);
    }

    public int login(String username, String password) {
        int match = 0;
        String[] str = new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cr = db.rawQuery("select * from users where username = ? and password = ?", str);
        if (cr.moveToFirst()) {
            match = 1;
        }
        cr.close();
        return match;
    }

    public int removeCart(String username) {
        String[] str = new String[1];
        str[0] = username;

        SQLiteDatabase db = getWritableDatabase();
        return db.delete("cart", "username = ?", str);
    }

    public void addingToCart(String username, String product, float price, String type) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("product", product);
        cv.put("price", price);
        cv.put("type", type);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("cart", null, cv);
    }

//    public ArrayList<HashMap<String, String>> getAllOrders() {
//        ArrayList<HashMap<String, String>> orderList = new ArrayList<>();
//        String selectQuery = "SELECT * FROM orders";
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                HashMap<String, String> order = new HashMap<>();
//                order.put("id", cursor.getString(0));
//                order.put("cstmr_name", cursor.getString(1));
//                order.put("c_phone", cursor.getString(2));
//                order.put("address", cursor.getString(3));
//                order.put("trnx_id", cursor.getString(4));
//                order.put("product_name", cursor.getString(5));
//                order.put("final_total_amount", cursor.getString(6));
//                orderList.add(order);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return orderList;
//    }
// Database.java
@SuppressLint("Range")
public ArrayList<HashMap<String, String>> getAllOrders() {
    ArrayList<HashMap<String, String>> orderList = new ArrayList<>();
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery("SELECT * FROM orders", null);
    if (cursor.moveToFirst()) {
        do {
            HashMap<String, String> order = new HashMap<>();
            order.put("id", cursor.getString(cursor.getColumnIndex("id")));
            order.put("cstmr_name", cursor.getString(cursor.getColumnIndex("cstmr_name")));
            order.put("c_phone", cursor.getString(cursor.getColumnIndex("c_phone")));
            order.put("address", cursor.getString(cursor.getColumnIndex("address")));
            order.put("trnx_id", cursor.getString(cursor.getColumnIndex("trnx_id")));
            order.put("product_name", cursor.getString(cursor.getColumnIndex("product_name")));
            order.put("final_total_amount", cursor.getString(cursor.getColumnIndex("final_total_amount")));
            orderList.add(order);
        } while (cursor.moveToNext());
    }
    cursor.close();
    return orderList;
}

    public long addOrder(String cstmr_name, String c_phone, String address, String trnx_id, String product_name, String final_total_amount) {
        ContentValues cv = new ContentValues();
        cv.put("cstmr_name", cstmr_name);
        cv.put("c_phone", c_phone);
        cv.put("address", address);
        cv.put("trnx_id", trnx_id);
        cv.put("product_name", product_name);
        cv.put("final_total_amount", final_total_amount);
        SQLiteDatabase db = getWritableDatabase();
        return db.insert("orders", null, cv);
    }

    public int checkCart(String username, String product) {
        int result = 0;
        String[] str = new String[2];
        str[0] = username;
        str[1] = product;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cr = db.rawQuery("select * from cart where username = ? and product = ?", str);
        if (cr.moveToFirst()) {
            result = 1;
        }
        cr.close();
        return result;
    }

    public ArrayList<HashMap<String, String>> getAllAmbulanceData() {
        ArrayList<HashMap<String, String>> ambulanceList = new ArrayList<>();

        String urlData = "[{\"station_name\":\"AJ HOSPITAL MANGALORE\",\"ambulnc_man_name\":\"ROHIT KUMAR\",\"num_01\":\"1813395618\",\"num_02\":\"1633966128\"},{\"station_name\":\"YENEPOYA HOSPITAL \",\"ambulnc_man_name\":\"RAKESH RAMU\",\"num_01\":\"1814134513\",\"num_02\":\"1767595331\"},{\"station_name\":\"MANIPAL HOSPITAL MANGALORE\",\"ambulnc_man_name\":\"SURYA KUMAR\",\"num_01\":\"1918230894\",\"num_02\":\"1531777296\"},{\"station_name\":\"CITY HOSPITAL MANGALORE\",\"ambulnc_man_name\":\"KULDEEP SHARMA\",\"num_01\":\"1811652380\",\"num_02\":\"1719228739\"},{\"station_name\":\"ALVAS HOSPITAL MANGALORE\",\"ambulnc_man_name\":\"RAMESH \",\"num_01\":\"+8801861183588\",\"num_02\":\"+8801825191166\"},{\"station_name\":\"KMC HOSPITAL MANGALORE\",\"ambulnc_man_name\":\"SUKESH\",\"num_01\":\"+8801758883710\",\"num_02\":\"+8801886470050\"}]";

        try {
            JSONArray jsonArray = new JSONArray(urlData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String station_name = jsonObject.getString("station_name");
                String ambulnc_man_name = jsonObject.getString("ambulnc_man_name");
                String num_01 = jsonObject.getString("num_01");
                String num_02 = jsonObject.getString("num_02");

                HashMap<String, String> ambulance = new HashMap<>();
                ambulance.put("station_name", station_name);
                ambulance.put("ambulnc_man_name", ambulnc_man_name);
                ambulance.put("num_01", num_01);
                ambulance.put("num_02", num_02);

                ambulanceList.add(ambulance);
            }
        }
        catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return ambulanceList;
    }

    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> getAllUsers() {
        ArrayList<HashMap<String, String>> usersList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users", null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> user = new HashMap<>();
                user.put("username", cursor.getString(cursor.getColumnIndex("username")));
                user.put("email", cursor.getString(cursor.getColumnIndex("email")));
                user.put("password", cursor.getString(cursor.getColumnIndex("password")));
                user.put("phone", cursor.getString(cursor.getColumnIndex("phone")));
                usersList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return usersList;
    }

    public ArrayList<HashMap<String, String>> getCartData(String username) {
        ArrayList<HashMap<String, String>> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String[] str = new String[1];
        str[0] = username;

        Cursor cr = db.rawQuery("select * from cart where username = ? ", str);
        if (cr.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                String product = cr.getString(1);
                String price = cr.getString(2);
                map.put("product", product);
                map.put("price", price);
                arr.add(map);
            } while (cr.moveToNext());
        }
        cr.close();
        return arr;
    }
}
