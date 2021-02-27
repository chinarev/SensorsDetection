package com.example.testsensors;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.IOException;

public class Handler {

    public static void saveText(Context ctx, String FILE_NAME, float last_x_acc, float last_y_acc, float last_z_acc, float x_gyr, float y_gyr, float z_gyr) {

        FileOutputStream fos = null;

        try {
            String text = last_x_acc + ", " + last_y_acc + ", " + last_z_acc + ". \n";

            fos = ctx.openFileOutput(FILE_NAME, Context.MODE_APPEND);//MODE_APPEND чтобы не перезаписывать файл
            //fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());
            //Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
        } catch (IOException ex) {

            //Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException ex) {

                //Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
