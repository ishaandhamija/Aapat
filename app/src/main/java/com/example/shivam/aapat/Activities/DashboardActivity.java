package com.example.shivam.aapat.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.shivam.aapat.R;

public class DashboardActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";

    LinearLayout profileLayout,nearbyLayout,tipsLayout,requestLayout;
    ImageView btnEmergency;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnEmergency = (ImageView) findViewById(R.id.btnEmergency);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("my_userId", "-LRa4gDBjG9HjTyar1pF");
        editor.putString("my_name", "Ishaan");
        editor.putString("my_number", "9958421789");
        editor.putString("my_blood_group", "O+");
        editor.putBoolean("my_donation_willingness", true);
        editor.commit();

        profileLayout = (LinearLayout) findViewById(R.id.profileLayout);
        nearbyLayout = (LinearLayout) findViewById(R.id.nearbyLayout);
        tipsLayout = (LinearLayout) findViewById(R.id.tipsLayout);
        requestLayout = (LinearLayout) findViewById(R.id.requestLayout);

        btnEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, EmergencyActivity.class));
            }
        });

        profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this,ProfileActivity.class));
            }
        });

        nearbyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this,NearbuyActivity.class));
            }
        });

        tipsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        requestLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this,BloodRequestActivity.class));
            }
        });
    }
}
