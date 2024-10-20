package com.example.homemate.Booking;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.homemate.R;

public class Final_Booking__details_Activity extends AppCompatActivity {

    private Button Booking_btn;
    private TextView SelectedAddress,ServiceProvider,ServiceSelected,ServiceProviderContact,ServicePriceitemtotal,ServicePriceToPay,
            ServiceTime,ServiceDate;
    String serviceprovider,servicename,serviceprovidercontact,serviceprice,servicetime,servicedate;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_booking_details);

        SelectedAddress=findViewById(R.id.Selected_Address_label);
        ServiceSelected=findViewById(R.id.service_provider_service_text);
        ServicePriceitemtotal=findViewById(R.id.item_total_price);
        ServiceProvider=findViewById(R.id.service_provider_provider_text);
        ServiceProviderContact=findViewById(R.id.service_provider_contact_text);
        ServicePriceToPay=findViewById(R.id.to_pay_price);
        ServiceTime=findViewById(R.id.service_provider_time_text);
        ServiceDate=findViewById(R.id.service_provider_date_text);

        Booking_btn=findViewById(R.id.Booking_confirm_btn);
        Intent intent = getIntent();
        String Selected_Address_intent = intent.getStringExtra("selectedaddress");

        SelectedAddress.setText(Selected_Address_intent);

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPrefs_BookingActivity", MODE_PRIVATE);
        serviceprovider = sharedPreferences.getString("selected_sp_name_from_booking_activity", "");
        serviceprovidercontact = sharedPreferences.getString("selected_sp_contact_from_booking_activity", "");
        servicetime= sharedPreferences.getString("selected_time_from_booking_activity","");
        servicedate=sharedPreferences.getString("selected_date_from_booking_activity","");

        SharedPreferences sharedPreferences2 = getSharedPreferences("MySharedPrefs_FullViewActivity", MODE_PRIVATE);
        servicename=sharedPreferences2.getString("selected_service_name_from_FullView_activity","");
        serviceprice=sharedPreferences2.getString("selected_service_price_from_FullView_activity","");

        ServiceSelected.setText(servicename);
        ServicePriceitemtotal.setText("₹"+serviceprice);
        ServiceProvider.setText(serviceprovider);
        ServiceProviderContact.setText(serviceprovidercontact);
        ServicePriceToPay.setText("₹"+serviceprice);
        ServiceTime.setText(servicetime);
        ServiceDate.setText(servicedate);
        Booking_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Booking_Confirmed_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}