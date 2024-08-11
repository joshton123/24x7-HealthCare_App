package com.example.my_health;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class HomeAdmin extends AppCompatActivity {
    LinearLayout labtest,buymedicine,findmydoc,articles,orders,logout,c_findamb,helpline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        articles = findViewById(R.id.c_articles);
        orders = findViewById(R.id.c_orderdetails);
        logout = findViewById(R.id.logout);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                startActivity(new Intent(HomeAdmin.this,AdminLogin.class));
            }

        });
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeAdmin.this,TotalOrder.class));
            }
        });


        articles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeAdmin.this,Totaluser.class));

            }
        });


    }
}