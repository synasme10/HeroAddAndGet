package com.e.heroaddandget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText Etusername,Etpassword;
    Button Btnlogin,Btnregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Etusername=findViewById(R.id.Etusername);
        Etpassword=findViewById(R.id.Etpassword);
        Btnlogin=findViewById(R.id.Btnlogin);
        Btnregister=findViewById(R.id.Btnregister);

        Btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
