package com.example.homemate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homemate.Profile.AboutUsctivity;
import com.example.homemate.Profile.Address.AddressActivity;
import com.example.homemate.Profile.MyAccountActivity;
import com.example.homemate.Profile.SettingsActivity;
import com.example.homemate.Profile.TipsandTricksActivity;
import com.example.homemate.splash.SplashActivity_2;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment{
    private Button logout;
    private ImageView imageView;
    TextView username,email;
    String data_email;
    DatabaseReference databaseReference;
    ConstraintLayout my_account,address_profile,settings_profile,Tipsandtricks_Profile,AboutUs_Profile;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

      logout = (Button) view.findViewById(R.id.logout);
      imageView=view.findViewById(R.id.profile_image);
      email=view.findViewById(R.id.profilepage_email);
      username=view.findViewById(R.id.profilepage_username);
      my_account=view.findViewById(R.id.My_Account_Profile);
      address_profile=view.findViewById(R.id.Address_Profile);
      settings_profile=view.findViewById(R.id.Settings_Profile);
      Tipsandtricks_Profile=view.findViewById(R.id.Tipsandtricks_Profile);
      AboutUs_Profile=view.findViewById(R.id.AboutUs_Profile);

      data_email= PreferenceManager.getDefaultSharedPreferences(getContext()).getString("Email_from_login","").replace(".",",");
      email.setText(data_email.toString().replace(",","."));
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        DatabaseReference getImage = databaseReference.
                child("UserDetails").child(data_email).child("Profile");

        my_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), MyAccountActivity.class);
                startActivity(intent);
            }
        });
        address_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), AddressActivity.class);
                startActivity(intent);
            }
        });
        settings_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });
        Tipsandtricks_Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), TipsandTricksActivity.class);
                startActivity(intent);
            }
        });
        AboutUs_Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), AboutUsctivity.class);
                startActivity(intent);
            }
        });
        getImage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String link = dataSnapshot.getValue(String.class);
                Picasso.get().load(link).into(imageView);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Error Loading Image", Toast.LENGTH_SHORT).show();
            }
        });

        databaseReference.child("UserDetails").addValueEventListener(new ValueEventListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(data_email)){
                username.setText(snapshot.child(data_email).child("UserName").getValue().toString());}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(getContext(), SplashActivity_2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        return view;
    }
}