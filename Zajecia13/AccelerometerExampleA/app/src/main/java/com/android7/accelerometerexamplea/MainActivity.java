package com.android7.accelerometerexamplea;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mSensorManager.registerListener(this,mAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        Sensor sensor = sensorEvent.sensor;

        if(sensor.getType()==Sensor.TYPE_ACCELEROMETER) {
            double Ax = sensorEvent.values[0];
            double Ay = sensorEvent.values[1];
            double Az = sensorEvent.values[2];

            Log.i("Accelerometer", "Ax: "+Ax+", Ay: "+Ay+", Az: "+Az);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
