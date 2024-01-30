package com.example.homemate.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homemate.BottomNavigationActivity;
import com.example.homemate.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private Button Login_btn;
    private TextInputLayout email;
    private TextInputLayout password;
    private ProgressBar progressBar;
    private TextView forgotpassword;
    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(getApplicationContext(), BottomNavigationActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email_login);
        password = findViewById(R.id.password_login);
        Login_btn = (Button) findViewById(R.id.LoginButton);
        progressBar = findViewById(R.id.progressbar_send);
        forgotpassword=findViewById(R.id.forgot_password);
        mAuth = FirebaseAuth.getInstance();

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);
                Login_btn.setVisibility(View.INVISIBLE);
                String txtemail=email.getEditText().getText().toString();
                String txtpassword=password.getEditText().getText().toString();
                if(txtemail.isEmpty() | txtpassword.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please enter all details", Toast.LENGTH_SHORT).show();
                }else {
                    mAuth.signInWithEmailAndPassword(txtemail, txtpassword)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    Login_btn.setVisibility(View.VISIBLE);
                                    if (task.isSuccessful()) {
                                    Intent intent=new Intent(getApplicationContext(), BottomNavigationActivity.class);
                                    startActivity(intent);
                                    finish();
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }

            }
        });


    }


}