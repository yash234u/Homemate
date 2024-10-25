package com.example.homemate.ServiceProvider;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.homemate.Booking.BookingActivity;
import com.example.homemate.R;
import java.util.ArrayList;

public class SP_Adapter extends RecyclerView.Adapter<SP_Adapter.ViewHolder> {

    private Context context;
    private ArrayList<SP_Model> serviceProviderList;
    String serviceName, serviceDesc, servicePrice, serviceImage, serviceInclude, serviceExclude, category;

    public SP_Adapter(Context context, ArrayList<SP_Model> serviceProviderList,
                      String serviceName, String serviceDesc, String servicePrice, String serviceImage,
                      String serviceInclude, String serviceExclude, String category) {
        this.context = context;
        this.serviceProviderList = serviceProviderList;
        this.serviceName = serviceName;
        this.serviceDesc = serviceDesc;
        this.servicePrice = servicePrice;
        this.serviceImage = serviceImage;
        this.serviceInclude = serviceInclude;
        this.serviceExclude = serviceExclude;
        this.category = category;
    }

    public SP_Adapter(ServiceProviderActivity context, ArrayList<SP_Model> serviceProviderList) {
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

        // Handle sp_contact
        if (spModel.getSp_contact() != null) {
            holder.spContact.setText("Contact: " + String.valueOf(spModel.getSp_contact()));
        } else {
            holder.spContact.setText("Contact: N/A");
        }

        holder.spServices.setText(spModel.getSp_services());
        holder.spExperience.setText(spModel.getSp_experience());

        // Load image using Glide
        Glide.with(context).load(spModel.getSp_img())
                .into(holder.spImage);

        // Handle sp_ratings
        if (spModel.getSp_ratings() != null) {
            float ratingValue = 0;
            try {
                if (spModel.getSp_ratings() instanceof Long) {
                    ratingValue = ((Long) spModel.getSp_ratings()).floatValue();
                } else if (spModel.getSp_ratings() instanceof Double) {
                    ratingValue = ((Double) spModel.getSp_ratings()).floatValue();
                } else if (spModel.getSp_ratings() instanceof String) {
                    ratingValue = Float.parseFloat((String) spModel.getSp_ratings());
                }
                holder.spRating.setRating(ratingValue);
            } catch (NumberFormatException e) {
                holder.spRating.setRating(0); // Default to 0 if parsing fails
            }
        } else {
            holder.spRating.setRating(0); // Default to 0 if no rating is available
        }

        // Set an OnClickListener on the CardView to handle clicks
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start BookingActivity
                Intent intent = new Intent(context, BookingActivity.class);

                // Pass data to BookingActivity using Intent extras
                intent.putExtra("sp_name", spModel.getSp_name());
                intent.putExtra("sp_contact", String.valueOf(spModel.getSp_contact()));
                intent.putExtra("sp_experience", spModel.getSp_experience());
                intent.putExtra("sp_img", spModel.getSp_img());
                intent.putExtra("sp_ratings", String.valueOf(spModel.getSp_ratings()));
                intent.putExtra("sp_services", spModel.getSp_services());

                // Pass Service details data to BookingActivity using Intent extras
                intent.putExtra("servicename", serviceName);
                intent.putExtra("servicedesc", serviceDesc);
                intent.putExtra("serviceprice", servicePrice);
                intent.putExtra("serviceimage", serviceImage);
                intent.putExtra("serviceinclude", serviceInclude);
                intent.putExtra("serviceexclude", serviceExclude);
                intent.putExtra("category", category);

                // Start the BookingActivity
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return serviceProviderList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView spName, spContact, spServices, spExperience;
        ImageView spImage;
        RatingBar spRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            spName = itemView.findViewById(R.id.SPName);
            spContact = itemView.findViewById(R.id.SPContact);
            spServices = itemView.findViewById(R.id.SPServices);
            spExperience = itemView.findViewById(R.id.SPExperience);
            spImage = itemView.findViewById(R.id.SPImage);
            spRating = itemView.findViewById(R.id.ratingBar);

            // Initialize other views as needed
        }
    }
}
