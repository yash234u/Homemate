package com.example.homemate.Booking;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homemate.Categories.FullViewActivity;
import com.example.homemate.Profile.Address.AddressAdapter;
import com.example.homemate.Profile.UserModel;
import com.example.homemate.R;

import java.util.ArrayList;

public class SelectAddressAdapter extends RecyclerView.Adapter<SelectAddressAdapter.myViewHolder> {
    Context context;
    ArrayList<UserModel> list;

    public SelectAddressAdapter(Context context, ArrayList<UserModel> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.address_layout,parent,false);
        return new SelectAddressAdapter.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        UserModel userModel=list.get(position);
        holder.View_Address.setText(userModel.getAddress());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Final_Booking__details_Activity.class);
                intent.putExtra("selectedaddress",userModel.getAddress());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        TextView View_Address;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            View_Address=itemView.findViewById(R.id.View_Address);
        }
    }
}
