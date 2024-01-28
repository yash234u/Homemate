package com.example.homemate.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.homemate.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    private Button btnreset;
    private ProgressBar progressBar;
    private EditText email;
    private FirebaseAuth firebaseAuth;
    private String txtemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        btnreset=findViewById(R.id.btnReset);
        progressBar=findViewById(R.id.forgetPasswordProgressbar);
        email=findViewById(R.id.edtForgotPasswordEmail);
        firebaseAuth=FirebaseAuth.getInstance();

        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtemail=email.getText().toString().trim();
                if(txtemail.isEmpty()){
                    Toast.makeText(ForgotPasswordActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
                } else {
                    ResetPassword();
                }
            }
        });
    }

    private void ResetPassword() {
        progressBar.setVisibility(View.VISIBLE);
        btnreset.setVisibility(View.INVISIBLE);

        firebaseAuth.sendPasswordResetEmail(txtemail)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(ForgotPasswordActivity.this, "Link Sent", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ForgotPasswordActivity.this, "Error in sending link", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        btnreset.setVisibility(View.INVISIBLE);
                    }
                });
    }
}