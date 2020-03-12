package com.sih.majorproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FarmerCheckout extends AppCompatActivity {



    String id;
    String pos;
    String t_id;
    String v_no;
    String cap;
String pri;
    TextView tid;
    TextView capacity ;
    TextView vno ;
    TextView price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_checkout);
        tid=findViewById(R.id.transport_id_checkout);
         capacity=findViewById(R.id.capacity_checkout) ;
         vno =findViewById(R.id.vehicle_no_checkout);
         price=findViewById(R.id.price_checkout);
        Intent intent = getIntent();

        id=intent.getStringExtra("id");
       pos= intent.getStringExtra("pos");
         t_id=intent.getStringExtra("t_id");
         v_no = intent.getStringExtra("v_no");
         cap=intent.getStringExtra("capacity");
            pri=intent.getStringExtra("price");

         tid.setText(t_id);
         vno.setText(v_no);
         capacity.setText(cap);
         price.setText(pri);


    }
}
