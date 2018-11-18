package com.example.shivam.aapat.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

import com.example.shivam.aapat.R;

public class ProfileActivity extends AppCompatActivity {

    Switch aSwitch;
    boolean flag;
    android.content.SharedPreferences spDonate;
    android.content.SharedPreferences.Editor editor;
    public static final String Flag = "Flag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spDonate = getSharedPreferences(Flag, MODE_PRIVATE);
        editor = spDonate.edit();




        aSwitch = (Switch) findViewById(R.id.btnDonate);
        aSwitch.setChecked(spDonate.getBoolean("flag",false));
//        aSwitch.setChecked(flag);

        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean("flag",aSwitch.isChecked());
                editor.commit();
//                if (aSwitch.isChecked())
//                    Toast.makeText(ProfileActivity.this, "On", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
