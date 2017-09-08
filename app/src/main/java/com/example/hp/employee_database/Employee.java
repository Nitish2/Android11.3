package com.example.hp.employee_database;

import android.graphics.Bitmap;

/**
 * Created by Hp on 08-09-2017.
 */

class Employee { // Creating employee class

    //Declaring variables
    String empName;
    int empAge;
    Bitmap imageInByte;

    public Employee(String empName, int empAge, Bitmap imageInByte){ // Creating method
        // Giving reference to the objects
        this.empName = empName;
        this.empAge = empAge;
        this.imageInByte = imageInByte;
    }

    // get() method for name
    public String getEmployeeName() {
        return empName;
    }


    // get() method for age
    public int getEmployeeAge() {
        return empAge;
    }


    // get() method for image
    public Bitmap getimageInByte() {
        return imageInByte;
    }


}
