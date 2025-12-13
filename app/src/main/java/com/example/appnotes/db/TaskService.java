package com.example.appnotes.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private final DBHelper dbHelper;
    public TaskService(Context context){
        this.dbHelper=new DBHelper(context.getApplicationContext());
    }
    public long createTask (int userId, String task, String category, String priority, String notes, String dueDate,
    String dueTime, int completed){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("user_id", userId);
        cv.put("task", task);
        cv.put("category", category);
        cv.put("priority", priority);
        cv.put("notes", notes);
        cv.put("dueDate", dueDate);
        cv.put("completed", completed);

        long id = db.insert("tasks", null, cv);
        db.close();
        return id;
    }
    public List<Task> getTasks(int userId){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Task> res = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM tasks WHERE userId = ?",new String[]{String.valueOf(userId)});
        if(c != null){
            while(c.moveToNext()){
                Task task = new Task(
                        c.getInt(c.getColumnIndexOrThrow("id")),
                        c.getInt(c.getColumnIndexOrThrow("user_id")),
                        c.getString(c.getColumnIndexOrThrow("category")),
                        c.getString(c.getColumnIndexOrThrow("priority")),
                        c.getString(c.getColumnIndexOrThrow("notes")),
                        c.getString(c.getColumnIndexOrThrow("dueDate")),
                        c.getInt(c.getColumnIndexOrThrow("completed"))
                );
                res.add(task);
            }
            c.close();
        }
        db.close();
        return res;
    }
    public void updateTask(int id, String task, String category, String priority, String notes, String dueDate,
                           String dueTime, int completed){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("task", task);
        cv.put("category", category);
        cv.put("priority", priority);
        cv.put("priority", priority);
        cv.put("notes", notes);
        cv.put("dueDate", dueDate);
        cv.put("dueTime", dueTime);
        cv.put("completed", completed);

        db.update("tasks", cv, "id=?", new String[]{String.valueOf(id)});
        db.close();
    }
    public void deleteTask(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("tasks", "id=?", new String[]{String.valueOf(id)});
        db.close();
    }
    public  Task getTaskById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM tasks WHERE id = ?", new String[]{String.valueOf(id)});

        if (c.moveToFirst()) {
             Task task = new Task(
                     c.getInt(c.getColumnIndexOrThrow("id")),
                     c.getInt(c.getColumnIndexOrThrow("user_id")),
                     c.getString(c.getColumnIndexOrThrow("category")),
                     c.getString(c.getColumnIndexOrThrow("priority")),
                     c.getString(c.getColumnIndexOrThrow("notes")),
                     c.getString(c.getColumnIndexOrThrow("dueDate")),
                     c.getInt(c.getColumnIndexOrThrow("completed")));

                     c.close();
            db.close();
            return task;
        }

        c.close();
        db.close();
        return null;
    }

};
