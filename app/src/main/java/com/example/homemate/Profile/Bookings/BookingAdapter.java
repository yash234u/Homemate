package com.example.homemate.Profile.Bookings;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.homemate.R;

import java.util.ArrayList;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.viewHolder> {

    Context context;
    ArrayList<Model> list;

    public BookingAdapter(Context context, ArrayList<Model> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_details_layout,parent,false);
        return new BookingAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Model model=list.get(position);
        holder.s_name.setText(model.getS_category());
        holder.date.setText(model.getDate());
        holder.time.setText(model.getTime());
        holder.price.setText("₹"+model.getPrice());
        holder.status.setText(model.getStatus());
        if(holder.status.getText().equals("ongoing")){
            holder.status.setTextColor(Color.parseColor("#2196F3"));
        } else if (holder.status.getText().equals("completed")) {
            holder.status.setTextColor(Color.parseColor("#4CAF50"));
        } else if (holder.status.getText().equals("cancelled")) {
            holder.status.setTextColor(Color.parseColor("#F44336"));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView s_name,price,time,date,status;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            s_name=itemView.findViewById(R.id.service_provider_service_text);
            price=itemView.findViewById(R.id.service_provider_price_text);
            time=itemView.findViewById(R.id.service_provider_time_text);
            date=itemView.findViewById(R.id.service_provider_date_text);
            status=itemView.findViewById(R.id.service_provider_status_text);

        }
    }
}
