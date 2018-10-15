package com.example.sandy.newgeckotransporter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sandy on 04-Oct-18.
 */

public class EmployeeList {
    @SerializedName("employeeList")
    private ArrayList<EmployeeData> employeeList;

    public ArrayList<EmployeeData> getEmployeeArrayList() {
        return employeeList;
    }

    public void setEmployeeArrayList(ArrayList<EmployeeData> employeeArrayList) {
        this.employeeList = employeeArrayList;
    }
}
