package com.example.testsensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager AccSensorManager;
    private SensorManager GyrSensorManager;
    private Sensor senAccelerometer;
    private Sensor senGyroscope;
    private long lastUpdate = 0;
    private float last_x_acc, last_y_acc, last_z_acc;
    private static final int SHAKE_THRESHOLD = 600;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AccSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        GyrSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = AccSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senGyroscope = GyrSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        AccSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        GyrSensorManager.registerListener(this, senGyroscope, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x_acc = sensorEvent.values[0];
            float y_acc = sensorEvent.values[1];
            float z_acc = sensorEvent.values[2];
            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                float speed = Math.abs(x_acc + y_acc + z_acc - last_x_acc - last_y_acc - last_z_acc)/ diffTime * 10000;

                TextView text = (TextView)findViewById(R.id.textView4);
                text.setText(""+last_x_acc);
                text = (TextView)findViewById(R.id.textView5);
                text.setText(""+last_y_acc);
                text = (TextView)findViewById(R.id.textView6);
                text.setText(""+last_z_acc);
                text = (TextView)findViewById(R.id.textView7);
                text.setText(""+curTime);
                text = (TextView)findViewById(R.id.textView8);
                text.setText(""+speed);

                last_x_acc = x_acc;
                last_y_acc = y_acc;
                last_z_acc = z_acc;

            }
        }

        if (mySensor.getType() == Sensor.TYPE_GYROSCOPE) {
            float x_gyr = sensorEvent.values[0];
            float y_gyr = sensorEvent.values[1];
            float z_gyr = sensorEvent.values[2];

            TextView text = (TextView) findViewById(R.id.textView9);
            text.setText("" + x_gyr);
            text = (TextView) findViewById(R.id.textView10);
            text.setText("" + y_gyr);
            text = (TextView) findViewById(R.id.textView11);
            text.setText("" + z_gyr);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    protected void onPause() {
        super.onPause();
        AccSensorManager.unregisterListener(this);
        GyrSensorManager.unregisterListener(this);
    }

    protected void onResume() {
        super.onResume();
        AccSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        GyrSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
}