package com.example.homemate.Categories.Plumbing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.homemate.BottomNavigationActivity;
import com.example.homemate.Categories.MainAdapter;
import com.example.homemate.Categories.Model;
import com.example.homemate.CategoriesFragment;
import com.example.homemate.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PlumbingActivity extends AppCompatActivity {

    ImageView backbtn;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    ArrayList<Model> servicesList;
    DatabaseReference databaseReference;
    MainAdapter mainAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plumbing);

        backbtn=findViewById(R.id.back_button_service);
        progressBar=(ProgressBar) findViewById(R.id.progress_bar_service);
        recyclerView=(RecyclerView) findViewById(R.id.rv_plumbing);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseReference= FirebaseDatabase.getInstance().getReference("Categories").child("Plumbing");

        servicesList=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainAdapter=new MainAdapter(this,servicesList);
        recyclerView.setAdapter(mainAdapter);

        progressBar.setVisibility(View.VISIBLE);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    Model model=dataSnapshot.getValue(Model.class);
                    servicesList.add(model);
                    progressBar.setVisibility(View.GONE);
                }
                mainAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), BottomNavigationActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}