package com.android7.accelerometerexampleb;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager mSensorManager;
    Sensor mAccelerometer;

    SensorEventListener mAcceleremeterListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            Log.d("Sensor","Accelerometer listener 2");
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mSensorManager.registerListener(this,mAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(mAcceleremeterListener,mAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        Log.d("Sensor","accelerometer");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
