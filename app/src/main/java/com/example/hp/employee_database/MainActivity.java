package com.example.hp.employee_database;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Declaring variables
    TextView name,age;
    ImageView imageView;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database=new Database(this); // Creating database object


        /*
        BitmapFactory creates Bitmap objects from various sources, including files, streams,
        and byte-arrays.
         */
        //Creating and Initializing object of Employee class
        Employee employee=new Employee("Virat Kohli",28,(BitmapFactory.decodeResource(getResources()
                ,R.drawable.virat)));
        //Calling open method() to open the databse
        database.open();
        // Calling employee_Details() method of Employee class
        database.employee_Details(employee);
        Employee emp=database.getEmployee_Details(); // Creating object

        //Initializing variables by ID
        name=(TextView)findViewById(R.id.name);
        name.setText(emp.getEmployeeName());
        age=(TextView)findViewById(R.id.age);
        age.setText(String.valueOf(emp.getEmployeeAge()));
        imageView=(ImageView)findViewById(R.id.imageView);

        // setImageBitmap Sets a Bitmap as the content of this ImageView.
        imageView.setImageBitmap(emp.imageInByte);
    }
}
