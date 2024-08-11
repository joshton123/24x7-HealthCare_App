
package com.example.my_health;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class TotalOrder extends AppCompatActivity {

    GridView recyclerView;
    ProgressBar simpleProgressBar;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_order);

        recyclerView = findViewById(R.id.recyclerview1);
        simpleProgressBar = findViewById(R.id.simpleProgressBar);
        database = new Database(getApplicationContext(), "myhealth", null, 1);

        createTable();
    }

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View myView = layoutInflater.inflate(R.layout.order_design, null);

            TextView orderList = myView.findViewById(R.id.orderlist);
            TextView customerName = myView.findViewById(R.id.customername);
            TextView productName = myView.findViewById(R.id.prdctname);
            TextView phoneNumber = myView.findViewById(R.id.phnmber);
            TextView address = myView.findViewById(R.id.adress);
            TextView transactionId = myView.findViewById(R.id.tnjksn);
            TextView totalAmount = myView.findViewById(R.id.totaltaka);

            HashMap<String, String> hashMap = arrayList.get(i);
            orderList.setText(hashMap.get("id"));
            customerName.setText(hashMap.get("cstmr_name"));
            productName.setText(hashMap.get("product_name"));
            phoneNumber.setText(hashMap.get("c_phone"));
            address.setText(hashMap.get("address"));
            transactionId.setText(hashMap.get("trnx_id"));
            totalAmount.setText(hashMap.get("final_total_amount"));

            return myView;
        }
    }

    public void createTable() {
        arrayList = database.getAllOrders();
        if (!arrayList.isEmpty()) {
            MyAdapter myAdapter = new MyAdapter();
            recyclerView.setAdapter(myAdapter);
        } else {
            Log.e("TotalOrder", "Failed to fetch orders from database");
        }
        simpleProgressBar.setVisibility(View.GONE);
    }
}
