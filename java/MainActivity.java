package com.example.sqliteapp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private DatabaseManager databaseManager;
    private EditText editName, editAge;
    private Button btnInsert, btnUpdateLast, btnDeleteLast, btnViewAll;
    private TextView txtRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        editName = findViewById(R.id.editName);
        editAge = findViewById(R.id.editAge);
        btnInsert = findViewById(R.id.btnInsert);
        btnUpdateLast = findViewById(R.id.btnUpdateLast);
        btnDeleteLast = findViewById(R.id.btnDeleteLast);
        btnViewAll = findViewById(R.id.btnViewAll);
        txtRecords = findViewById(R.id.txtRecords);

        // Initialize DatabaseManager
        databaseManager = new DatabaseManager(this);

        // Insert Data
        btnInsert.setOnClickListener(v -> {
            String name = editName.getText().toString().trim();
            String ageStr = editAge.getText().toString().trim();
            if (name.isEmpty() || ageStr.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter name and age", Toast.LENGTH_SHORT).show();
                return;
            }
            int age = Integer.parseInt(ageStr);
            long result = databaseManager.insertStudent(name, age);
            if (result != -1) {
                Toast.makeText(MainActivity.this, "Student inserted successfully!", Toast.LENGTH_SHORT).show();
                editName.setText("");
                editAge.setText("");
            } else {
                Toast.makeText(MainActivity.this, "Failed to insert student.", Toast.LENGTH_SHORT).show();
            }
        });

        // Update Last Record
        btnUpdateLast.setOnClickListener(v -> {
            String newName = editName.getText().toString().trim();
            String newAgeStr = editAge.getText().toString().trim();
            if (newName.isEmpty() || newAgeStr.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter new name and age to update", Toast.LENGTH_SHORT).show();
                return;
            }
            int newAge = Integer.parseInt(newAgeStr);
            databaseManager.updateLastStudent(newName, newAge);
            Toast.makeText(MainActivity.this, "Last student updated successfully!", Toast.LENGTH_SHORT).show();
        });

        // Delete Last Record
        btnDeleteLast.setOnClickListener(v -> {
            databaseManager.deleteLastStudent();
            Toast.makeText(MainActivity.this, "Last student deleted successfully!", Toast.LENGTH_SHORT).show();
        });

        // View All Records
        btnViewAll.setOnClickListener(v -> {
            Cursor cursor = databaseManager.getAllStudents();
            StringBuilder records = new StringBuilder();
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex("id"));
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    int age = cursor.getInt(cursor.getColumnIndex("age"));
                    records.append("ID: ").append(id)
                            .append(", Name: ").append(name)
                            .append(", Age: ").append(age)
                            .append("\n");
                } while (cursor.moveToNext());
                cursor.close();
            } else {
                records.append("No records found.");
            }
            txtRecords.setText(records.toString());
        });
    }
}


