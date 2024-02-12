package com.example.homemate.Categories;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.homemate.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PlumbingActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Model> servicesList;
    DatabaseReference databaseReference;
    MainAdapter mainAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plumbing);

        recyclerView=(RecyclerView) findViewById(R.id.rv_plumbing);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseReference= FirebaseDatabase.getInstance().getReference("Categories").child("Plumbing");

        servicesList=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainAdapter=new MainAdapter(this,servicesList);
        recyclerView.setAdapter(mainAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    Model model=dataSnapshot.getValue(Model.class);
                    servicesList.add(model);
                }
                mainAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}