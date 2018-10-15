package com.example.sandy.newgeckotransporter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText username,password;
    Button send;
    private  Preferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        preferences = new Preferences(getApplicationContext());
        send = findViewById(R.id.send1);
        if (preferences.getstatus())
        {
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equals("bharath") && password.getText().toString().equals("bharath599"))
                {
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    preferences.writesharedpreferences(true);
                    finish();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Not Matched", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}
