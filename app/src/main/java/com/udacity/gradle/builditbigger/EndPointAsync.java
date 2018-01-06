package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by Omni on 06/01/2018.
 */

public class EndPointAsync extends AsyncTask<Void, Void, String> {
    private static final String TAG = EndPointAsync.class.getSimpleName();
    private MyApi myApiService = null;
    private String joke ="";

    @Override
    protected String doInBackground(Void... voids) {
        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/");

            myApiService = builder.setApplicationName("FinalProject").build();
        }

        try {
            return myApiService.sayAJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }


    }


    @Override
    protected void onPostExecute(String result) {
        joke = result;
//            Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
        Log.d(TAG, "onPostExecute: " +result);
    }
}

