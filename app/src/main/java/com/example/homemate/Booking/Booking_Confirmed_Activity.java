package com.example.homemate.Booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.homemate.Profile.Bookings.BookingActivity;
import com.example.homemate.R;

public class Booking_Confirmed_Activity extends AppCompatActivity {

    private TextView name;
    private LottieAnimationView lottie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirmed);

        name=findViewById(R.id.Done_booking_text);
        lottie=findViewById(R.id.Done_booking_file);

        name.animate().translationY(-500).setDuration(2700).setStartDelay(0);
        lottie.animate().translationY(2000).setDuration(4000).setStartDelay(2900);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(getApplicationContext(), BookingActivity.class);
                startActivity(intent);
                finish();
            }
        },7000);
    }
}