package com.example.homemate.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.VideoView;

import com.example.homemate.R;
import com.example.homemate.walkthrough.OnboardingActivity;

public class SplashActivity extends AppCompatActivity {

    private VideoView videoView;
    SharedPreferences onBoardingScreen;
    Handler h=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        videoView=findViewById(R.id.splash);
        String uriPath = "android.resource://" + getPackageName() + "/" + R.raw.splash;
        Uri uri = Uri.parse(uriPath);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                onBoardingScreen=getSharedPreferences("onBoardingScreen",MODE_PRIVATE);
                boolean isFirstTime=onBoardingScreen.getBoolean("firstTime",true);
                if(isFirstTime){
                    SharedPreferences.Editor editor=onBoardingScreen.edit();
                    editor.putBoolean("firstTime",false);
                    editor.commit();
                    startActivity(new Intent(SplashActivity.this, OnboardingActivity.class));
                    finish();
                }else {
                    startActivity(new Intent(SplashActivity.this, SplashActivity_2.class));
                    finish();
                }

            }
        },3000);

    }
}