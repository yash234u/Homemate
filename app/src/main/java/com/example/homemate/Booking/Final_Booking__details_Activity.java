package com.example.homemate.Booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homemate.Profile.Address.AddAdressActivity;
import com.example.homemate.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Final_Booking__details_Activity extends AppCompatActivity {

    private Button Booking_btn;
    private TextView SelectedAddress,ServiceProvider,ServiceSelected,ServiceProviderContact,ServicePriceitemtotal,ServicePriceToPay,
            Instructions,ServiceTime,ServiceDate;
    String serviceprovider,servicename,serviceprovidercontact,serviceprice,servicetime,servicedate,data_email;
    DatabaseReference databaseReference;
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
        Instructions=findViewById(R.id.add_instruction);

        databaseReference= FirebaseDatabase.getInstance().getReference();
        String key=databaseReference.child("BookingDetails").push().getKey();
        data_email= PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Email_from_login","").replace(".",",");

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
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child("BookingDetails").child(data_email).child(key).child("sp_name").setValue(serviceprovider);
                        databaseReference.child("BookingDetails").child(data_email).child(key).child("sp_contact").setValue(serviceprovidercontact);
                        databaseReference.child("BookingDetails").child(data_email).child(key).child("s_category").setValue(servicename);
                        databaseReference.child("BookingDetails").child(data_email).child(key).child("user_address").setValue(SelectedAddress.getText().toString());
                        databaseReference.child("BookingDetails").child(data_email).child(key).child("date").setValue(servicedate);
                        databaseReference.child("BookingDetails").child(data_email).child(key).child("time").setValue(servicetime);
                        databaseReference.child("BookingDetails").child(data_email).child(key).child("price").setValue(serviceprice);
                        databaseReference.child("BookingDetails").child(data_email).child(key).child("instructions").setValue(Instructions.getText().toString());
                        databaseReference.child("BookingDetails").child(data_email).child(key).child("status").setValue("ongoing");
                        Toast.makeText(Final_Booking__details_Activity.this, "Booking successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                Intent intent=new Intent(getApplicationContext(), Booking_Confirmed_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}