package com.sih.majorproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FarmerBook extends AppCompatActivity {


     ArrayList<Crop> cl;
    ArrayList<String> al;
   ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_book);
        list =findViewById(R.id.list_crop_f);


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Crop");

        query.findInBackground( new FindCallback<ParseObject>() {
                                    public void done(List<ParseObject> objects, ParseException e) {
                                if(e==null){
                                    cl=new ArrayList<>();
                                    al=new ArrayList<String>();
                                    for(ParseObject obj: objects){
                                        Crop c=new Crop();
                                        c.setCropId(obj.getString("crop_id"));
                                        c.setCropType(obj.getString("cropType"));
                                        c.setLoad(obj.getString("load"));
                                        cl.add(c);
                                        al.add("CropType= "+obj.getString("cropType")+"-"+"load= "+obj.getString("load")+"-"+"Crop_id= "+obj.getString("crop_id"));
                                }

                                    }

                                        ListAdapter listAdapter = new ArrayAdapter<String>(FarmerBook.this,
                                                android.R.layout.simple_list_item_1, al
                                        );
                                        list.setAdapter(listAdapter);
                                        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                System.out.println(position+"p"+id+"id");
                                                Intent intent= new Intent(FarmerBook.this ,FarmerBookTrans.class);

                                                intent.putExtra("pos",position);
                                                intent.putExtra("id",id);
                                                intent.putExtra("c_id",cl.get(position).getCropId());
                                                intent.putExtra("cropType",cl.get(position).getCropType());
                                                intent.putExtra("load",cl.get(position).getLoad());

                                                startActivity(intent);
                                            }
                                        });
                                    }
                                });





        /*



         */
         //wont work here
        //System.out.println(al.get(0)+"----------------------------");


    }


}
