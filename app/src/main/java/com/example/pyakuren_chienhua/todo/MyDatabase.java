package com.example.pyakuren_chienhua.todo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Pyakuren-Chienhua on 2017/1/30.
 */

public class MyDatabase extends SQLiteOpenHelper {
    public MyDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE  TABLE main " +
                "(_id INTEGER PRIMARY KEY  NOT NULL , " +
                "title VARCHAR, " +
                "description VARCHAR, " +
                "date VARCHAR," +
                "priority VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table  if exits user");
        onCreate(db);
    }
}
