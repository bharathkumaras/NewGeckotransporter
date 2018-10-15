package com.example.sandy.newgeckotransporter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sandy on 11-Oct-18.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final String DbName = "Contacts_Db";
    public static final String Table_NAme = "ContactsTable";
    private static final int DbVersion = 2;
    public static final String Id = "Cid";
    public static  final String Name = "Cname";
    public static final String Phone = "Cphone";

    public  String Create_TAble = "create table ContactsTable(Cid INTEGER PRIMARY KEY AUTOINCREMENT,Cname TEXT, Cphone TEXT)";
    public String Drop_Table = "DROP TABLE IF EXISTS " + Table_NAme;


    public DbHelper(Context context) {
        super(context, DbName, null, DbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_TAble);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Drop_Table);
        onCreate(db);

    }

}
