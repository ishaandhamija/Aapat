package com.example.shivam.aapat.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.shivam.aapat.Models.User;
import com.example.shivam.aapat.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BackActivity extends AppCompatActivity {

    DatabaseReference dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);

        dbReference = FirebaseDatabase.getInstance().getReference("Users");

        String key = dbReference.push().getKey();
        dbReference.child(key).setValue(new User(key, "Sharad", "8800387550", "O+", true))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Toast.makeText(BackActivity.this, "Submitted", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("dikkat", "onFailure: " + e.toString());
                        Toast.makeText(BackActivity.this, "Please Try Again", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
