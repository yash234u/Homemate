package com.example.homemate.Booking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

        // Initialize views for Service details
        serviceName = findViewById(R.id.serviceNameTextView);
        servicePrice = findViewById(R.id.servicePriceTextView);
        category = findViewById(R.id.categoryTextView);

        // Retrieve Service Provider data from Intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("sp_name");
        String contact = intent.getStringExtra("sp_contact");

        // Retrieve Service data from Intent
        String serviceNameStr = intent.getStringExtra("servicename");
        String servicePriceStr = intent.getStringExtra("serviceprice");
        String categoryStr = intent.getStringExtra("category");

        // Set data to views for Service Provider
        spName.setText(name != null ? name : "N/A");
        spContact.setText(contact != null ? contact : "N/A");

        // Set data to views for Service details
        serviceName.setText(serviceNameStr != null ? serviceNameStr : "N/A");
        servicePrice.setText(servicePriceStr != null ? servicePriceStr : "N/A");
        category.setText(categoryStr != null ? categoryStr : "N/A");

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

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPrefs_BookingActivity", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("selected_sp_name_from_booking_activity",name);
        editor.putString("selected_sp_contact_from_booking_activity",contact);
        editor.apply();

        // Set listener for the booking button
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedTime = timeSlotSpinner.getSelectedItem().toString();
                //Toast.makeText(BookingActivity.this, "Booking confirmed for " + selectedTime + " on "
                  //      + datePicker.getDayOfMonth() + "/" + (datePicker.getMonth() + 1) + "/" + datePicker.getYear(), Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getApplicationContext(), SelectAddressActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}