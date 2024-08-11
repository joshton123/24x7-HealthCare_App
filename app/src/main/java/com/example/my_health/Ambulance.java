
package com.example.my_health;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Ambulance extends AppCompatActivity {

    GridView recyclerview1;
    ProgressBar simpleProgressBar;

    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance);

        recyclerview1 = findViewById(R.id.recyclerview1);
        simpleProgressBar = findViewById(R.id.simpleProgressBar);
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

            LayoutInflater layoutInflater = getLayoutInflater();
            @SuppressLint("ViewHolder") View myView = layoutInflater.inflate(R.layout.ambulance_design_part, null);
            ImageView dial_01_image = myView.findViewById(R.id.dial_01_image);
            ImageView dial_02_image = myView.findViewById(R.id.dial_02_image);
            ImageView copy_image_01 = myView.findViewById(R.id.copy_image_01);
            ImageView copy_image_02 = myView.findViewById(R.id.copy_image_02);

            TextView ambulance_man_name = myView.findViewById(R.id.ambulance_man_name);
            TextView number1_text = myView.findViewById(R.id.number1_text);
            TextView number2_text = myView.findViewById(R.id.number2_text);
            TextView station_name_fire = myView.findViewById(R.id.station_name_fire);

            HashMap<String, String> hashMap = arrayList.get(i);
            String station_name = hashMap.get("station_name");
            String ambulnc_man_name1 = hashMap.get("ambulnc_man_name");
            String num1 = hashMap.get("num_01");
            String num2 = hashMap.get("num_02");

            station_name_fire.setText(station_name);
            ambulance_man_name.setText(ambulnc_man_name1);
            number1_text.setText(num1);
            number2_text.setText(num2);

            dial_01_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", num1, null));
                    startActivity(intent);
                }
            });

            dial_02_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", num2, null));
                    startActivity(intent);
                }
            });

            copy_image_01.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    copyToClipboard(num1);
                }
            });

            copy_image_02.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    copyToClipboard(num2);
                }
            });

            return myView;
        }

        private void copyToClipboard(String text) {
            Context context = getApplicationContext();
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("label", text);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(context, "Copied", Toast.LENGTH_LONG).show();
        }
    }


    public void Createtable1() {
        // Database operations here
        Database db = new Database(this, "my_health", null, 1);
        arrayList = db.getAllAmbulanceData();

        MyAdapter myAdapter = new MyAdapter();
        recyclerview1.setAdapter(myAdapter);

        // Hide the progress bar
        simpleProgressBar.setVisibility(View.GONE);
    }

}
