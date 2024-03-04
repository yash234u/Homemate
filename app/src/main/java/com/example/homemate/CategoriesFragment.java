package com.example.homemate;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.homemate.Categories.Plumbing.PlumbingActivity;

public class CategoriesFragment extends Fragment {

    CardView plumbing;
        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_categories, container, false);

        plumbing= (CardView) view.findViewById(R.id.Plumbing_categories);

        plumbing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), PlumbingActivity.class);
                startActivity(intent);
            }
        });
            return view;
        }
}