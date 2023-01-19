package com.example.gosports;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    Button btn_login;
    EditText username, password;
    private DatabaseHandler db;
    private user user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHandler(this);
        user = new user();

        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = username.getText().toString();
                String pass = password.getText().toString();

                user.setUsername(name);
                user.setPassword(pass);

                if(db.authUser(user)){
                    int userID = db.getID(user);
                    Toast.makeText(login.this, "Welcome " + name, Toast.LENGTH_SHORT).show();
                    db.close();
                    Intent intent = new Intent(login.this, sportmenu.class);
                    intent.putExtra("userID", userID);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(login.this, "Invalid credentials!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}