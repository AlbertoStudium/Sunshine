package com.example.android.sunshine.app;

import ConnectionHTTPAndroid.ConecctionHTTPRequest;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {


       private ArrayAdapter<String> mForcastAdapter;



        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);


            String[]forecastArray={
                    "Today - Sunny 88/63",
                    "Tomorow - Sunny 88/63",
                    "Weds - Sunny 88/63",
                    "Thurs - Sunny 88/63",
                    "Friday - Sunny 88/63",
                    "Satur - Sunny 88/63",
                    "Sund - Sunny 88/63"};


            List<String> weekForcast= new ArrayList<String>(Arrays.asList(forecastArray));

            ListView listview = (ListView)rootView.findViewById(R.id.listview_forecast);


             mForcastAdapter = new ArrayAdapter<String>(
                     getActivity(),
                     R.layout.list_item_forcast,
                     R.id.list_item_forcast_textview,
                     weekForcast);

            listview.setAdapter(mForcastAdapter);

           try {

               URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=seville&mode=json&cnt=7&units=metric");
               ConecctionHTTPRequest task = new ConecctionHTTPRequest();
               String result = task.execute(url).get();

           }catch (Exception e){

               Log.e("PlaceholderFragment", "Error abriendo la conexion", e);
           }




            return rootView;
        }
    }
}
