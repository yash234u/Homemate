package com.example.homemate.Categories;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.homemate.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import kotlin.collections.ArrayDeque;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.myViewHolder> {

    private Context context;
    private ArrayList<Model> servicesList;

    public MainAdapter(Context context, ArrayList<Model> servicesList) {
        this.context = context;
        this.servicesList = servicesList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.view_services_list,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Model model=servicesList.get(position);
        holder.sname.setText(model.getName());
        holder.dname.setText(model.getDesc());
        holder.price.setText(model.getPrice());
        Glide.with(context).load(model.getImage())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return servicesList.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView sname,dname,price;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=(ImageView) itemView.findViewById(R.id.ServiceImage);
            sname=(TextView) itemView.findViewById(R.id.ServiceName);
            dname=(TextView) itemView.findViewById(R.id.ServiceDescription);
            price=(TextView) itemView.findViewById(R.id.ServicePrice);
        }
    }
}