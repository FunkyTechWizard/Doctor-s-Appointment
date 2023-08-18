package com.kashyap.docappointment.DataBase;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kashyap.docappointment.model.Appointment;
import com.kashyap.docappointment.model.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "AppointmentsDB";
    private static final int DATABASE_VERSION = 1;

    // Table name and columns
    public static final String TABLE_APPOINTMENTS = "appointments";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
//    public static final String COLUMN_DOB = "dob";
    public static final String COLUMN_MEDICAL_ISSUE = "medical_issue";
    public static final String COLUMN_APPOINTMENT_DATE = "appointment_date";

    // Create table query
    private static final String CREATE_TABLE_APPOINTMENTS =
            "CREATE TABLE " + TABLE_APPOINTMENTS + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME + " TEXT," +
                    COLUMN_EMAIL + " TEXT," +
//                    COLUMN_DOB + " TEXT," +
                    COLUMN_MEDICAL_ISSUE + " TEXT," +
                    COLUMN_APPOINTMENT_DATE + " INTEGER" +
                    ")";

    // Table name and columns
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    // Create table query
    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USERNAME + " TEXT UNIQUE NOT NULL,"
            + COLUMN_EMAIL + " TEXT NOT NULL," // Add the email column
            + COLUMN_PASSWORD + " TEXT NOT NULL" + ")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_APPOINTMENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_APPOINTMENTS+TABLE_USERS);
        onCreate(db);
    }
    public long insertUser(String username, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_EMAIL, email); // Add email to the ContentValues
        values.put(COLUMN_PASSWORD, password);
        long id = db.insert(TABLE_USERS, null, values);
        db.close();
        return id;
    }


    public boolean checkUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID};
        String selection = COLUMN_USERNAME + " = ?";
        String[] selectionArgs = {username};
        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }
    public User getUserDetails(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_USERNAME, COLUMN_EMAIL};
        String selection = COLUMN_USERNAME + " = ?";
        String[] selectionArgs = {username};
        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        User user = null;

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") int userId = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            @SuppressLint("Range") String userEmail = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
            user = new User(userId, username, userEmail);
        }

        if (cursor != null) {
            cursor.close();
        }

        db.close();
        return user;
    }




    // Method to insert appointment details into the database
    public long insertAppointment(String name, String email, long appointmentDate, String medicalIssue) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EMAIL, email);
//        values.put(COLUMN_DOB, dob);
        values.put(COLUMN_MEDICAL_ISSUE, medicalIssue);
        values.put(COLUMN_APPOINTMENT_DATE, appointmentDate);
        long rowId = db.insert(TABLE_APPOINTMENTS, null, values);
        db.close();
        return rowId;
    }


    // Method to fetch all appointments from the database
    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_APPOINTMENTS, null, null, null, null, null, null);

        if (cursor != null) {
            int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
            int emailIndex = cursor.getColumnIndex(COLUMN_EMAIL);
//            int dobIndex = cursor.getColumnIndex(COLUMN_DOB);
            int medicalIssueIndex = cursor.getColumnIndex(COLUMN_MEDICAL_ISSUE);
            int appointmentDateIndex = cursor.getColumnIndex(COLUMN_APPOINTMENT_DATE);

            while (cursor.moveToNext()) {
                String name = cursor.getString(nameIndex);
                String email = cursor.getString(emailIndex);
//                String dob = cursor.getString(dobIndex);
                String medicalIssue = cursor.getString(medicalIssueIndex);
                long appointmentDate = cursor.getLong(appointmentDateIndex);
                appointments.add(new Appointment(name, email, appointmentDate,medicalIssue));
            }

            cursor.close();
        }

        db.close();
        return appointments;
    }
}