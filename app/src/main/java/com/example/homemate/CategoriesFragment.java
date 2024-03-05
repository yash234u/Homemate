package com.example.homemate;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.homemate.Categories.AC.ACActivity;
import com.example.homemate.Categories.Appliance.ApplianceActivity;
import com.example.homemate.Categories.Carpenter.CarpenterActivity;
import com.example.homemate.Categories.Electricians.ElectriciansActivity;
import com.example.homemate.Categories.Plumbing.PlumbingActivity;

public class CategoriesFragment extends Fragment {

    CardView plumbing;
    CardView electricians;
    CardView AC_services_repair;
    CardView carpenter;
    CardView Appliance;
        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_categories, container, false);

        plumbing= (CardView) view.findViewById(R.id.Plumbing_categories);
        electricians=(CardView) view.findViewById(R.id.Electricians_categories);
        AC_services_repair=(CardView)view.findViewById(R.id.AC_services_repair_categories);
        carpenter=(CardView)view.findViewById(R.id.Carpenters_categories);
        Appliance=(CardView) view.findViewById(R.id.Appliance_services_repair_categories);
        plumbing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), PlumbingActivity.class);
                startActivity(intent);
            }
        });
        electricians.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ElectriciansActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
            AC_services_repair.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(getContext(), ACActivity.class);
                    //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });
            carpenter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(getContext(), CarpenterActivity.class);
                    //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });
            Appliance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(getContext(), ApplianceActivity.class);
                    //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });
            return view;
        }
}