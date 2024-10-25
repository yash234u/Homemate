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
        Model model = list.get(position);

        // Show or hide each TextView based on whether the corresponding data is available
        if (model.getServiceName() != null && !model.getServiceName().isEmpty()) {
            holder.s_name.setText(model.getServiceName());
            holder.s_name.setVisibility(View.VISIBLE);  // Show if not null
        } else {
            holder.s_name.setVisibility(View.GONE);  // Hide if null or empty
        }

        if (model.getServiceDate() != null && !model.getServiceDate().isEmpty()) {
            holder.date.setText(model.getServiceDate());
            holder.date.setVisibility(View.VISIBLE);  // Show if not null
        } else {
            holder.date.setVisibility(View.GONE);  // Hide if null or empty
        }

        if (model.getServiceTime() != null && !model.getServiceTime().isEmpty()) {
            holder.time.setText(model.getServiceTime());
            holder.time.setVisibility(View.VISIBLE);  // Show if not null
        } else {
            holder.time.setVisibility(View.GONE);  // Hide if null or empty
        }

        if (model.getServicePrice() != null && !model.getServicePrice().isEmpty()) {
            holder.price.setText("₹" + model.getServicePrice());
            holder.price.setVisibility(View.VISIBLE);  // Show if not null
        } else {
            holder.price.setVisibility(View.GONE);  // Hide if null or empty
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
