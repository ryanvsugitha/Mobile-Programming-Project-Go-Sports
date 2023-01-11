package com.example.gosports;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {
    private MyListData[] listData;

    public MyListAdapter(MyListData[] listData){
        this.listData = listData;
    }

    @NonNull
    @Override
    public MyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyListAdapter.ViewHolder holder, int position) {
        final MyListData myListData = listData[position];
        holder.arenaName.setText(listData[position].getArenaName());
        holder.arenaDesc.setText(listData[position].getArenaDesc());
        holder.arenaImg.setImageResource(listData[position].getArenaImg());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), arena_reservation.class);
                i.putExtra("arenaName", myListData.getArenaName());
                i.putExtra("arenaDesc", myListData.getArenaDesc());
                i.putExtra("arenaImg", myListData.getArenaImg());
                i.putExtra("userID", myListData.getUserID());
                view.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.length;
    }

    //mapping item layout
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView arenaImg;
        public TextView arenaName, arenaDesc;
        public LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.arenaImg = itemView.findViewById(R.id.arenaImg);
            this.arenaName = itemView.findViewById(R.id.arenaName);
            this.arenaDesc = itemView.findViewById(R.id.arenaDesc);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
}
