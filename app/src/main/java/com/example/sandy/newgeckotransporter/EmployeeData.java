package com.example.sandy.newgeckotransporter;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sandy on 04-Oct-18.
 */

public class EmployeeData {
    @SerializedName("name")
    private String Name;
    @SerializedName("email")
    private String Email;
    @SerializedName("phone")
    private String phone;

    public void setName(String name)
    {
        this.Name = name;
    }
    public String getName()
    {
        return Name;
    }

    public void setEmail(String email)
    {
        this.Email = email;
    }
    public String getEmail()
    {
        return Email;
    }

    public void setPhone(String Phone)
    {
        this.phone = Phone;
    }
    public String getPhone()
    {
        return phone;
    }
}
