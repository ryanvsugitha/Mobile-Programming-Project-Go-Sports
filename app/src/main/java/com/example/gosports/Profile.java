package com.example.gosports;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    int userID;
    TextView username, email, phone;
    private user user;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        db = new DatabaseHandler(this);
        user = new user();

        userID = getIntent().getIntExtra("userID", 0);
        user = db.getUserInfo(userID);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);

        username.setText(user.getUsername());
        email.setText(user.getEmail());
        phone.setText(user.getPhone());

        int x = db.getCount(userID);

//        String a = Integer.toString(db.getCount(userID));
//
//        Toast.makeText(this, a, Toast.LENGTH_SHORT).show();

        ReservationHistory[] history = new ReservationHistory[x];

        String[][] temp = new String[x][3];
        temp = db.getData(userID, x);

        for(int i = 0; i<x; i++){
            history[i] = new ReservationHistory(userID, temp[i][0], temp[i][1], temp[i][2]);
        }
        db.close();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ReservationHistoryAdapter adapter = new ReservationHistoryAdapter(history);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}