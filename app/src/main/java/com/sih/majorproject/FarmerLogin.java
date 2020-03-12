package com.sih.majorproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class FarmerLogin extends AppCompatActivity {

     EditText phone;
     EditText pword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_login);


        TextView tv=findViewById(R.id.register);
        Button btn =findViewById(R.id.btnLogin);
        phone=findViewById(R.id.f_name);
        pword = findViewById(R.id.password);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FarmerLogin.this, FarmerRegister.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Farmers");
                query.whereEqualTo("f_phone",phone.getText().toString());
                query.getFirstInBackground( new GetCallback<ParseObject>() {
                    public void done(ParseObject object, ParseException e) {
                        if (e == null) {
                            String password = object.getString("f_password");
                            //System.out.println(name);

                            if(password.equals(pword.getText().toString())){
                               // startActivity(new Intent(FarmerLogin.this, FarmerHome.class));
                                Intent intent= new Intent(FarmerLogin.this ,FarmerHome.class);

                                intent.putExtra("phone",phone.getText().toString());
                                intent.putExtra("pword",pword.getText().toString());

                                startActivity(intent);
                            }else{
                                Toast.makeText(getApplicationContext(), "Please enter correct details", Toast.LENGTH_LONG).show();
                            }

                        } else {
                            // something went wrong
                        }
                    }
                });
            }
        });





    }

}
