package com.example.mymobileapplogin;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.FutureTask;

public class search extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.mymobileapplogin";
    Button searchBtn;
    RequestQueue queue;
    EditText text;
    Button favBTN;
    private final String api_key = "4bcd4080";

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
        //Sample here
        final TextView textViews = findViewById(R.id.textView);
        final ImageView imageView = findViewById(R.id.imageView);
        //final WebView webView
        String name;
        URI poster;
        try {
            System.out.println(data.getString("Title"));
            name = data.getString("Title");
            System.out.println(data.getString("Genre"));

            poster = new URI(data.getString("Poster"));
            // Used the commented code to hard code in image in order to learn how to
            // request the poster from the API
            //imageView.setImageURI(https://m.media-amazon.com/images/M/MV5BMTYwNjAyODIyMF5BMl5BanBnXkFtZTYwNDMwMDk2._V1_SX300.jpg);
            textViews.setText(name);
           Picasso.get().load(String.valueOf(poster)).into(imageView);


           // Created a favorites button that will create a toast message
            // to tell the user "Added to favorites"
            favBTN = findViewById(R.id.button2);
            favBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Added to Favorites", Toast.LENGTH_LONG).show();
                }
            });
        } catch (JSONException | URISyntaxException e) {
            System.out.println("ERROR DISPLAYING RESPONSE");
        }


    }
}





