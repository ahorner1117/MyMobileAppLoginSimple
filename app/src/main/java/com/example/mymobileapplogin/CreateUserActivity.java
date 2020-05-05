package com.example.mymobileapplogin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class CreateUserActivity extends Activity {

    // Declaring variables for app attributes
    ImageView imageView;
    TextView name, email;
    Button createUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createuser);

        // Initializing the variables for the app
        name = findViewById(R.id.textName);
        email = findViewById(R.id.textEmail);
        email = findViewById(R.id.textEmail);
        createUser = findViewById(R.id.buttonCreateUser);

        // Setting the click listener to create a new user
        createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.buttonCreateUser:
                        createUser();
                        break;
                }
            }
        });

    }

    private void createUser() {
        Toast.makeText(getApplicationContext(), "New User Created", Toast.LENGTH_LONG).show();
        System.out.println("");
        finish();
    }
}

