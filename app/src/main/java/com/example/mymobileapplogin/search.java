package com.example.mymobileapplogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class search extends AppCompatActivity {

    Button searchBtn;
    // API key
    //http://www.omdbapi.com/?i=tt3896198&apikey=4bcd4080
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }
}
