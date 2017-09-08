package com.example.hp.employee_database;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by Hp on 08-09-2017.
 */

class Blob_Format {  // Creating class
    public static byte[] getBytes(Bitmap bitmap){
        // ByteArrayOutputStream class is used to write common data into multiple files.
        ByteArrayOutputStream stream=new ByteArrayOutputStream(); // Creating object
        /*
         compress() method will write a compressed version of the bitmap to the specified
          outputstream.
         */
        bitmap.compress(Bitmap.CompressFormat.PNG,0,stream);
        // Will return a newly allocated Byte array
        return stream.toByteArray();
    }
    // Converting from byte array to bitmap
    public static Bitmap getPhoto(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

}
