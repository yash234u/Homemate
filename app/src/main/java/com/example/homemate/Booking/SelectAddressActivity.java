package com.example.homemate.Booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import com.example.homemate.Profile.Address.AddAdressActivity;
import com.example.homemate.Profile.Address.AddressAdapter;
import com.example.homemate.Profile.UserModel;
import com.example.homemate.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SelectAddressActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    ArrayList<UserModel> list;
    SelectAddressAdapter selectaddressAdapter;
    String data_email;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_address);
        data_email= PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Email_from_login","").replace(".",",");
        recyclerView=findViewById(R.id.rv_select_address);

        databaseReference= FirebaseDatabase.getInstance().getReference("SavedAddress").child(data_email);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        selectaddressAdapter=new SelectAddressAdapter(this,list);
        recyclerView.setAdapter(selectaddressAdapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    UserModel model=dataSnapshot.getValue(UserModel.class);
                    list.add(model);
                }
                selectaddressAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}