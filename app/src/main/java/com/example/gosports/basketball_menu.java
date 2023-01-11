package com.example.gosports;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class basketball_menu extends AppCompatActivity {

    int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basketball_menu);

        userID = getIntent().getIntExtra("userID", 0);

        MyListData[] myListData = new MyListData[]{
                new MyListData("GOKS Tangerang", R.drawable.basketball_arena, "Tangerang Basketball", userID),
                new MyListData("AJAX PIK", R.drawable.basketball_arena, "Basketball at PIK", userID),
                new MyListData("JEKS Serpong", R.drawable.basketball_arena, "Cheapest Basketball Court at Serpong", userID),
                new MyListData("Basketball Warriors", R.drawable.basketball_arena, "Warriors Academy and Court", userID),
                new MyListData("Utah Jezz", R.drawable.basketball_arena, "Jezz Basketball", userID),
                new MyListData("Delles Mevs", R.drawable.basketball_arena, "Meverik Basketball Court", userID),
                new MyListData("Chikago Balls", R.drawable.basketball_arena, "Balls Basketball", userID),
                new MyListData("Ayam Geprek Basketball", R.drawable.basketball_arena, "Basketball with Ayam Geprek", userID),
                new MyListData("Jakarta Basketball", R.drawable.basketball_arena, "Jakarta Basketball", userID),
                new MyListData("SUNIB Basketball", R.drawable.basketball_arena, "Basketball for Sunibian", userID),
                new MyListData("ALSUT Basketball", R.drawable.basketball_arena, "Basketball arena at ALSUT", userID),
                new MyListData("Lekers", R.drawable.basketball_arena, "Los Bantos Lekers", userID),
                new MyListData("Bruklin Mets", R.drawable.basketball_arena, "Bruklin Basketball Academy", userID),
        };

//        MyListData[] myListData = new MyListData[1];
//
//        myListData[0] = new MyListData("GOKS Tangerang", R.drawable.basketball_arena, "Tangerang Basketball", userID);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        MyListAdapter adapter = new MyListAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}