package com.example.sandy.newgeckotransporter;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.net.URL;

/**
 * Created by sandy on 01-Oct-18.
 */

public class MesssageContentprovider extends ContentProvider {
    Messagedatabase messagedatabase;
    SQLiteDatabase db;

    public static  String Authority = "com.example.sandy.newgeckotransporter.MesssageContentprovider";
    public static  Uri uri = Uri.parse("content://"+Authority +"/messageings");
    public static  UriMatcher urimatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static
    {
        urimatcher.addURI(Authority,"/messageings",1);
    }



    @Override
    public boolean onCreate() {
        Context context = getContext();
         messagedatabase = new Messagedatabase(context);

        /**
         * Create a write able database which will trigger its
         * creation if it doesn't already exist.
         */

        db = messagedatabase.getWritableDatabase();
        return (db == null)? false:true;
    }


    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        Cursor cursor = db.query(Messagedatabase.Table_Name,projection,selection,selectionArgs,null,null,null);

        return cursor;
    }


    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }


    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long l =db.insert(messagedatabase.Table_Name,null,values);
        return uri;
    }


    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }


    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
