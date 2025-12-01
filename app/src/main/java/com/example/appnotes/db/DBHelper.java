package com.example.appnotes.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "NoteMD.db";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_USER_TABLE =
            "CREATE TABLE users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "email TEXT NOT NULL UNIQUE, " +
                    "password_hash TEXT NOT NULL" +
                    ");";

    private static final String SQL_DROP_USER_TABLE = "DROP TABLE IF EXISTS users;";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USER_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_USER_TABLE);
        onCreate(db);
    }
}