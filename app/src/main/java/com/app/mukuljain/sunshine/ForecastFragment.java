package com.app.mukuljain.sunshine;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mukuljain on 10/19/2015.
 * this is a frament class for a simple view
 */
public class ForecastFragment extends Fragment {

    ArrayAdapter<String> mForecastAdapter;

    public ForecastFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // add this line in order for this fragment to handle menus
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.forecastfragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                FetchWeatherForecastAsyncTask fetchWeatherForecastAsyncTask = new FetchWeatherForecastAsyncTask(mForecastAdapter);
                fetchWeatherForecastAsyncTask.execute("94043");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        /*String[] forecast = {
                "Today - Sunny - 88/63",
                "Today - Sunny - 88/63",
                "Today - Sunny - 88/63",
                "Today - Sunny - 88/63",
                "Today - Sunny - 88/63",
                "Today - Sunny - 88/63",
                "Today - Sunny - 88/63",
                "Today - Sunny - 88/63",
                "Today - Sunny - 88/63",
                "Today - Sunny - 88/63",
                "Today - Sunny - 88/63"
        };*/

        List<String> weekForecast = new ArrayList<String>();

        mForecastAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.list_item_forecast,
                R.id.list_item_forecast_textview,
                weekForecast);

        ListView listView = (ListView) rootView.findViewById(R.id.listview_forecast);
        listView.setAdapter(mForecastAdapter);

        return rootView;
    }
}
