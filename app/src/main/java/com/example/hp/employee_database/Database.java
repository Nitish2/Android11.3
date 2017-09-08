package com.example.hp.employee_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Hp on 08-09-2017.
 */

//SQLiteOpenHelper create a helper object to create, open, and/or manage a database.
class Database extends SQLiteOpenHelper { // Creating a class and extends from SQLiteOpenHelper

    // Declaring database variables
    private static final int DATABASE_VERSION = 2; // Initializing Database_Version
    public static final String DATABASE_NAME = "database"; // Initializing Database_Name
    public static final String EMPLOYEE_TABLE_NAME = "employee_table";  // Initializing Employee_Table
    public static final String EMPLOYEE_NAME = "employee_Name";  // Initializing Employee_Name
    public static final String EMPLOYEE_ID = "id";  // Initializing Employee_ID
    public static final String EMPLOYEE_AGE = "employee_Age"; // Initializing Employee_Age
    private static final String EMPLOYEE_IMAGE = "employee_Image"; // Initializing Employee_Image

    private static final String CREATE_EMPLOYEE_TABLE = ("CREATE TABLE " + EMPLOYEE_TABLE_NAME + " "
            + "(" + EMPLOYEE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + EMPLOYEE_NAME + " text,"
            + EMPLOYEE_AGE + " text,"
            + EMPLOYEE_IMAGE + " BLOB NOT NULL );");

    private SQLiteDatabase database = null; // Creating object

    public Database(Context context) { //Creating Method
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // onCreate is called when the database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase database) {
        //creating table in db
        database.execSQL(CREATE_EMPLOYEE_TABLE);
        this.database = database;

    }


    //onUpgrade method is called when the database needs to be upgraded
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS employees");
        onCreate(database);
    }

    // openDB method is called when the database has been opened
    public void open() {
        if (this.database == null) {
            this.database = this.getWritableDatabase();
        }
    }

    //close() method is used to close any open database object.
    public void close() {
        if (this.database != null) {
            if (this.database.isOpen())
                this.database.close();
        }
    }

    public boolean employee_Details(Employee employee) { //Creating method
        //ContentValues is used to get the values from database tables
        ContentValues contentValues = new ContentValues(); //Creating Object

        contentValues.put(EMPLOYEE_NAME, employee.getEmployeeName());
        contentValues.put(EMPLOYEE_AGE, employee.getEmployeeAge());
        contentValues.put(EMPLOYEE_IMAGE, Blob_Format.getBytes(employee.getimageInByte()));
        // Inserting Employee_Table_Name into database
        database.insert(EMPLOYEE_TABLE_NAME, null, contentValues);
        return true;
    }

    public Employee getEmployee_Details() throws SQLException { // Creating method

        //Cursor exposes results from a query on a SQLiteDatabase.
        Cursor cursor = database.query(true, EMPLOYEE_TABLE_NAME, new String[]{EMPLOYEE_IMAGE,
                EMPLOYEE_NAME, EMPLOYEE_AGE}, null, null, null, null, null, null);

        if (cursor.moveToLast()) { // If statement
            String employeeName = cursor.getString(cursor.getColumnIndex(EMPLOYEE_NAME));
            int employeeAge = cursor.getInt(cursor.getColumnIndex(EMPLOYEE_AGE));
            byte[] blob = cursor.getBlob(cursor.getColumnIndex(EMPLOYEE_IMAGE));
            cursor.close();
            return new Employee(employeeName, employeeAge,Blob_Format.getPhoto(blob));

        }
        cursor.close();
        return null; // Return statement
    }
}