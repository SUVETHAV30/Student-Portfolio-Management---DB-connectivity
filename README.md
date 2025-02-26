# Student-Portfolio-Management---DB-connectivity
Student Portfolio Management App

Overview
The Student Portfolio Management app is an Android application that enables students to efficiently manage their academic records, projects, and achievements. It leverages SQLite for secure and reliable local data storage.

Features
Add, update, delete, and view student details.
Store academic records, project details, and achievements.
SQLite database integration for offline access.
Simple and user-friendly UI.

Technologies Used
Programming Language: Java
IDE: Android Studio
Database: SQLite
UI Components: RecyclerView, EditText, Buttons

Prerequisites
Install Android Studio.
Minimum API Level 21 (Android 5.0 Lollipop).
Enable USB Debugging on a physical device or use an emulator.
Installation

Clone the repository:
git clone https://github.com/your-repo/student-portfolio-management.git

Open the project in Android Studio.
Build and run the project on an emulator or physical device.

Database Schema
The app uses an SQLite database with the following schema:

CREATE TABLE students (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
    phone TEXT NOT NULL,
    department TEXT NOT NULL,
    year INTEGER NOT NULL
);

Code Implementation
Database Helper Class

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "StudentPortfolio.db";
    public static final String TABLE_NAME = "students";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, phone TEXT, department TEXT, year INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

How to Use
Launch the App: Open the app on your Android device.
Add Student Data: Enter details like name, email, phone, department, and academic year.
Manage Portfolio: Add projects, achievements, and other academic records.
Update/Delete Records: Modify or remove student details from the database.

Future Enhancements
Implement Firebase for cloud storage.
Add image uploads for profile pictures.
Integrate PDF generation for student resumes.
Provide export/import database functionality.

Contributing Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.
License
This project is licensed under the MIT License.


