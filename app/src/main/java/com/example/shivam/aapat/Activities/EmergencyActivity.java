package com.example.shivam.aapat.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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

    public static CountAsync countAsync;

    public static TextView tvSeconds;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        btnCancel = (TextView) findViewById(R.id.btnCancel);
        btnBook = (TextView) findViewById(R.id.btnBook);

        tvSeconds = (TextView) findViewById(R.id.tvSeconds);


        countAsync = new CountAsync(this);
        countAsync.execute();

        problemArrayList = new ArrayList<>();

        problemArrayList.add(new Problem(R.drawable.ic_common, "Accident"));
        problemArrayList.add(new Problem(R.drawable.ic_common, "Heart Attach"));
        problemArrayList.add(new Problem(R.drawable.ic_common, "Head Injury"));
        problemArrayList.add(new Problem(R.drawable.ic_common, "Police"));
        problemArrayList.add(new Problem(R.drawable.ic_common, "Pregnancy"));
        problemArrayList.add(new Problem(R.drawable.ic_common, "Fire"));


        rvProblems = (RecyclerView) findViewById(R.id.rvProblems);

        recyclerAdapter = new RecyclerAdapter(this, problemArrayList);
        rvProblems.setLayoutManager(new LinearLayoutManager(this));
        rvProblems.setAdapter(recyclerAdapter);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countAsync.cancel(true);
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


    public void bookAmbulance() {
        Toast.makeText(this, "Request Sent", Toast.LENGTH_SHORT).show();
        finish();

        countAsync.cancel(true);

        sendSMS("8800387550", "Emergency! Go To lat 28.6814, lng 77.3736 Team Aapat");
    }

    void sendSMS(String number, String msg) {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://manage.staticking.net/index.php/smsapi/httpapi/?uname=ankit01&password=info@3030&sender=PFAITH&receiver=" + number + "&route=TA&msgtype=1&sms=" + msg;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);
    }
}
