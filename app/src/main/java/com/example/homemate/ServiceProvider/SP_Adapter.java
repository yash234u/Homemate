package com.example.homemate.ServiceProvider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.homemate.R;
import java.util.ArrayList;

public class SP_Adapter extends RecyclerView.Adapter<SP_Adapter.ViewHolder> {

    private Context context;
    private ArrayList<SP_Model> serviceProviderList;

    public SP_Adapter(Context context, ArrayList<SP_Model> serviceProviderList) {
        this.context = context;
        this.serviceProviderList = serviceProviderList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_serviceprovider_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SP_Model spModel = serviceProviderList.get(position);
        holder.spName.setText(spModel.getSp_name());
        holder.spContact.setText("Contact: "+spModel.getSp_contact());
        holder.spServices.setText("Services: "+spModel.getSp_services());
        holder.spExperience.setText("Experience: "+spModel.getSp_experience());
        Glide.with(context).load(spModel.getSp_img())
                .into(holder.spImage);
        // Set other fields as needed (ratings, image, etc.)
    }

    @Override
    public int getItemCount() {
        return serviceProviderList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView spName, spContact, spServices, spExperience;
        ImageView spImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            spName = itemView.findViewById(R.id.SPName);
            spContact = itemView.findViewById(R.id.SPContact);
            spServices = itemView.findViewById(R.id.SPServices);
            spExperience = itemView.findViewById(R.id.SPExperience);
            spImage = itemView.findViewById(R.id.SPImage);
            // Initialize other views as needed
        }
    }
}
