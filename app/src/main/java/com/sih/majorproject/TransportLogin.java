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

public class TransportLogin extends AppCompatActivity {

    EditText phone;
    EditText pword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport_login);

        TextView tv=findViewById(R.id.tt_register);
        Button btn =findViewById(R.id.tt_btnLogin);
        phone=findViewById(R.id.tt_name);
        pword = findViewById(R.id.tt_password);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TransportLogin.this, TransportRegister.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Transport");
                query.whereEqualTo("t_phone",phone.getText().toString());
                query.getFirstInBackground( new GetCallback<ParseObject>() {
                    public void done(ParseObject object, ParseException e) {
                        if (e == null) {
                            String password = object.getString("t_password");
                            //System.out.println(name);

                            if(password.equals(pword.getText().toString())){
                               // startActivity(new Intent(TransportLogin.this, TRANSPORT_Drawer.class));
                                Intent intent= new Intent(TransportLogin.this ,TRANSPORT_Drawer.class);

                                intent.putExtra("phone",phone.getText().toString());
                                intent.putExtra("pword",pword.getText().toString());

                                startActivity(intent);
                            }else{
                                Toast.makeText(getApplicationContext(), "Please enter correct details", Toast.LENGTH_LONG).show();
                            }

                        } else {
                            // something went wrong
                            Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
