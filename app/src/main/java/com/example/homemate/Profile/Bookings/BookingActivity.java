package com.example.homemate.Profile.Bookings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.preference.PreferenceManager;

import com.example.homemate.Profile.Address.AddressAdapter;
import com.example.homemate.Profile.UserModel;
import com.example.homemate.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BookingActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    ArrayList<Model> list;
    BookingAdapter bookingAdapter;
    String data_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking2);

        data_email= PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Email_from_login","").replace(".",",");
        recyclerView=findViewById(R.id.rv_booking);
        databaseReference= FirebaseDatabase.getInstance().getReference("BookingDetails").child(data_email);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        bookingAdapter=new BookingAdapter(this,list);
        recyclerView.setAdapter(bookingAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //list.clear();  // Clear the list to avoid duplicate entries

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Model model = dataSnapshot.getValue(Model.class);
                    list.add(model);
                    // Check if the model has all necessary non-empty fields

                }
                bookingAdapter.notifyDataSetChanged();  // Notify the adapter of changes
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}