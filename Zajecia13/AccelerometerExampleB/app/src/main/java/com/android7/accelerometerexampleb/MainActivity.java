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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        Sensor proximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        mSensorManager.registerListener(this,mAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        //mSensorManager.registerListener(this,proximity,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if(sensorEvent.sensor.getType()==Sensor.TYPE_ACCELEROMETER) {

            double Fx = sensorEvent.values[0];
            double Fy = sensorEvent.values[1];
            double Fz = sensorEvent.values[2];

            Log.d("Sensor", "Fx: "+Fx+", Fy: "+Fy+", Fz: "+Fz);

        }

        if(sensorEvent.sensor.getType()==Sensor.TYPE_PROXIMITY) {

            double proximity = sensorEvent.values[0];
            Log.d("Sensor", "proximity:"+proximity);
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onPause() {
        super.onPause();

        mSensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i("Accelerometer Example","Resumed...");
        mSensorManager.registerListener(this,mAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
    }
}
