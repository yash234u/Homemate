package com.example.homemate.Categories;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.homemate.R;

import org.w3c.dom.Text;

public class FullViewActivity extends AppCompatActivity {

    TextView fullviewdescname,fullviewpricename,fullviewservicename,includeservice,excludeservice;
    ImageView fullviewimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_view);

        fullviewdescname=(TextView) findViewById(R.id.fullviewdescname);
        fullviewservicename=(TextView) findViewById(R.id.fullviewservicename);
        fullviewpricename=(TextView) findViewById(R.id.fullviewpricename);
        fullviewimage=(ImageView) findViewById(R.id.fullviewimage);
        includeservice=(TextView) findViewById(R.id.included_in_service);
        excludeservice=(TextView) findViewById(R.id.excluded_in_service);

        fullviewservicename.setText(getIntent().getStringExtra("servicename"));
        fullviewdescname.setText(getIntent().getStringExtra("servicedesc"));
        fullviewpricename.setText(" ₹ "+getIntent().getStringExtra("serviceprice"));
        Glide.with(getApplicationContext()).load(getIntent().getStringExtra("serviceimage")).into(fullviewimage);
        includeservice.setText(getIntent().getStringExtra("serviceinclude"));
        excludeservice.setText(getIntent().getStringExtra("serviceexclude"));


    }
}