package com.example.sandy.newgeckotransporter;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.preference.Preference;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.security.acl.Permission;

public class MainActivity extends AppCompatActivity {
    Button sendrequest,camera,gallery,retofit,sqlite;
    private int camerapermission = 200;
    private int External = 201;
    //This is test code



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendrequest = findViewById(R.id.sendmessage);
        camera = findViewById(R.id.CameraRequest);
        gallery = findViewById(R.id.Gallery);
        retofit = findViewById(R.id.rtrofit);
        sqlite = findViewById(R.id.Sqlite);

        sqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Sqlite.class);
                startActivity(intent);
            }
        });
        retofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Retrofit.class);
                startActivity(intent);
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                {
                    Intent intent = new Intent(MainActivity.this,Gallery.class);
                    startActivity(intent);
                }
                else
                {
                    if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.CAMERA))
                    {

                        Snackbar.make(findViewById(android.R.id.content),"Permission Requid",Snackbar.LENGTH_SHORT).setAction("Enable", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},External);
                            }
                        }).show();

                    }
                    else {
                        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},External);

                    }

                }
            }
        });
        sendrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Sendmessage.class);
                startActivity(intent);

            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
                {
                    Intent intent = new Intent(MainActivity.this,CameraActivity.class);
                    startActivity(intent);
                }
                else
                {
                    if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.CAMERA))
                    {

                        Snackbar.make(findViewById(android.R.id.content),"Permission Requid",Snackbar.LENGTH_SHORT).setAction("Enable", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},camerapermission);
                            }
                        }).show();


                    }
                    else {
                        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},camerapermission);

                    }

                }
            }
        });

    }
}
