package com.gobernanza.pruebalogin;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.gobernanza.pruebalogin.databinding.ActivityHome2Binding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity2 extends AppCompatActivity  {


    private ActivityHome2Binding binding;
    private String Emaill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if(intent != null){
             Emaill = intent.getStringExtra("correoEmail");
        }


        binding = ActivityHome2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_home2);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);




        navView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Bundle homeBundle = new Bundle();
                    homeBundle.putString("correoEmail", Emaill);
                    navController.navigate(R.id.navigation_home, homeBundle);
                    return true;
                case R.id.navigation_dashboard:
                    Bundle dashboardBundle = new Bundle();
                    navController.navigate(R.id.navigation_dashboard, dashboardBundle);
                    return true;
                case R.id.navigation_notifications:
                    Bundle notificationsBundle = new Bundle();
                    navController.navigate(R.id.navigation_notifications, notificationsBundle);
                    return true;
            }
            return false;
        });

    }



}