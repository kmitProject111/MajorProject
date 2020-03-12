package com.sih.majorproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.view.View.OnClickListener;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Save the current Installation to Back4App
        //ParseInstallation.getCurrentInstallation().saveInBackground();

        Button btn = findViewById(R.id.btnFarmer);

        btn.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, FarmerLogin.class));
            }


        });

        Button btnt = findViewById(R.id.btnTransport);

        btnt.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, TransportLogin.class));
            }


        });

    }
}
