package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.omni.mylibrary.JokesActivity;
import com.udacity.gradle.builditbigger.IdlingResource.SimpleIdlingResource;


public class MainActivity extends AppCompatActivity {

    private static String joke = "";
    private static SimpleIdlingResource mSimpleIdlingResource ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create and initialize resource here
        if (mSimpleIdlingResource == null) {
            mSimpleIdlingResource = new SimpleIdlingResource();
            mSimpleIdlingResource.setIdleState(false);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
         new FinalAsyncTask1(this).execute();
        // Delay the execution, return message via callback.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (mSimpleIdlingResource != null) {
                    mSimpleIdlingResource.setIdleState(true);
                }
            }
        } , 2000);

    }

    public void launchLibraryActivity(View view) {
        new FinalAsyncTask2(this).execute();
    }

    @VisibleForTesting
    @NonNull
    public static SimpleIdlingResource getIdlingResource() {
        if (mSimpleIdlingResource == null)
            mSimpleIdlingResource = new SimpleIdlingResource();
        return mSimpleIdlingResource;
    }


    public static class FinalAsyncTask1 extends EndPointAsync {
        private final String TAG = FinalAsyncTask1.class.getSimpleName();
        private Context context;

        FinalAsyncTask1(Context context) {
            this.context = context;
        }



        @Override
        protected void onPostExecute(String result) {
            joke = result;
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
            Log.d(TAG, "onPostExecute: " + result);
        }
    }

    private static class FinalAsyncTask2 extends EndPointAsync {
        private final String TAG = FinalAsyncTask2.class.getSimpleName();
        private Context context;

        FinalAsyncTask2(Context context) {
            this.context = context;
        }

        @Override
        protected void onPostExecute(String result) {
            Intent myIntent = new Intent(context, JokesActivity.class);
            myIntent.putExtra("joke" , result);
            context.startActivity(myIntent);
            Log.d(TAG, "onPostExecute: " + result);
        }
    }
}