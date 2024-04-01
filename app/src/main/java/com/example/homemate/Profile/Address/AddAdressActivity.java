package com.example.homemate.Profile.Address;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.homemate.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddAdressActivity extends AppCompatActivity {

    EditText add_address;
    Button savebtn;
    String data_email;
    DatabaseReference databaseReference;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_adress);

        add_address=findViewById(R.id.editText_address_add);
        savebtn=findViewById(R.id.save_button);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        String key=databaseReference.child("SavedAddress").push().getKey();
        data_email= PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Email_from_login","").replace(".",",");

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child("SavedAddress").child(data_email).
                                child(key).child("address").setValue(add_address.getText().toString());
                        Toast.makeText(AddAdressActivity.this, "Saved successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(AddAdressActivity.this, "Error in saving address", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}