package com.sih.majorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class FarmerBookTrans extends AppCompatActivity {
    ArrayList<Vehicle> vl;
    ArrayList<String> al;
    ListView list;
    String cropType;
    String load;
    String crop_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_book_trans);
        list =findViewById(R.id.list_vehicle_f);
        Intent intent = getIntent();
        cropType = intent.getStringExtra("cropType");
        crop_id = intent.getStringExtra("c_id");
        load=intent.getStringExtra("load");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Vehicle");

        query.findInBackground( new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    vl=new ArrayList<>();
                    al=new ArrayList<String>();
                    for(ParseObject obj: objects){
                        Vehicle v= new Vehicle();
                        v.setCapacity(obj.getString("capacity"));
                        v.setTransport_id(obj.getString("transport_id"));
                        v.setVehicle_no(obj.getString("vehicle_no"));
                        v.setPrice(obj.getString("price"));
                        vl.add(v);
                        al.add(obj.getString("capacity")+"-"+obj.getString("transport_id")+"-"+obj.getString("vehicle_no")+"-"+obj.getString("price"));

                    }


                    //System.out.println(al.get(0)+"----------------------------");
                    ListAdapter listAdapter = new ArrayAdapter<String>(FarmerBookTrans.this,
                            android.R.layout.simple_list_item_1, al
                    );
                    list.setAdapter(listAdapter);
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            System.out.println(position+"p"+id+"id");
                            // startActivity(new Intent(FarmerBook.this,FarmerCheckout.class));
                            Intent intent= new Intent(FarmerBookTrans.this ,FarmerCheckout.class);

                            intent.putExtra("pos",position);
                            intent.putExtra("id",id);
                            intent.putExtra("t_id",vl.get(position).getTransport_id());
                            intent.putExtra("capacity",vl.get(position).getCapacity());
                            intent.putExtra("v_no",vl.get(position).getVehicle_no());
                            intent.putExtra("price",vl.get(position).getPrice());
                            intent.putExtra("c_id",crop_id);
                            intent.putExtra("cropType",cropType);
                            intent.putExtra("load",load);

                            startActivity(intent);
                        }
                    });

                }else{
                    //something went wrong
                }
            }




        });
    }
}
