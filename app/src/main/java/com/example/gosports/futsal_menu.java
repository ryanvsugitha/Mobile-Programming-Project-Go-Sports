package com.example.gosports;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class futsal_menu extends AppCompatActivity {

    int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_futsal_menu);

        userID = getIntent().getIntExtra("userID", 0);

        MyListData[] myListData = new MyListData[]{
                new MyListData("Penaldo Academy", R.drawable.futsal_arena, "Futsal Academy by Penaldo", userID),
                new MyListData("Em Yu Arena", R.drawable.futsal_arena, "Em Yu official home", userID),
                new MyListData("Pessi Arena", R.drawable.futsal_arena, "Pessi had played here", userID),
                new MyListData("Decul Club", R.drawable.futsal_arena, "Decul Lover club and arena", userID),
                new MyListData("Men Siti Academy", R.drawable.futsal_arena, "Men Siti Academy by Urling Baaland", userID),
                new MyListData("Bango United Arena", R.drawable.futsal_arena, "Bango official court", userID),
                new MyListData("Meriam Lontong Arena", R.drawable.futsal_arena, "Pucuk dingin", userID),
                new MyListData("Ayam Lontong Club", R.drawable.futsal_arena, "Futsal arena", userID),
                new MyListData("Jakarta Futsal", R.drawable.futsal_arena, "Jakarta Biggest Futsal Arena", userID),
                new MyListData("SUNIB Futsal", R.drawable.futsal_arena, "Futsal for Sunibian", userID),
                new MyListData("ALSUT Futsal", R.drawable.futsal_arena, "Futsal arena at ALSUT", userID),
                new MyListData("Mbappuk Academy", R.drawable.futsal_arena, "Academy by Kura Kura Ninja Mbappuk", userID),
                new MyListData("Dedemit Futsal Club", R.drawable.futsal_arena, "Dedemit lover club and arena", userID),
        };

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        MyListAdapter adapter = new MyListAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}