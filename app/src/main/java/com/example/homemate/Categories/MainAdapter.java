package com.example.homemate.Categories;

import android.content.Context;
import android.content.Intent;
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

    public MainAdapter(Context context, ArrayList<Model> servicesList, String category) {
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
        holder.tagline.setText(model.getTagline());
        holder.price.setText("Price: ₹ "+model.getPrice());
        Glide.with(context).load(model.getImage())
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, FullViewActivity.class);
                intent.putExtra("servicename",model.getName());
                intent.putExtra("servicedesc",model.getDesc());
                intent.putExtra("serviceprice",model.getPrice());
                intent.putExtra("serviceimage",model.getImage());
                intent.putExtra("serviceinclude",model.getInclude());
                intent.putExtra("serviceexclude",model.getExclude());
                intent.putExtra("category",model.getCategory());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return servicesList.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView sname,price,tagline,include,exclude;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=(ImageView) itemView.findViewById(R.id.ServiceImage);
            sname=(TextView) itemView.findViewById(R.id.ServiceName);
            tagline=(TextView) itemView.findViewById(R.id.ServiceTagline);
            price=(TextView) itemView.findViewById(R.id.ServicePrice);
            include=(TextView) itemView.findViewById(R.id.included_in_service);
            exclude=(TextView) itemView.findViewById(R.id.excluded_in_service);

        }
    }
}
