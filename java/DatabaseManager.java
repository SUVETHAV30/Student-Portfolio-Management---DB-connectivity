package com.example.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseManager(Context context) {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public long insertStudent(String name, int age) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("age", age);
        return database.insert("students", null, values);
    }

    public Cursor getAllStudents() {
        return database.query("students", null, null, null, null, null, null);
    }

    public void updateLastStudent(String newName, int newAge) {
        database.execSQL("UPDATE students SET name = ?, age = ? WHERE id = (SELECT MAX(id) FROM students)",
                new Object[]{newName, newAge});
    }

    public void deleteLastStudent() {
        database.execSQL("DELETE FROM students WHERE id = (SELECT MAX(id) FROM students)");
    }
}
