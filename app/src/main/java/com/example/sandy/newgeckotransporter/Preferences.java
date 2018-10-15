package com.example.sandy.newgeckotransporter;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sandy on 30-Sep-18.
 */

public class Preferences {
    private SharedPreferences sharedPreferences;
    private Context context;

    Preferences(Context context)
    {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("MyFile",Context.MODE_PRIVATE);

    }


    public void writesharedpreferences(boolean status)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getResources().getString(R.string.login_status_preferences),status);
        editor.commit();
    }
    public boolean getstatus()
    {
        boolean status = false;
        status = sharedPreferences.getBoolean(context.getResources().getString(R.string.login_status_preferences),false);
        return status;


    }

}