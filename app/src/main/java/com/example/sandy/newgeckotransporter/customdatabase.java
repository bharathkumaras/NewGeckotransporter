package com.example.sandy.newgeckotransporter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by sandy on 12-Oct-18.
 */

public class customdatabase {

    DbHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    customdatabase(Context context)
    {
        dbHelper = new DbHelper(context);
    }

    public void opendb()
    {

        sqLiteDatabase = dbHelper.getWritableDatabase();

    }
    public void closedb()
    {
        sqLiteDatabase.close();
    }

    public void insertdadata(String name,String phone)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.Name,name);
        contentValues.put(DbHelper.Phone,phone);
        sqLiteDatabase.insert(DbHelper.Table_NAme,null,contentValues);

    }

    public Cursor getcursor()
    {
        return sqLiteDatabase.rawQuery("select * from " +DbHelper.Table_NAme,null);
    }

    public void delete()
    {
        sqLiteDatabase.delete(DbHelper.Table_NAme,null,null);
    }

    public void deleteItem(String rowid)
    {
        sqLiteDatabase.delete(DbHelper.Table_NAme,rowid+"="+DbHelper.Id,null);
    }
}
