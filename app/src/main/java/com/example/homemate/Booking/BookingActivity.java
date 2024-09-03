package com.example.homemate.Booking;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.homemate.R;

public class BookingActivity extends AppCompatActivity {

    TextView spName, spContact, spExperience, spServices, serviceName, serviceDesc, servicePrice, serviceInclude, serviceExclude, category;
    ImageView spImage, serviceImage;
    RatingBar spRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        // Initialize views for Service Provider
        spName = findViewById(R.id.spNameTextView);
        spContact = findViewById(R.id.spContactTextView);
        spExperience = findViewById(R.id.spExperienceTextView);
        spServices = findViewById(R.id.spServicesTextView);
        spImage = findViewById(R.id.spImageView);
        spRating = findViewById(R.id.spRatingBar);

        // Initialize views for Service details
        serviceName = findViewById(R.id.serviceNameTextView);
        serviceDesc = findViewById(R.id.serviceDescTextView);
        servicePrice = findViewById(R.id.servicePriceTextView);
        serviceInclude = findViewById(R.id.serviceIncludeTextView);
        serviceExclude = findViewById(R.id.serviceExcludeTextView);
        category = findViewById(R.id.categoryTextView);
        serviceImage = findViewById(R.id.serviceImageView);

        // Retrieve Service Provider data from Intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("sp_name");
        String contact = intent.getStringExtra("sp_contact");
        String experience = intent.getStringExtra("sp_experience");
        String img = intent.getStringExtra("sp_img");
        String ratings = intent.getStringExtra("sp_ratings");
        String services = intent.getStringExtra("sp_services");

        // Retrieve Service data from Intent
        String serviceNameStr = intent.getStringExtra("servicename");
        String serviceDescStr = intent.getStringExtra("servicedesc");
        String servicePriceStr = intent.getStringExtra("serviceprice");
        String serviceImgStr = intent.getStringExtra("serviceimage");
        String serviceIncludeStr = intent.getStringExtra("serviceinclude");
        String serviceExcludeStr = intent.getStringExtra("serviceexclude");
        String categoryStr = intent.getStringExtra("category");

        // Set data to views for Service Provider
        if (name != null) {
            spName.setText(name);
        }

        if (contact != null) {
            spContact.setText(contact);
        }

        if (experience != null) {
            spExperience.setText(experience);
        }

        if (services != null) {
            spServices.setText(services);
        }

        if (img != null) {
            Glide.with(this).load(img).into(spImage);
        }

        if (ratings != null && !ratings.isEmpty()) {
            spRating.setRating(Float.parseFloat(ratings));
        } else {
            spRating.setRating(0);
        }

        // Set data to views for Service details
        if (serviceNameStr != null) {
            serviceName.setText(serviceNameStr);
        }

        if (serviceDescStr != null) {
            serviceDesc.setText(serviceDescStr);
        }

        if (servicePriceStr != null) {
            servicePrice.setText(servicePriceStr);
        }

        if (serviceIncludeStr != null) {
            serviceInclude.setText(serviceIncludeStr);
        }

        if (serviceExcludeStr != null) {
            serviceExclude.setText(serviceExcludeStr);
        }

        if (categoryStr != null) {
            category.setText(categoryStr);
        }

        if (serviceImgStr != null) {
            Glide.with(this).load(serviceImgStr).into(serviceImage);
        }
    }
}
