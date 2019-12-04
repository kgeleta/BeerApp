package com.vastmoths.beerapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.vastmoths.beerapp.database.model.Beer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseCRUD {

    private Context context;

    public DatabaseCRUD(Context context){
        this.context = context;
    }


    public Beer insertBeer(Beer beer){
        long id = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseConstants.COLUMN_NAME, beer.getName());
        contentValues.put(DatabaseConstants.COLUMN_RATE, beer.getRate());
        contentValues.put(DatabaseConstants.COLUMN_TYPE, beer.getType());
        contentValues.put(DatabaseConstants.COLUMN_PICTURE_PATH, beer.getPicturePath());

        try {
            id = sqLiteDatabase.insertOrThrow(DatabaseConstants.TABLE, null, contentValues);
        } catch (SQLiteException e){
            System.out.println(this.getClass().getName() + ": " + e.getMessage());
        } finally {
            sqLiteDatabase.close();
        }

        beer.setId(((int) id));
        return beer;
    }

    public List<Beer> getAllBeers(){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            cursor = sqLiteDatabase.query(DatabaseConstants.TABLE, null, null, null, null, null, null, null);

            if(cursor!=null)
                if(cursor.moveToFirst()){
                    List<Beer> beers = new ArrayList<>();
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(DatabaseConstants.COLUMN_ID));
                        String name = cursor.getString(cursor.getColumnIndex(DatabaseConstants.COLUMN_NAME));
                        int rate = cursor.getInt(cursor.getColumnIndex(DatabaseConstants.COLUMN_RATE));
                        String type = cursor.getString(cursor.getColumnIndex(DatabaseConstants.COLUMN_TYPE));
                        String picturePath = cursor.getString(cursor.getColumnIndex(DatabaseConstants.COLUMN_TYPE));

                        beers.add(new Beer(id, name, rate, type, picturePath));
                    }   while (cursor.moveToNext());

                    return beers;
                }
        } catch (Exception e){
            System.out.println(this.getClass().getName() + ": " + e.getMessage());
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }
}
