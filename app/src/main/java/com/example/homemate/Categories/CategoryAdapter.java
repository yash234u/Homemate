package com.example.homemate.Categories;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.homemate.Categories.AC.ACActivity;
import com.example.homemate.Categories.Appliance.ApplianceActivity;
import com.example.homemate.Categories.Carpenter.CarpenterActivity;
import com.example.homemate.Categories.Electricians.ElectriciansActivity;
import com.example.homemate.Categories.Plumbing.PlumbingActivity;
import com.example.homemate.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewHolder> {

    private Context context;
    private List<CategoryModel> list;

    public CategoryAdapter(Context context, List<CategoryModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list ,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getImg_url()).into(holder.catImg);
        holder.catName.setText(list.get(position).getName());

        switch (position){
            case 0:
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(view.getContext(), ApplianceActivity.class);
                        view.getContext().startActivity(intent);
                    }
                });
                break;
            case 1:
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       // Intent intent=new Intent(view.getContext(), ACActivity.class);
                      //  view.getContext().startActivity(intent);
                    }
                });
                break;
            case 2:
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(view.getContext(), CarpenterActivity.class);
                        view.getContext().startActivity(intent);
                    }
                });
                break;
            case 3:
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(view.getContext(), ElectriciansActivity.class);
                        view.getContext().startActivity(intent);
                    }
                });
                break;
            case 4:
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(view.getContext(), PlumbingActivity.class);
                        view.getContext().startActivity(intent);
                    }
                });
                break;
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView catImg;
        TextView catName;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            catImg = itemView.findViewById(R.id.cat_img);
            catName = itemView.findViewById(R.id.cat_name);
        }
    }
}
