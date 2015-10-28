package com.app.mukuljain.sunshine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private final static String LOG_TAG = MainActivity.class.getSimpleName();
    private final static String FORECASTFRAGMENT_TAG = "FFTAG";
    private String mLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(LOG_TAG, "in onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLocation = Utility.getPreferredLocation(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment(), FORECASTFRAGMENT_TAG)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.action_map:
                openPreferredLocationInMap();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openPreferredLocationInMap() {
        String location = Utility.getPreferredLocation(this);

        Uri geolocation = Uri.parse("geo:0,0?").buildUpon()
                .appendQueryParameter("q", location)
                .build();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geolocation);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d(LOG_TAG, "Couldn't call " + location + ", no activity found");
        }
    }

    @Override
    protected void onStart() {
        Log.v(LOG_TAG, "in onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.v(LOG_TAG, "in onResume");
        String location = Utility.getPreferredLocation(this);
        // update the location in our second pane using the fragment manager
        if (location != null && !location.equals(mLocation)) {
            ForecastFragment ff = (ForecastFragment) getSupportFragmentManager().findFragmentByTag(FORECASTFRAGMENT_TAG);
            if (ff != null) {
                ff.onLocationChanged();
            }
            mLocation = location;
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.v(LOG_TAG, "in onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.v(LOG_TAG, "in onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.v(LOG_TAG, "in onDestroy");
        super.onDestroy();
    }
}
