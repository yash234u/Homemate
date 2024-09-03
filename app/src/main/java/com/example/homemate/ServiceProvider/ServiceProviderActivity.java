package com.example.homemate.ServiceProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.homemate.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class ServiceProviderActivity extends AppCompatActivity {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    ArrayList<SP_Model> serviceProviderList;
    DatabaseReference databaseReference;
    SP_Adapter spAdapter;
    String serviceCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider);

        progressBar = findViewById(R.id.progress_bar_sp);
        recyclerView = findViewById(R.id.recycler_view_sp);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        serviceProviderList = new ArrayList<>();
        spAdapter = new SP_Adapter(this, serviceProviderList);
        recyclerView.setAdapter(spAdapter);

        // Retrieve service details and category from intent
        Intent intent = getIntent();
        String serviceName = intent.getStringExtra("servicename");
        String serviceDesc = intent.getStringExtra("servicedesc");
        String servicePrice = intent.getStringExtra("serviceprice");
        String serviceImage = intent.getStringExtra("serviceimage");
        String serviceInclude = intent.getStringExtra("serviceinclude");
        String serviceExclude = intent.getStringExtra("serviceexclude");
        String category = intent.getStringExtra("category");

        // Initialize adapter with all service details
        spAdapter = new SP_Adapter(this, serviceProviderList, serviceName, serviceDesc, servicePrice, serviceImage, serviceInclude, serviceExclude, category);
        recyclerView.setAdapter(spAdapter);

        // Receive service category from FullViewActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("serviceCategory")) {
            serviceCategory = extras.getString("serviceCategory");
            if (serviceCategory != null) {
                // Construct the correct database reference based on serviceCategory
                databaseReference = FirebaseDatabase.getInstance().getReference("ServiceProvider")
                        .child(getServiceProviderNode(serviceCategory));

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        serviceProviderList.clear(); // Clear the list before adding new data
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            SP_Model spModel = dataSnapshot.getValue(SP_Model.class);
                            serviceProviderList.add(spModel);
                        }
                        spAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        progressBar.setVisibility(View.GONE);
                        Log.e("Firebase", "Error retrieving data: " + error.getMessage());
                    }
                });
            }
        }
    }

    // Method to determine the correct service provider node based on service category
    private String getServiceProviderNode(String category) {
        switch (category) {
            case "AC":
                return "AC_service_provider";
            case "Plumbing":
                return "Plumbing_service_provider";
            case "Electricians":
                return "Electricians_service_provider";
            case "Carpenter":
                return "Carpenter_service_provider";
            case "Appliance":
                return "Appliance_service_provider";
            default:
                return "";
        }
    }
}
