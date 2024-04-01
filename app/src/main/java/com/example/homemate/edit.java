package com.example.homemate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class edit extends AppCompatActivity {
    TextInputLayout fullname , Username , email , contact , Gender;
    Button Editprofile;

    FirebaseDatabase rootnode;
    DatabaseReference reference;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        fullname = findViewById(R.id.txtname);
        Username = findViewById(R.id.txtusername);
        email = findViewById(com.firebase.ui.auth.R.id.email_layout);
        contact = findViewById(R.id.txtcontact);
        Gender = findViewById(R.id.GenderRadio);
        Editprofile = findViewById(com.firebase.ui.auth.R.id.button);

        Editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               rootnode=  FirebaseDatabase.getInstance();
               reference = rootnode.getReference("My Account");

               //Get all the values
                String minnieFaisal = fullname.getEditText().getText().toString();
                String Female =    Gender.getEditText().getText().toString();
               UserHelperClass helperClass= new UserHelperClass();

               reference.setValue("Edit my Account");

            }
        });
    }
}
