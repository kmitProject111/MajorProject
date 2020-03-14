package com.sih.majorproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class FarmerCheckout extends AppCompatActivity {



    String id;
    String pos;
    String t_id;
    String v_no;
    String cap;
    String pri;
    String f_no;
    TextView fno;
    TextView tid;
    TextView capacity ;
    TextView vno ;
    TextView price;
    Button checkout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_checkout);
        fno=findViewById(R.id.farmer_phone_checkout);
        tid=findViewById(R.id.transport_id_checkout);
         capacity=findViewById(R.id.capacity_checkout) ;
         vno =findViewById(R.id.vehicle_no_checkout);
         price=findViewById(R.id.price_checkout);
        Intent intent = getIntent();

        f_no=intent.getStringExtra("phone");
        id=intent.getStringExtra("id");
       pos= intent.getStringExtra("pos");
         t_id=intent.getStringExtra("t_id");
         v_no = intent.getStringExtra("v_no");
         cap=intent.getStringExtra("capacity");
            pri=intent.getStringExtra("price");
        checkout=(Button) findViewById(R.id.button2);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBooking();
                openBookingShow();
            }
        });

         fno.setText(f_no);
         tid.setText(t_id);
         vno.setText(v_no);
         capacity.setText(cap);
         price.setText(pri);



    }
    public void addBooking(){
        ParseObject booking = new ParseObject("Bookings");

        booking.put("farmer_no",fno.getText().toString());
        booking.put("transport_id",tid.getText().toString());
        booking.put("vehicle_no",  vno.getText().toString());
        booking.put("capacity",capacity.getText().toString());
        booking.put("Price",price.getText().toString());

        booking.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    // System.out.println("success");
                    Toast.makeText(getApplicationContext(), "Booking added successfully", Toast.LENGTH_LONG).show();
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
    public void openBookingShow(){
        Intent intent1 = new Intent(this, FarmerBookingShow.class);
        startActivity(intent1);
    }
}
