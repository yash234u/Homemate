package com.example.homemate.Profile.Address;

import android.content.Context;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homemate.Categories.MainAdapter;
import com.example.homemate.Profile.UserModel;
import com.example.homemate.R;

import java.sql.Array;
import java.util.ArrayList;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.myViewHolder> {

    Context context;
    ArrayList<UserModel> list;

    public AddressAdapter(Context context, ArrayList<UserModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.address_layout,parent,false);
        return new AddressAdapter.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        UserModel userModel=list.get(position);
        holder.View_Address.setText(userModel.getAddress());

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
