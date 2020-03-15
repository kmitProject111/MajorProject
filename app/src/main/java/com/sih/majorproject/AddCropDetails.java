package com.sih.majorproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class AddCropDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_crop_details);

        final EditText crop =findViewById(R.id.crop);
        final EditText load =findViewById(R.id.load_f);
        Button add = findViewById(R.id.add_crop);


        FarmerHome fh =new FarmerHome();
       final String phone =fh.getPhone();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseObject vehicle = new ParseObject("Crop");
                vehicle.put("cropType",crop.getText().toString());
                vehicle.put("load", load.getText().toString());
                vehicle.put("crop_id",phone);
                vehicle.put("idcc",phone+crop.getText().toString()+load.getText().toString());
                vehicle.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            // System.out.println("success");
                            Toast.makeText(getApplicationContext(), "crop added successful", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(
                                    getApplicationContext(),
                                    e.getMessage(),
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    }
                });

            }
        });

    }
}
