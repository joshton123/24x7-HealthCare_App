
package com.example.my_health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class OederConfirme extends AppCompatActivity {

    EditText cstmr_name1, c_phone1, adress1, trnx_id1, prudct_code1;
    Button confirm_button1, cancel_button1;
    ImageView number_copy3;
    public static String usernameprdct="";
    public static String finalTotalamount="";
    private Database dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oeder_confirme);

        dbHelper = new Database(this, "my_health.db", null, 1);

        cstmr_name1=findViewById(R.id.customer_name);
        c_phone1=findViewById(R.id.c_phone);
        adress1=findViewById(R.id.address);
        trnx_id1=findViewById(R.id.trnx_id);
        prudct_code1=findViewById(R.id.product_code);
        confirm_button1=findViewById(R.id.confirm_button);
        cancel_button1=findViewById(R.id.cancel_button);
        number_copy3=findViewById(R.id.number_copy);

        number_copy3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel","01613120090", null));
                startActivity(intent);
            }
        });

        cancel_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OederConfirme.this, myorders.class));
            }
        });

        confirm_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cstmr_name=cstmr_name1.getText().toString();
                String c_phone=c_phone1.getText().toString();
                String adress=adress1.getText().toString();
                String trnx_id=trnx_id1.getText().toString();
                String prudct_name=prudct_code1.getText().toString();

                if (prudct_name.length()==0){
                    prudct_code1.setError("Enter product name");
                }
                else if (cstmr_name.length()==0){
                    cstmr_name1.setError("Enter your name");
                }

                else if (c_phone.length()==0){
                    c_phone1.setError("Enter your phone number");
                }
                //  else if (cstmr_gmail.length()==0){
                //     cstmr_gmail1.setError("Enter your gmail");
                //  }
                else if (adress.length()==0){
                    adress1.setError("Enter your full adress");
                }

                else if (trnx_id.length()==0){
                    adress1.setError("Enter your trnx_id");
                }
                else {
                    dbHelper.addingToCart(cstmr_name, prudct_name, 0, ""); // Assuming price is not needed here
                    Toast.makeText(OederConfirme.this, "Order Confirmed", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(OederConfirme.this, Home.class));
                }
            }
        });
    }
}
