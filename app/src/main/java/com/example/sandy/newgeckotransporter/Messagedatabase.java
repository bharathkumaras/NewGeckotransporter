package com.example.sandy.newgeckotransporter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sandy on 01-Oct-18.
 */

public class Messagedatabase extends SQLiteOpenHelper {
    public static  final  String Database_Name = "Messageing";
    public static  final int Databaseversion = 2;
    public static  String Table_Name = "MessageTable";
    public static  String Id = "id";
    public static  String Message = "message";

    public static final  String Crate_table = "CREATE TABLE " + Table_Name +"("+Id+" INTEGER PRIMARY KEY," +Message+" Text"+")";


    public static final String Drop_table = "drop table if exists"+Table_Name;

        public Messagedatabase(Context context) {
        super(context, Database_Name, null, Databaseversion);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL(Crate_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(Drop_table);
            onCreate(db);
    }


}
