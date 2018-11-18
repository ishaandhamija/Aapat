package com.example.shivam.aapat.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.shivam.aapat.Models.Problem;
import com.example.shivam.aapat.R;
import com.example.shivam.aapat.Utils.CountAsync;
import com.example.shivam.aapat.Utils.RecyclerAdapter;

import java.util.ArrayList;

public class EmergencyActivity extends AppCompatActivity {

    RecyclerView rvProblems;
    ArrayList<Problem> problemArrayList;

    RecyclerAdapter recyclerAdapter;

    TextView btnCancel, btnBook;

    public static TextView tvSeconds;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        btnCancel = (TextView) findViewById(R.id.btnCancel);
        btnBook = (TextView) findViewById(R.id.btnBook);

        tvSeconds = (TextView) findViewById(R.id.tvSeconds);


        CountAsync countAsync = new CountAsync();
        countAsync.execute();

        problemArrayList = new ArrayList<>();

        problemArrayList.add(new Problem(R.mipmap.ic_launcher, "ABC"));
        problemArrayList.add(new Problem(R.mipmap.ic_launcher, "ABC"));
        problemArrayList.add(new Problem(R.mipmap.ic_launcher, "ABC"));
        problemArrayList.add(new Problem(R.mipmap.ic_launcher, "ABC"));

        rvProblems = (RecyclerView) findViewById(R.id.rvProblems);

        recyclerAdapter = new RecyclerAdapter(this, problemArrayList);
        rvProblems.setLayoutManager(new LinearLayoutManager(this));
        rvProblems.setAdapter(recyclerAdapter);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookAmbulance();
            }
        });
    }

    public static void bookAmbulance() {
        Log.d("bookAmbulance", "bookAmbulance: ");
    }
}
