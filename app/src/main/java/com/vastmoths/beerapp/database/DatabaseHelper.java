package com.vastmoths.beerapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = DatabaseConstants.DATABASE;
    private static DatabaseHelper instance = null;

    public static synchronized DatabaseHelper getInstance(Context context){
        if(instance==null){
            instance = new DatabaseHelper(context);
        }
        return instance;
    }

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableCreation = "CREATE TABLE IF NOT EXISTS " + DatabaseConstants.TABLE + " (" + DatabaseConstants.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DatabaseConstants.COLUMN_NAME + " TEXT, " + DatabaseConstants.COLUMN_RATE + " INTEGER," + DatabaseConstants.COLUMN_TYPE + " TEXT, " + DatabaseConstants.COLUMN_PICTURE_PATH + " TEXT);";
        db.execSQL(tableCreation);
        System.out.println("created database!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
