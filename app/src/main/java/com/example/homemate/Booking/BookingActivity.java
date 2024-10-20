package com.example.homemate.Booking;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class BookingActivity extends AppCompatActivity {

    TextView spName, spContact, spExperience, spServices, serviceName, serviceDesc, servicePrice, serviceInclude, serviceExclude, category;
    DatePickerDialog datePickerDialog;
    private TextInputEditText getDate;
    private Spinner timeSlotSpinner;
    private Button bookButton;
    public String timeselected,selectedDate;
    int day,month,year;

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
        getDate = findViewById(R.id.Date_Selector);
        getDate.setInputType(InputType.TYPE_NULL);
        timeSlotSpinner = findViewById(R.id.timeSlotSpinner);
        bookButton = findViewById(R.id.bookButton);

        getDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar=Calendar.getInstance();
                day=calendar.get(Calendar.DAY_OF_MONTH);
                month=calendar.get(Calendar.MONTH);
                year=calendar.get(Calendar.YEAR);

                 datePickerDialog=new DatePickerDialog(BookingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int day, int month, int year) {
                        selectedDate= day+"/"+(month+1)+"/"+year;
                        getDate.setText(selectedDate);
                    }
                },year,month,day);
                 datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000    );
                datePickerDialog.show();
            }
        });
        // Display or process the selected date

        Toast.makeText(BookingActivity.this, "Selected Date: " + selectedDate, Toast.LENGTH_LONG).show();

        // Define time slots
        String[] timeSlots = {"09:00 AM", "10:00 AM", "11:00 AM", "01:00 PM", "02:00 PM", "03:00 PM", "04:00 PM"};

        // Set up adapter for spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, timeSlots);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSlotSpinner.setAdapter(adapter);
        timeSlotSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                timeselected=adapterView.getItemAtPosition(i).toString();
                Toast.makeText(BookingActivity.this, "Selected: " + timeselected, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(BookingActivity.this,"Please Select Time",Toast.LENGTH_SHORT).show();
            }
        });


        // Set listener for the booking button
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String selectedTime = timeSlotSpinner.getSelectedItem().toString();
                //Toast.makeText(BookingActivity.this, "Booking confirmed for " + selectedTime + " on "
                  //      + datePicker.getDayOfMonth() + "/" + (datePicker.getMonth() + 1) + "/" + datePicker.getYear(), Toast.LENGTH_LONG).show();

                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPrefs_BookingActivity", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("selected_sp_name_from_booking_activity",name);
                editor.putString("selected_sp_contact_from_booking_activity",contact);
                editor.putString("selected_time_from_booking_activity",timeselected);
                editor.putString("selected_date_from_booking_activity",selectedDate);
                editor.apply();
                Intent intent=new Intent(getApplicationContext(), SelectAddressActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}