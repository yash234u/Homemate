package com.example.homemate.Categories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.homemate.R;
import com.example.homemate.ServiceProvider.ServiceProviderActivity;

public class FullViewActivity extends AppCompatActivity {

    TextView fullviewdescname, fullviewpricename, fullviewservicename, includeservice, excludeservice;
    ImageView fullviewimage;
    Button bookButton;
    private String Price_to_send_for_booking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_view);

        fullviewdescname = findViewById(R.id.fullviewdescname);
        fullviewservicename = findViewById(R.id.fullviewservicename);
        fullviewpricename = findViewById(R.id.fullviewpricename);
        fullviewimage = findViewById(R.id.fullviewimage);
        includeservice = findViewById(R.id.included_in_service);
        excludeservice = findViewById(R.id.excluded_in_service);
        bookButton = findViewById(R.id.book_button);

        // Retrieve service details and category from intent
        Intent intent = getIntent();
        String serviceName = intent.getStringExtra("servicename");
        String serviceDesc = intent.getStringExtra("servicedesc");
        String servicePrice = intent.getStringExtra("serviceprice");
        String serviceImage = intent.getStringExtra("serviceimage");
        String serviceInclude = intent.getStringExtra("serviceinclude");
        String serviceExclude = intent.getStringExtra("serviceexclude");
        String category = intent.getStringExtra("category");

        // Set service details in UI
        fullviewservicename.setText(serviceName);
        fullviewdescname.setText(serviceDesc);
        fullviewpricename.setText("₹ " + servicePrice);
        Glide.with(getApplicationContext()).load(serviceImage).into(fullviewimage);
        includeservice.setText(serviceInclude);
        excludeservice.setText(serviceExclude);

        // Handle "Book" button click
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FullViewActivity.this, ServiceProviderActivity.class);
                intent.putExtra("serviceCategory", category); // Pass the selected service category

                // Pass all service details to ServiceProviderActivity
                intent.putExtra("servicename", serviceName);
                intent.putExtra("servicedesc", serviceDesc);
                intent.putExtra("serviceprice", servicePrice);
                intent.putExtra("serviceimage", serviceImage);
                intent.putExtra("serviceinclude", serviceInclude);
                intent.putExtra("serviceexclude", serviceExclude);

                startActivity(intent);
            }
        });

    }
}
