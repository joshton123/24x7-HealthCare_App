
package com.example.my_health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText r_email, r_username, r_phone, r_password, r_Cpassword;
    Button register_button;
    TextView login_nu;
    ProgressBar simpleProgressBar;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        database = new Database(getApplicationContext(), "myhealth", null, 1);

        r_email = findViewById(R.id.r_email);
        r_username = findViewById(R.id.med_name);
        r_phone = findViewById(R.id.baddress);
        r_password = findViewById(R.id.med_price);
        r_Cpassword = findViewById(R.id.r_Cpassword);
        register_button = findViewById(R.id.add_cart_med);
        login_nu = findViewById(R.id.registered);
        simpleProgressBar = findViewById(R.id.simpleProgressBar);

        login_nu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = r_username.getText().toString();
                String email = r_email.getText().toString();
                String phone = r_phone.getText().toString();
                String password = r_password.getText().toString();
                String Cpassword = r_Cpassword.getText().toString();

                simpleProgressBar.setVisibility(View.VISIBLE);

                if (username.length() == 0 || password.length() == 0 || email.length() == 0 || phone.length() == 0 || Cpassword.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill in all the information", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.compareTo(Cpassword) == 0) {
                        if (isValidPassword(password)) {
                            // Register user in the database
                            long result = database.register(username, email, phone, password);
                            if (result != -1) {
                                Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Register.this, Login.class));
                            } else {
                                Toast.makeText(getApplicationContext(), "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Password must contain at least 6 characters, including letters and digits", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Confirm password didn't match", Toast.LENGTH_SHORT).show();
                    }
                }

                simpleProgressBar.setVisibility(View.GONE);
            }
        });
    }

    public static boolean isValidPassword(String password) {
        int digitCount = 0;
        int letterCount = 0;

        if (password.length() < 6) {
            return false;
        }

        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                digitCount++;
            } else if (Character.isLetter(c)) {
                letterCount++;
            }
        }

        return digitCount > 0 && letterCount > 0;
    }
}
