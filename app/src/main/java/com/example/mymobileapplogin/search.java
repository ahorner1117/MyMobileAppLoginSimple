package com.example.mymobileapplogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.android.volley.Request;
import com.android.volley.RequestQueue;


import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.FutureTask;

public class search extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.mymobileapplogin";
    Button searchBtn;
    EditText text;
    RequestQueue queue;

    private final String api_key = "4bcd4080";
   // private FutureTask Picasso;

    // API key
    //https://www.omdbapi.com/?i=tt3896198&apikey=4bcd4080
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //Attach references to views
        searchBtn = findViewById(R.id.button);
        text = findViewById(R.id.editText);

        //Initialize the volley request queue
        queue = queue = Volley.newRequestQueue(this);


    }


    // Send message to obtain API information and create a new intent
    public void searchClicked(View v) {
        String query = text.getText().toString(); //Get the title of the movie.
        String url = String.format("https://www.omdbapi.com/?t=%s&apikey=%s", query, api_key);
        System.out.println(url);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject data = new JSONObject(response);
                            DisplayMovie(data);
                        } catch (JSONException e) {
                            System.out.println("ERROR PARSING RESPONSE");
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERROR WITH REQUEST");
            }
        });
        getRequestQueue().add(stringRequest);





    }


    //get the request queue currently being used by this activity.
    public RequestQueue getRequestQueue() {
        if (queue == null) {
            queue = Volley.newRequestQueue(this);
        }
        return this.queue;

    }


    //Callback for doing work with movie once the request has fetched it.
    public void DisplayMovie(JSONObject data) {
        //Insert your work here
        //Sample here
        final TextView textViews = findViewById(R.id.textView);
        final ImageView imageView = findViewById(R.id.imageView);
        String name;
        try {
            System.out.println(data.getString("Title"));
            name = data.getString("Title");
            System.out.println(data.getString("Genre"));
            textViews.setText(name);
            
           // Picasso.get().load("http://i.imgur.com/DvpvklR.png%22).into(imageView));
        } catch (JSONException e) {
            System.out.println("ERROR DISPLAYING RESPONSE");
        }
    }
}





