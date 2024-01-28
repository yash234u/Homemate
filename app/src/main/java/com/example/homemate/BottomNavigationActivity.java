package com.example.homemate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        frameLayout=findViewById(R.id.FrameLayoutForNav);
        bottomNavigationView=findViewById(R.id.bottomNav);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemID=item.getItemId();

                if(itemID==R.id.navHome){
                    LoadFragment(new HomeFragment(),false);
                    
                }else if(itemID==R.id.navCategories){
                    LoadFragment(new CategoriesFragment(),false);
                    
                } else if (itemID==R.id.navProfile) {
                    LoadFragment(new ProfileFragment(),false);
                    
                }

                return true;
            }
        });
        LoadFragment(new HomeFragment(),true);
    }

    private void LoadFragment(Fragment fragment,boolean isAppInitialized)
    {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        if (isAppInitialized){
            fragmentTransaction.add(R.id.FrameLayoutForNav,fragment);
        }
        else {
            fragmentTransaction.replace(R.id.FrameLayoutForNav,fragment);
        }
        fragmentTransaction.commit();
    }
}