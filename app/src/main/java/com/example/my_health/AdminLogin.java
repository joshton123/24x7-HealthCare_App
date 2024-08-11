
package com.example.my_health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {
    EditText login_username, login_password;
    Button login_button, userBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        Database dbHelper = new Database(this, "myhealth.db", null, 1);
        if (dbHelper.login("admin1", "admin1") != 1) {
            dbHelper.register("admin1", "admin@example.com", "", "admin1");
        }

        login_username = findViewById(R.id.r_email);
        login_password = findViewById(R.id.r_Cpassword);
        login_button = findViewById(R.id.add_cart_med);
        userBtn = findViewById(R.id.userBtn);

        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminLogin.this, Login.class));
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String username = login_username.getText().toString().trim();
        String password = login_password.getText().toString().trim();

        Database dbHelper = new Database(this, "my_health.db", null, 1);
        int loginResult = dbHelper.login(username, password);
        if (loginResult == 1) {
            // Login successful
            Toast.makeText(AdminLogin.this, "Login successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AdminLogin.this, HomeAdmin.class));
        } else {
            // Login failed
            Toast.makeText(AdminLogin.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }
}
