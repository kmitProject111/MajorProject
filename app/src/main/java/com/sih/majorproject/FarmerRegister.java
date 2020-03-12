package com.sih.majorproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

public class FarmerRegister extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_register);
        final EditText fname = findViewById(R.id.name);
        final EditText password = findViewById(R.id.password);
        final EditText location = findViewById(R.id.location);
        final EditText email = findViewById(R.id.email);
        final EditText phone = findViewById(R.id.phone);
        Button btn =findViewById(R.id.btnRegister);
    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {



            ParseQuery<ParseObject> query = ParseQuery.getQuery("Farmers");

           // query.whereEqualTo("f_phone",phone.getText().toString());
            query.findInBackground( new FindCallback<ParseObject>() {
                public void done(List<ParseObject> objects, ParseException e) {
                    if (e == null) {
                       // String name = object.getString("f_name");
                        int flag=0;
                        for(ParseObject obj:objects){
                            if(obj.getString("f_phone").equals(phone.getText().toString())){
                                flag=1;
                                break;
                            }
                        }
                        //String name= object.getString("f_name");
                      // System.out.println("--------"+name);

                        if(flag==0){
                            ParseObject farmers = new ParseObject("Farmers");

                            farmers.put("f_name",fname.getText().toString());
                            farmers.put("f_password", password.getText().toString());
                            farmers.put("f_location",location.getText().toString());
                            farmers.put("f_email",email.getText().toString());
                            farmers.put("f_phone",phone.getText().toString());
                            farmers.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e == null) {
                                        // System.out.println("success");
                                        Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(
                                                getApplicationContext(),
                                                e.getMessage(),
                                                Toast.LENGTH_LONG
                                        ).show();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_LONG).show();

                        }


                    }
                }
            });






        }
    }




    );




    }
}
