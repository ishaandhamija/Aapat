package com.example.shivam.aapat.Utils;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shivam.aapat.Activities.EmergencyActivity;

/**
 * Created by ishaandhamija on 27/12/17.
 */

public class CountAsync extends AsyncTask<Void, String, Boolean> {

    Context context;

    public CountAsync(Context ctx) {
        context = ctx;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        for (Integer i=9;i>=0;i--) {
            publishProgress(i.toString());
            loopOneSec();
        }

        return true;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);

        EmergencyActivity.tvSeconds.setText(values[0]);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if (aBoolean.equals(true)) {
//            EmergencyActivity.bookAmbulance();
            Toast.makeText(context, "Request Sent", Toast.LENGTH_SHORT).show();
            ((Activity)context).finish();

            sendSMS("8800387550", "Emergency! Go To lat 28.6814, lng 77.3736 Team Aapat");
        }
    }

    public void loopOneSec () {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 1000);
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