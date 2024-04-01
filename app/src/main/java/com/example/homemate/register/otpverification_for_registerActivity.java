package com.example.homemate.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homemate.BottomNavigationActivity;
import com.example.homemate.R;
import com.example.homemate.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.concurrent.TimeUnit;

public class otpverification_for_registerActivity extends AppCompatActivity {

    private EditText inputOtp1, inputOtp2, inputOtp3, inputOtp4, inputOtp5, inputOtp6;
    private Button verifyBtn;
    private ProgressBar progressBar;
    private String getotpfrombackend;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private TextView resend;
    private FirebaseAuth mAuth;
    private Uri filePath_upload;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification_for_register);

        inputOtp1 = findViewById(R.id.otp1_register);
        inputOtp2 = findViewById(R.id.otp2_register);
        inputOtp3 = findViewById(R.id.otp3_register);
        inputOtp4 = findViewById(R.id.otp4_register);
        inputOtp5 = findViewById(R.id.otp5_register);
        inputOtp6 = findViewById(R.id.otp6_register);

        verifyBtn = findViewById(R.id.otp_verify_register);
        progressBar = findViewById(R.id.progressbarforotp_register);
        resend = findViewById(R.id.Resendotpbutton_register);

        databaseReference = FirebaseDatabase.getInstance().getReference("UserDetails");
        storageReference = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        filePath_upload=getIntent().getParcelableExtra("profile_img_register");

        TextView textView = findViewById(R.id.mobilenumber_register);
        textView.setText(String.format(
                "+91-%s", getIntent().getStringExtra("mobile_register")
        ));

        getotpfrombackend = getIntent().getStringExtra("backendotp");

        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!inputOtp1.getText().toString().trim().isEmpty() && !inputOtp2.getText().toString().trim().isEmpty() &&
                        !inputOtp3.getText().toString().trim().isEmpty() && !inputOtp4.getText().toString().trim().isEmpty() &&
                        !inputOtp5.getText().toString().trim().isEmpty() && !inputOtp6.getText().toString().trim().isEmpty()) {

                    String enteredotp = inputOtp1.getText().toString() +
                            inputOtp2.getText().toString() +
                            inputOtp3.getText().toString() +
                            inputOtp4.getText().toString() +
                            inputOtp5.getText().toString() +
                            inputOtp6.getText().toString();

                    if (getotpfrombackend != null) {
                        progressBar.setVisibility(View.VISIBLE);
                        verifyBtn.setVisibility(View.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                                getotpfrombackend, enteredotp
                        );
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressBar.setVisibility(View.GONE);
                                        verifyBtn.setVisibility(View.VISIBLE);

                                        if (task.isSuccessful()) {
                                            mAuth.createUserWithEmailAndPassword(getIntent().getStringExtra("Email_register").replace(",","."),
                                                            getIntent().getStringExtra("password_register"))
                                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                                            if (task.isSuccessful()) {
                                                                Toast.makeText(otpverification_for_registerActivity.this, "Authentication successful.",
                                                                        Toast.LENGTH_SHORT).show();
                                                            } else {
                                                                Toast.makeText(otpverification_for_registerActivity.this, "Authentication failed.",
                                                                        Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    });

                                            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    databaseReference.child(getIntent().getStringExtra("Email_register"))
                                                            .child("FullName").setValue(getIntent().getStringExtra("fullname_register"));
                                                    databaseReference.child(getIntent().getStringExtra("Email_register"))
                                                            .child("UserName").setValue(getIntent().getStringExtra("username_register"));
                                                    databaseReference.child(getIntent().getStringExtra("Email_register"))
                                                            .child("Gender").setValue(getIntent().getStringExtra("gender_register"));
                                                    databaseReference.child(getIntent().getStringExtra("Email_register"))
                                                            .child("Email").setValue(getIntent().getStringExtra("Email_register"));
                                                    databaseReference.child(getIntent().getStringExtra("Email_register"))
                                                            .child("Contact").setValue(getIntent().getStringExtra("mobile_register"));
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {
                                                    Toast.makeText(otpverification_for_registerActivity.this, "Error Connecting to Database", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                            Toast.makeText(otpverification_for_registerActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(otpverification_for_registerActivity.this, "Enter the correct otp", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                    } else {
                        Toast.makeText(otpverification_for_registerActivity.this, "Please check internet connection", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(otpverification_for_registerActivity.this, "Please enter otp", Toast.LENGTH_SHORT).show();
                }
            }
        });

        numberotpmove();

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra("mobile_register"),
                        60, TimeUnit.SECONDS,
                        otpverification_for_registerActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(otpverification_for_registerActivity.this, "Code not Send", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(s, forceResendingToken);
                                getotpfrombackend=s;
                                Toast.makeText(otpverification_for_registerActivity.this, "Code sent", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        });
    }

    private void numberotpmove() {

        inputOtp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    inputOtp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        inputOtp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    inputOtp3.requestFocus();
                } else {
                    inputOtp1.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        inputOtp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    inputOtp4.requestFocus();
                } else {
                    inputOtp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        inputOtp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    inputOtp5.requestFocus();
                } else {
                    inputOtp3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        inputOtp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    inputOtp6.requestFocus();
                } else {
                    inputOtp4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        inputOtp6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().isEmpty()) {
                    inputOtp5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }
}