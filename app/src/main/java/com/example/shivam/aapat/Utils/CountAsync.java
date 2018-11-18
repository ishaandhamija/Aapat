package com.example.shivam.aapat.Utils;

import android.os.AsyncTask;

import com.example.shivam.aapat.Activities.EmergencyActivity;

/**
 * Created by ishaandhamija on 27/12/17.
 */

public class CountAsync extends AsyncTask<Void, String, Boolean> {

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
            EmergencyActivity.bookAmbulance();
        }
    }

    public void loopOneSec () {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 1000);
    }
}