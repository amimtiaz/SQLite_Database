package com.imtiaz_acedamy.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "my_database";
    public static final int DB_VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table my_table (id INTEGER primary key autoincrement, name TEXT, mobile TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" drop table if exists my_table ");

    }

    public void insertData(String name, String mobile) {

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues conval = new ContentValues();

        conval.put("name", name);
        conval.put("mobile", mobile);

        database.insert("my_table", null, conval);

    }



    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from my_table ", null);
        return cursor;
    }

    public Cursor searchDataById(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from my_table where id like '%" + id + "%' ", null);
        return cursor;

    }


    public Cursor searchDataByName(String name) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from my_table where name like '%" + name + "%' ", null);
        return cursor;

    }

}
