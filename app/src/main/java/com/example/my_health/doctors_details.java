package com.example.my_health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class doctors_details extends AppCompatActivity {
    private String[][] PHYSICIAN = {
            {"Name : Dr. John Smith", "Address : KARKALA", "EXP : 5 Years", "Fees : Rs. 2500"},
            {"Name : Dr. Alice Johnson", "Address : NITTE", "EXP : 7 Years", "Fees : Rs. 2500"},
            {"Name : Dr. Emily Davis", "Address : KARKALA", "EXP : 5 Years", "Fees : Rs. 2500"},
            {"Name : Dr. Michael Brown", "Address : NITTE", "EXP : 3 Years", "Fees : Rs. 2500"},
            {"Name : Dr. David Wilson", "Address : KARKALA", "EXP : 5 Years", "Fees : Rs. 1000"},
            {"Name : Dr. Sarah Taylor", "Address : NITTE", "EXP : 3 Years", "Fees : Rs. 2000"}
    };

    private String[][] DENTIST = {
            {"Name : Dr. Anna Lee", "Address : KARKALA", "EXP : 2 Years", "Fees : Rs. 2000"},
            {"Name : Dr. James Anderson", "Address : NITTE", "EXP : 4 Years", "Fees : Rs. 2000"},
            {"Name : Dr. William Thomas", "Address : KARKALA", "EXP : 4 Years", "Fees : Rs. 1000"},
            {"Name : Dr. Linda Moore", "Address : NITTE", "EXP : 12 Years", "Fees : Rs. 1800"},
            {"Name : Dr. Robert Miller", "Address : KARKALA", "EXP : 27 Years", "Fees : Rs. 500"},
            {"Name : Dr. Jessica Martinez", "Address : NITTE", "EXP : 1 Year", "Fees : Rs. 620"}
    };

    private String[][] SURGEON = {
            {"Name : Dr. Christopher Jackson", "Address : KARKALA", "EXP : 38 Years", "Fees : Rs. 3000"},
            {"Name : Dr. Patricia Harris", "Address : NITTE", "EXP : 7 Years", "Fees : Rs. 1500"},
            {"Name : Dr. Richard Martin", "Address : KARKALA", "EXP : 18 Years", "Fees : Rs. 2000"},
            {"Name : Prof. Dr. Susan Clark", "Address : NITTE", "EXP : 33 Years", "Fees : Rs. 1000"},
            {"Name : Dr. Daniel Lewis", "Address : KARKALA", "EXP : 15 Years", "Fees : Rs. 2000"},
            {"Name : Dr. Jennifer Robinson", "Address : NITTE", "EXP : 20 Years", "Fees : Rs. 1000"}
    };

    private String[][] DIETITIAN = {
            {"Name : Dr. Lisa Walker", "Address : KARKALA", "EXP : 13 Years", "Fees : Rs. 2500"},
            {"Name : Dr. Brian Hall", "Address : NITTE", "EXP : 14 Years", "Fees : Rs. 2500"},
            {"Name : Dr. Sharon Young", "Address : KARKALA", "EXP : 7 Years", "Fees : Rs. 2000"},
            {"Name : Dr. Kevin King", "Address : NITTE", "EXP : 25 Years", "Fees : Rs. 2500"},
            {"Name : Dr. Karen Scott", "Address : KARKALA", "EXP : 2 Years", "Fees : Rs. 2500"},
            {"Name : Dr. Steven Green", "Address : NITTE", "EXP : 8 Years", "Fees : Rs. 1500"}
    };

    private String[][] CARDIOLOGIST = {
            {"Name : Dr. Paul Adams", "Address : KARKALA", "EXP : 12 Years", "Fees : Rs. 1800"},
            {"Name : Dr. Nancy Hill", "Address : NITTE", "EXP : 12 Years", "Fees : Rs. 1500"},
            {"Name : Dr. Larry Baker", "Address : KARKALA", "EXP : 7 Years", "Fees : Rs. 1500"},
            {"Name : Dr. Cynthia Wright", "Address : NITTE", "EXP : 25 Years", "Fees : Rs. 2000"},
            {"Name : Dr. Andrew Carter", "Address : KARKALA", "EXP : 7 Years", "Fees : Rs. 2000"},
            {"Name : Dr. Amanda Mitchell", "Address : NITTE", "EXP : 18 Years", "Fees : Rs. 1500"}
    };

    TextView doctext;
    ListView docdetails;
    Button backtomydoc;
    String [][] doc_details= {};
    ArrayList list;
    HashMap<String, String> item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_details);
        doctext=findViewById(R.id.lab);
        docdetails=findViewById(R.id.cartdetails);
        backtomydoc=findViewById(R.id.backtomain);

        Intent it = getIntent();
        String title= it.getStringExtra("title");
        doctext.setText(title);

        if(title.compareTo("PHYSICIAN") == 0){
            doc_details = PHYSICIAN ;
        }
        if(title.compareTo("DENTIST") == 0){
            doc_details = DENTIST ;
        }
        if(title.compareTo("SURGEON") == 0){
            doc_details = SURGEON ;
        }
        if(title.compareTo("DIETITIAN") == 0){
            doc_details = DIETITIAN ;
        }
        if(title.compareTo("CARDIOLOGIST") == 0){
            doc_details = CARDIOLOGIST ;
        }
        

        backtomydoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(doctors_details.this,findmydoc.class));
            }
        });
        list = new ArrayList();
        for(int i = 0 ; i < doc_details.length;i++){
            item = new HashMap<String, String>();
            item.put("docname",doc_details[i][0]);
            item.put("docaddress",doc_details[i][1]);
            item.put("docexperience",doc_details[i][2]);
            item.put("docfees",doc_details[i][3]);
            list.add(item);
        }
        SimpleAdapter adap = new SimpleAdapter(
                this,list,
                R.layout.detaildesign,
                new String[]{"docname","docaddress","docexperience","docfees"},
                new int[]{R.id.t_name, R.id.t_price,
                R.id.docexperience, R.id.docfees});
        docdetails.setAdapter(adap);
        docdetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(doctors_details.this,booking.class);
                intent.putExtra("docname",doc_details[i][0]);
                intent.putExtra("docaddress",doc_details[i][1]);
                intent.putExtra("docfees",doc_details[i][3]);

                startActivity(intent);
            }
        });}


}