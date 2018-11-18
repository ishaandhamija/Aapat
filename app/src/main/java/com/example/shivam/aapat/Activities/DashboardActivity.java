package com.example.shivam.aapat.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.shivam.aapat.R;

public class DashboardActivity extends AppCompatActivity {

    LinearLayout profileLayout,nearbyLayout,tipsLayout,requestLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        profileLayout = (LinearLayout) findViewById(R.id.profileLayout);
        nearbyLayout = (LinearLayout) findViewById(R.id.nearbyLayout);
        tipsLayout = (LinearLayout) findViewById(R.id.tipsLayout);
        requestLayout = (LinearLayout) findViewById(R.id.requestLayout);

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
