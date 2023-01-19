package com.example.gosports;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReservationHistoryAdapter extends RecyclerView.Adapter<ReservationHistoryAdapter.ViewHolder> {
    private ReservationHistory[] listData;

    public ReservationHistoryAdapter(ReservationHistory[] listData){
        this.listData = listData;
    }

    @NonNull
    @Override
    public ReservationHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_reserved, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationHistoryAdapter.ViewHolder holder, int position) {
        final ReservationHistory reservationHistory = listData[position];
        holder.arenaName.setText(listData[position].getArenaName());
        holder.arenaDate.setText(listData[position].getArenaDate());
        holder.arenaTime.setText(listData[position].getArenaTime());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView arenaName, arenaDate, arenaTime;
        public LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.arenaName = itemView.findViewById(R.id.arenaName);
            this.arenaDate = itemView.findViewById(R.id.arenaDate);
            this.arenaTime = itemView.findViewById(R.id.arenaTime);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
}
