package com.example.mymobileapplogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.api.Response;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class search<RequestQueue, StringRequest> extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.mymobileapplogin";
    Button searchBtn;
    // API key
    //http://www.omdbapi.com/?i=tt3896198&apikey=4bcd4080
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    // Send message to obtain API information and create a new intent
    public void sendMessage(String message) {
        // Do something in response to button
        Intent intent = new Intent(this, search.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    // Creating the api key to send the message
    public void createURLZipCode(String zipCode) {
        final String url = "http://www.omdbapi.com/?i=tt3896198&apikey=4bcd4080";;
        sendMessage(url);
    }

    // In order to display the movie results
    protected void DisplayMovieResults(JSONObject response) {
        String MovieResults;
        final TextView textView = findViewById(R.id.weatherTextView);
        final TextView cityNameView = findViewById(R.id.cityNameView);
        try {
            JSONObject coord = response.getJSONObject("coord");
           // lon = coord.get("lon").toString();
           // lat = coord.get("lat").toString();
            JSONArray weather = response.getJSONArray("weather");
            JSONObject weatherPart = weather.getJSONObject(0);
            String main = weatherPart.getString("main");
            String description = weatherPart.getString("description");
            String cityName = response.getString("name");
            JSONObject mainJSON = response.getJSONObject("main");
            String temperature = mainJSON.getString("temp");
            MovieResults =
                    "\nWeather: " +
                    main.toUpperCase() + "\nDescription: " + description.toUpperCase() + "\nTemperature: "
                    + temperature;
            cityNameView.setText(cityName + " Weather");
            textView.setText(MovieResults);
            //addMarker();

        } catch (JSONException e) {
            System.out.println("Cannot work");
            e.printStackTrace();
        }
    }
}
