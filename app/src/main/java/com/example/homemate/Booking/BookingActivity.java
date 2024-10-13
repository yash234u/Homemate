package com.example.homemate.Booking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.homemate.R;
import com.example.homemate.register.UserRegistrationActivity;

public class BookingActivity extends AppCompatActivity {

    TextView spName, spContact, spExperience, spServices, serviceName, serviceDesc, servicePrice, serviceInclude, serviceExclude, category;
    ImageView spImage, serviceImage;
    RatingBar spRating;
    private DatePicker datePicker;
    private Spinner timeSlotSpinner;
    private Button bookButton;

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
        spName.setText(name != null ? name : "N/A");
        spContact.setText(contact != null ? contact : "N/A");
        spExperience.setText(experience != null ? experience : "N/A");
        spServices.setText(services != null ? services : "N/A");
        Glide.with(this).load(img).into(spImage);
        spRating.setRating(ratings != null ? Float.parseFloat(ratings) : 0);

        // Set data to views for Service details
        serviceName.setText(serviceNameStr != null ? serviceNameStr : "N/A");
        serviceDesc.setText(serviceDescStr != null ? serviceDescStr : "N/A");
        servicePrice.setText(servicePriceStr != null ? servicePriceStr : "N/A");
        serviceInclude.setText(serviceIncludeStr != null ? serviceIncludeStr : "N/A");
        serviceExclude.setText(serviceExcludeStr != null ? serviceExcludeStr : "N/A");
        category.setText(categoryStr != null ? categoryStr : "N/A");
        Glide.with(this).load(serviceImgStr).into(serviceImage);

        // Initialize DatePicker and Spinner for booking
        datePicker = findViewById(R.id.datePicker);
        timeSlotSpinner = findViewById(R.id.timeSlotSpinner);
        bookButton = findViewById(R.id.bookButton);

        // Define time slots
        String[] timeSlots = {"09:00 AM", "10:00 AM", "11:00 AM", "01:00 PM", "02:00 PM", "03:00 PM", "04:00 PM"};

        // Set up adapter for spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, timeSlots);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSlotSpinner.setAdapter(adapter);

        // Set listener for the booking button
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedTime = timeSlotSpinner.getSelectedItem().toString();
                //Toast.makeText(BookingActivity.this, "Booking confirmed for " + selectedTime + " on "
                  //      + datePicker.getDayOfMonth() + "/" + (datePicker.getMonth() + 1) + "/" + datePicker.getYear(), Toast.LENGTH_LONG).show();

                Intent intent=new Intent(getApplicationContext(), Final_Booking__details_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}