package com.example.homemate.Profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homemate.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class MyAccountActivity extends AppCompatActivity {

    private TextView username;
    private TextView Fullname;
    private TextView Email;
    private TextView Contact;
    private TextView Gender;
    private ImageView account_image;
    private FirebaseDatabase database;
    private DatabaseReference userRef;

    private static final String USER ="users";
    String data_email;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        username = findViewById(R.id.textView5);
        Fullname = findViewById(R.id.textView3);
        Email = findViewById(R.id.textView7);
        Contact = findViewById(R.id.textView9);
        Gender = findViewById(R.id.textView11);
        account_image=findViewById(R.id.account_image);

        data_email= PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Email_from_login","").replace(".",",");
        database = FirebaseDatabase.getInstance();
        userRef= database.getReference();
        DatabaseReference getImage = userRef.child("UserDetails").child(data_email).child("Profile");

        getImage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String link = dataSnapshot.getValue(String.class);
                Picasso.get().load(link).into(account_image);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Error Loading Image", Toast.LENGTH_SHORT).show();
            }
        });
        userRef.child("UserDetails").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(data_email)){
                    username.setText(snapshot.child(data_email).child("UserName").getValue().toString());
                    Fullname.setText(snapshot.child(data_email).child("FullName").getValue().toString());
                    Email.setText(data_email.replace(",","."));
                    Gender.setText(snapshot.child(data_email).child("Gender").getValue().toString());
                    Contact.setText(snapshot.child(data_email).child("Contact").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        }
    }


