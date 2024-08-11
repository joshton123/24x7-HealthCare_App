
package com.example.my_health;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class Totaluser extends AppCompatActivity {

    GridView recyclerview1;
    ProgressBar simpleProgressBar;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_totaluser);

        recyclerview1=findViewById(R.id.recyclerview1);
        simpleProgressBar=findViewById(R.id.simpleProgressBar);
        database = new Database(getApplicationContext(), "myhealth", null, 1);
        Createtable1();
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

            LayoutInflater layoutInflater= getLayoutInflater();
            View myView=layoutInflater.inflate(R.layout.totluser_design,null);


            TextView Email=myView.findViewById(R.id.Email);
            TextView username=myView.findViewById(R.id.username);
            TextView phnmber=myView.findViewById(R.id.phnmber);
            TextView password=myView.findViewById(R.id.password);


            HashMap<String,String> hashMap=arrayList.get(i);
            String email=hashMap.get("email");
            String username5=hashMap.get("username");
            String phone=hashMap.get("phone");
            String password5=hashMap.get("password");


            Email.setText(email);
            username.setText(username5);
            phnmber.setText(phone);
            password.setText(password5);

            return myView;
        }
    }

    public void Createtable1 () {
        arrayList = database.getAllUsers(); // Implement getAllUsers() in Database class
        if (!arrayList.isEmpty()) {
            MyAdapter myAdapter=new MyAdapter();
            recyclerview1.setAdapter(myAdapter);
        } else {
            Log.e("Totaluser", "Failed to fetch user data from database");
        }
        simpleProgressBar.setVisibility(View.GONE);
    }
}
