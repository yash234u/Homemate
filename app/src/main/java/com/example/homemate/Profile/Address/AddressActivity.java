package com.example.homemate.Profile.Address;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import com.example.homemate.Categories.Model;
import com.example.homemate.Profile.UserModel;
import com.example.homemate.R;
import com.firebase.ui.auth.data.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddressActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    ArrayList<UserModel> list;
    AddressAdapter addressAdapter;
    String data_email;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        data_email= PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Email_from_login","").replace(".",",");
        floatingActionButton=findViewById(R.id.floatingActionButton);
        recyclerView=findViewById(R.id.rv_address);
        databaseReference= FirebaseDatabase.getInstance().getReference("UserDetails").child(data_email).child("SavedAddress");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        addressAdapter=new AddressAdapter(this,list);
        recyclerView.setAdapter(addressAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    UserModel model=dataSnapshot.getValue(UserModel.class);
                    list.add(model);
                }
                addressAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), AddAdressActivity.class);
                startActivity(intent);
            }
        });

    }
}