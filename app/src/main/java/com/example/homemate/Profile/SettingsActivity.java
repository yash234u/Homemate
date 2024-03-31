package com.example.homemate.Profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import com.example.homemate.LoadingDailog;
import com.example.homemate.R;
import com.example.homemate.splash.SplashActivity_2;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SettingsActivity extends AppCompatActivity {

    String data_email;
    ConstraintLayout delete_account;
    LoadingDailog loadingDailog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        delete_account=findViewById(R.id.Delete_Account);
        loadingDailog=new LoadingDailog(SettingsActivity.this);
        data_email= PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Email_from_login","").replace(".",",");
        delete_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doDeleteUser();
            }
        });
    }

    private void doDeleteUser() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
        builder.setTitle("Alert!")
                        .setMessage("Do you really want to delete your account")
                        .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        loadingDailog.startDialog();
                                       FirebaseDatabase.getInstance().getReference()
                                                .child("UserDetails")
                                                .child(data_email)
                                                .setValue(null)
                                               .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                   @Override
                                                   public void onSuccess(Void unused) {
                                                       FirebaseAuth.getInstance().getCurrentUser().delete()
                                                               .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                   @Override
                                                                   public void onComplete(@NonNull Task<Void> task) {
                                                                       if (task.isSuccessful()){
                                                                           loadingDailog.dismissDiaLog();
                                                                           Intent intent=new Intent(SettingsActivity.this, SplashActivity_2.class);
                                                                           startActivity(intent);
                                                                           finish();
                                                                       }
                                                                   }
                                                               });
                                                   }
                                               });
                                    }
                                })
                                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}