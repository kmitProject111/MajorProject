package com.sih.majorproject.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.sih.majorproject.R;
import com.sih.majorproject.TRANSPORT_Drawer;

public class TransportAddVehicle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport_add_vehicle);
        TRANSPORT_Drawer td= new TRANSPORT_Drawer();
        final String phone=td.getPhone();
        final EditText no =findViewById(R.id.vehicle_no);
        final EditText capacity =findViewById(R.id.capacity);
       final EditText type= findViewById(R.id.vehicle_type);
       final EditText price =findViewById(R.id.price_add);

        Button add =findViewById(R.id.addVehicle);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseObject vehicle = new ParseObject("Vehicle");
                vehicle.put("capacity",capacity.getText().toString());
                vehicle.put("vehicle_no", no.getText().toString());
                vehicle.put("transport_id",phone);
                vehicle.put("price",price.getText().toString());
                vehicle.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            // System.out.println("success");
                            Toast.makeText(getApplicationContext(), "Vehicle added successful", Toast.LENGTH_LONG).show();
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
