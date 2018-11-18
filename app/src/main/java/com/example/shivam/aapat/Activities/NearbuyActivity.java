package com.example.shivam.aapat.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.shivam.aapat.Models.Hospital;
import com.example.shivam.aapat.R;
import com.example.shivam.aapat.Utils.RecyclerAdapter1;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NearbuyActivity extends AppCompatActivity {

    RecyclerView rvHospitals;
    RequestQueue requestQueue;
    public static final String TAG = "NearbuyActivity";
    ArrayList<Hospital> hospitals;
    RecyclerAdapter1 recyclerAdapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearbuy);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Nearby Hospitals");
        hospitals = new ArrayList<>();
        rvHospitals = (RecyclerView) findViewById(R.id.rvList);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching nearby hospitals..");
        progressDialog.show();
        fetchHospitals();
    }

    void fetchHospitals(){
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=28.6297,77.3721&radius=5000&type=hospital&key=AIzaSyBgEqbEuZ8LJdG7BmDXn3frx89AK1IVd0c",
                null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray= response.getJSONArray("results");
                            for (int i=0;i<jsonArray.length();++i){
                                Log.d(TAG, "onResponse: "+jsonArray.getJSONObject(i).getString("name"));
                                JSONObject loc = jsonArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location");
                                String name = jsonArray.getJSONObject(i).getString("name");
                                String address = jsonArray.getJSONObject(i).getString("vicinity");
                                String placeId = jsonArray.getJSONObject(i).getString("place_id");
                                LatLng latlng = new LatLng(loc.getDouble("lat"),loc.getDouble("lng"));
                                String dist = String.valueOf(round(distance(28.6297,77.3721,loc.getDouble("lat"),loc.getDouble("lng"),'K'),2)) + " km away";
                                Hospital hospital = new Hospital(name,address,placeId,latlng,dist);
                                hospitals.add(hospital);
                            }
                            rvHospitals.setLayoutManager(new LinearLayoutManager(NearbuyActivity.this));
                            recyclerAdapter = new RecyclerAdapter1(NearbuyActivity.this,hospitals);
                            rvHospitals.setAdapter(recyclerAdapter);
                            progressDialog.dismiss();

//                            Log.d("Hospitals", "onResponse: "+response.getJSONArray("results").getJSONObject(0).getString("name"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "onErrorResponse: "+error);
                    }
                }

        );

        requestQueue.add(jsonObjectRequest);
    }

    public static final double distance(double lat1, double lon1, double lat2, double lon2, char unit)
    {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        if (unit == 'K') {
            dist = dist * 1.609344;
        }
        else if (unit == 'N') {
            dist = dist * 0.8684;
        }

        return (dist);
    }

    private static final double deg2rad(double deg)
    {
        return (deg * Math.PI / 180.0);
    }

    private static final double rad2deg(double rad)
    {
        return (rad * 180 / Math.PI);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
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
