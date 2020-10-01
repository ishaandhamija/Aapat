package com.example.shivam.aapat.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shivam.aapat.Models.BloodRequest;
import com.example.shivam.aapat.Models.User;
import com.example.shivam.aapat.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BloodRequestActivity extends AppCompatActivity {

    Integer PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    public static final String TAG = "aapatt";

    TextView tvLocation;
    EditText etBloodGroup;
    Button btnRequestBlood;

    private String bloodGroup;
    private LatLng loc;
    private Double lat, lon;
    private String addr;
    private Boolean textViewFilled;

    DatabaseReference dbReference, dbReference1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_request);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvLocation = (TextView) findViewById(R.id.tvLocation);
        etBloodGroup = (EditText) findViewById(R.id.etBloodGroup);
        btnRequestBlood = (Button) findViewById(R.id.btnRequestBlood);

        textViewFilled = false;

        dbReference = FirebaseDatabase.getInstance().getReference("BloodRequests");
        dbReference1 = FirebaseDatabase.getInstance().getReference("Users"); 

        tvLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent =
                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                                    .build(BloodRequestActivity.this);
                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                    // TODO: Handle the error.
                } catch (GooglePlayServicesNotAvailableException e) {
                    // TODO: Handle the error.
                }
            }
        });

        btnRequestBlood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid()) {
                    bloodGroup = etBloodGroup.getText().toString();
                    lat = loc.latitude;
                    lon = loc.longitude;

                    //logic for pushing the blood requests onto the panel
                    String key = dbReference.push().getKey();
                    dbReference.child(key).setValue(new BloodRequest(key, lat, lon, bloodGroup, addr, "7011690098"))
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    Toast.makeText(BloodRequestActivity.this, "Request Submitted", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(BloodRequestActivity.this, "Please Try Again!!", Toast.LENGTH_SHORT).show();
                                }
                            });

                    dbReference1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for (DataSnapshot u : dataSnapshot.getChildren()) {
                                User user = u.getValue(User.class);

                                Log.d("users_", "onDataChange: " + user.getName());

                                String no = user.getContactNumber();
                                String msg = "Blood Group: " + bloodGroup + " \n Location Required: " + addr + " \n Contact Number: " + "7011690098";

                                sendSMS(no, msg);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.i(TAG, "Place: " + place.getName());

                tvLocation.setText(place.getName() + ", " + place.getAddress());
                loc = place.getLatLng();
                addr = place.getName().toString() + ", " + place.getAddress().toString();

                textViewFilled = true;
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.i(TAG, status.getStatusMessage());



            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
                Toast.makeText(this, "Try Again", Toast.LENGTH_SHORT).show();
            }
        }
    }

    Boolean isValid() {

        if (TextUtils.isEmpty(tvLocation.getText())) {
            Toast.makeText(this, "Enter Location", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(etBloodGroup.getText())) {
            Toast.makeText(this, "Enter Blood Group", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
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
