package com.example.gosports;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class sportmenu extends AppCompatActivity {

    private ImageView basketball_menu, futsal_menu, profile;
    int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sportmenu);

        userID = getIntent().getIntExtra("userID", 0);

        basketball_menu = findViewById(R.id.img_basketball);
        futsal_menu = findViewById(R.id.img_futsal);
        profile = findViewById(R.id.img_profile);

        basketball_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sportmenu.this, basketball_menu.class);
                intent.putExtra("userID", userID);
                startActivity(intent);
            }
        });

        futsal_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sportmenu.this, futsal_menu.class);
                intent.putExtra("userID", userID);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sportmenu.this, Profile.class);
                intent.putExtra("userID", userID);
                startActivity(intent);
            }
        });
    }
}