package com.example.sandy.newgeckotransporter;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Retrofit extends AppCompatActivity {
    RecyclerView recyclerView;
    Retrofitreccyclerview adapter;
    private Retrofitservices service;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        recyclerView = findViewById(R.id.Retrofitrecycler);

        service = Apiclient.getRetrofitInstance().create(Retrofitservices.class);
        Call<EmployeeList> list = service.getEmployeeData(100);
        list.enqueue(new Callback<EmployeeList>() {
            @Override
            public void onResponse(Call<EmployeeList> call, Response<EmployeeList> response) {
                getdata(response.body().getEmployeeArrayList());

            }

            @Override
            public void onFailure(Call<EmployeeList> call, Throwable t) {

            }
        });

    }


        public void getdata(ArrayList<EmployeeData> data)
        {
            adapter = new Retrofitreccyclerview(data);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);

        }




}
