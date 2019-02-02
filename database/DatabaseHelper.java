package com.example.deepbeast.androidtest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.deepbeast.androidtest.database.model.UserItem;

import java.util.ArrayList;
import java.util.List;

import static com.example.deepbeast.androidtest.database.model.UserItem.TABLE_NAME;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "users_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(UserItem.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }



    public List<UserItem> getAllUser() {
        List<UserItem> userItems = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + UserItem.TABLE_NAME + " ORDER BY " +
                UserItem.COLUMN_NAME + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                UserItem userItem = new UserItem();
                userItem.setUserName(cursor.getString(cursor.getColumnIndex(UserItem.COLUMN_NAME)));
                userItem.setPasscode(cursor.getInt(cursor.getColumnIndex(UserItem.COLUMN_PWD)));
                userItem.setProfile_pic(cursor.getString(cursor.getColumnIndex(UserItem.COLUMN_PIC)));
                userItems.add(userItem);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return userItems;
    }

    public int getUsersCount() {
        String countQuery = "SELECT  * FROM " + UserItem.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public void insertUser(String name,int pwd,String pic) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(UserItem.COLUMN_NAME, name);
        values.put(UserItem.COLUMN_PWD,pwd);
        values.put(UserItem.COLUMN_PIC,pic);

        // insert row
        db.insert(TABLE_NAME, null, values);


        // close db connection
        db.close();

    }

}