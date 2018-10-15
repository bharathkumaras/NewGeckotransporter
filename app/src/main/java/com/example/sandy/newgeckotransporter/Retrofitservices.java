package com.example.sandy.newgeckotransporter;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sandy on 04-Oct-18.
 */

public interface Retrofitservices {
    @GET("retrofit-demo.php")
    Call<EmployeeList> getEmployeeData(@Query("company_no") int companyNo);
}
