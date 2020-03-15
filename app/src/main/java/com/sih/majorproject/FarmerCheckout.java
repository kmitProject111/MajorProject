package com.sih.majorproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

public class FarmerCheckout extends AppCompatActivity {



    String id;
    String pos;
    String t_id;
    String v_no;
    String cap;
    String phone;
String pri;
String ctype;
String ld;
String c_id;
    TextView tid;
    TextView capacity ;
    TextView vno ;
    TextView price;
    TextView pho;
    TextView cropType;
    TextView load;
    TextView cid;
    TextView space;
int s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_checkout);
        tid=findViewById(R.id.transport_id_checkout);
         capacity=findViewById(R.id.capacity_checkout) ;
         vno =findViewById(R.id.vehicle_no_checkout);
         price=findViewById(R.id.price_checkout);
         pho =findViewById(R.id.phone_checkout);
         space=findViewById(R.id.space_checkout);
         cropType=findViewById(R.id.cropType_checkout);
         load=findViewById(R.id.load_checkout);
        Intent intent = getIntent();
ctype=intent.getStringExtra("cropType");
ld=intent.getStringExtra("load");
c_id= intent.getStringExtra("crop_id");

       // id=intent.getStringExtra("id");
      // pos= intent.getStringExtra("pos");
         t_id=intent.getStringExtra("t_id");
         v_no = intent.getStringExtra("v_no");
         cap=intent.getStringExtra("capacity");
            pri=intent.getStringExtra("price");
FarmerHome fh=new FarmerHome();
phone = fh.getPhone();
         tid.setText(t_id);
         vno.setText(v_no);
         capacity.setText(cap);
         price.setText(pri);
         pho.setText(phone);
         cropType.setText(ctype);
         load.setText(ld);
s=Integer.parseInt(cap)-Integer.parseInt(ld);
space.setText(s+"");

        final Button ckout =findViewById(R.id.checkout);
        ckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseObject bookings = new ParseObject("Bookings");
                 bookings.put("transport_id",t_id);
                bookings.put("capacity",cap);
                bookings.put("load",ld);
                bookings.put("free",s+"");
                bookings.put("vehicle_no",v_no);
                bookings.put("price",pri);
                bookings.put("farmer_id",phone);
                bookings.put("cropType",ctype);
                //bookings.put("",phone);

                bookings.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            // System.out.println("success");
                            Toast.makeText(getApplicationContext(), "Booking successful", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(
                                    getApplicationContext(),
                                    e.getMessage(),
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    }
                });


                if(s<=0){
//                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Vehicle");
//                    query.whereEqualTo("vehicle_no",v_no);
//                    query.getFirstInBackground( new GetCallback<ParseObject>() {
//                        public void done(ParseObject object, ParseException e) {
//                            if (e == null) {
//                                object.put("active","no");
//                                object.saveInBackground();
//                            } else {
//                                // Failed
//                                Toast.makeText(
//                                        getApplicationContext(),
//                                        e.getMessage(),
//                                        Toast.LENGTH_LONG
//                                ).show();
//                            }
//                        }
//                    });

String idcc=phone+ctype+ld;
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Crop");
                    query.whereEqualTo("idcc",idcc);
                    query.getFirstInBackground( new GetCallback<ParseObject>() {
                        public void done(ParseObject object, ParseException e) {
                            if (e == null) {
                                object.put("load",s*-1+"");
                                object.put("idcc",phone+ctype+(s*-1+""));
                                object.saveInBackground();
                            } else {
                                // Failed
                                Toast.makeText(
                                        getApplicationContext(),
                                        e.getMessage(),
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                        }
                    });



                }else{



                }


            }
        });


    }
}
