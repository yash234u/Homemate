package com.example.homemate.Profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.example.homemate.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyAccountActivity extends AppCompatActivity {

    private TextView username;
    private TextView Fullname;
    private TextView Email;
    private TextView Contact;
    private TextView Gender;
    private FirebaseDatabase database;
    private DatabaseReference userRef;

    private static final String USER ="users";
    String email;






    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        username = findViewById(R.id.textView5);
        Fullname = findViewById(R.id.textView2);
        Email = findViewById(R.id.textView7);
        Contact = findViewById(R.id.textView9);
        Gender = findViewById(R.id.textView11);

        database = FirebaseDatabase.getInstance();
        userRef= database.getReference(USER);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.child("email").getValue().equals(email)) {
                        username.setText(ds.child("UserName").getValue(String.class));
                        Fullname.setText(ds.child("FullName").getValue(String.class));
                        Email.setText("Email");
                        Contact.setText(ds.child("Contact").getValue(String.class));
                        Gender.setText(ds.child("Gender").getValue(String.class));

                    }

                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {





        }
    }


