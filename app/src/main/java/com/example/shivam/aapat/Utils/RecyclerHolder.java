package com.example.shivam.aapat.Utils;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shivam.aapat.Activities.EmergencyActivity;
import com.example.shivam.aapat.R;


/**
 * Created by ishaandhamija on 26/09/17.
 */

public class RecyclerHolder extends RecyclerView.ViewHolder {

    ImageView ivProblemImg;
    TextView tvProblem;
    View view;
    Context context;

    public RecyclerHolder(View itemView, final Context ctx) {
        super(itemView);

        context = ctx;

        this.ivProblemImg = (ImageView) itemView.findViewById(R.id.ivProblemImg);
        this.tvProblem = (TextView) itemView.findViewById(R.id.tvProblem);
        this.view = itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                EmergencyActivity.bookAmbulance();
                Toast.makeText(ctx, "Request Sent", Toast.LENGTH_SHORT).show();
                ((Activity)ctx).finish();
                sendSMS("8800387550", "Emergency! Go To lat 28.6814, lng 77.3736 Team Aapat");
                EmergencyActivity.countAsync.cancel(true);
            }
        });
    }

    void sendSMS(String number, String msg) {

        RequestQueue queue = Volley.newRequestQueue(context);
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
