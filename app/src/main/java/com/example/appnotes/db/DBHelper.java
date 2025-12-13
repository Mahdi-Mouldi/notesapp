package com.example.appnotes.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "NoteMD.db";
    private static final int DATABASE_VERSION = 2;

    // Création de la table users
    private static final String SQL_CREATE_USERS =
            "CREATE TABLE users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "email TEXT NOT NULL UNIQUE, " +
                    "password_hash TEXT NOT NULL" +
                    ");";

    // Création de la table tasks
    public static final String SQL_CREATE_TASKS =
            "CREATE TABLE tasks (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "user_id INTEGER NOT NULL, " +
                    "task TEXT NOT NULL, " +
                    "category TEXT, " +
                    "priority TEXT, " +
                    "notes TEXT, " +
                    "due_date TEXT, " +
                    "due_time TEXT, " +
                    "completed INTEGER DEFAULT 0, " +
                    "FOREIGN KEY(user_id) REFERENCES users(id)" +
                    ");";

    private static final String SQL_DROP_USERS = "DROP TABLE IF EXISTS users;";
    private static final String SQL_DROP_TASKS = "DROP TABLE IF EXISTS tasks;";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USERS);
        db.execSQL(SQL_CREATE_TASKS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_TASKS);
        db.execSQL(SQL_DROP_USERS);
        onCreate(db);
    }
}