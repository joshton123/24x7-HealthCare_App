
package com.example.my_health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class myorders extends AppCompatActivity {
    Button checkout, back;
    ListView cartdetails;
    TextView cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorders);

        cartdetails = findViewById(R.id.cartdetails);
        checkout = findViewById(R.id.checkout);
        back = findViewById(R.id.backtomain);
        cost = findViewById(R.id.totalcost);

        SharedPreferences sharedPreferences = getSharedPreferences("sharedprefs", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        Database db = new Database(getApplicationContext(), "myhealth", null, 1);
        ArrayList<HashMap<String, String>> data = db.getCartData(username);
        ArrayAdapter<HashMap<String, String>> adapt = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);

        cartdetails.setAdapter(adapt);

        float totalamount = 0;
        for (HashMap<String, String> item : data) {
            totalamount += Float.parseFloat(item.get("price"));
        }
        cost.setText("TOTAL COST IS " + totalamount);

        float finalTotalamount = totalamount;
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.removeCart(username);
                Intent intent = new Intent(myorders.this, OederConfirme.class);
                intent.putExtra("username", username);
                intent.putExtra("totalamount", String.valueOf(finalTotalamount));
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(myorders.this, Home.class));
            }
        });
    }

}
