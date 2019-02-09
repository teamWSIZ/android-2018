package com.android7.orientationexampleb;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mOrientationSensor;

    private Sensor mAccelerometer;
    private Sensor mMagneticField;

    private CompassView mCompassView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCompassView = new CompassView(this);

        setContentView(mCompassView);

        //setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagneticField = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        mOrientationSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        mSensorManager.registerListener(this,mAccelerometer,SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(this,mMagneticField,SensorManager.SENSOR_DELAY_GAME);

        mSensorManager.registerListener(this,mOrientationSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        switch(sensorEvent.sensor.getType()){
            case Sensor.TYPE_ACCELEROMETER:
                Log.i("Compass","Accelerometer");
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                Log.i("Compass","Magnetic field");
                break;
            case Sensor.TYPE_ORIENTATION:
                Log.i("Compass","Orientation");
        }

        //mCompassView.update(-azimuth*180/(float)Math.PI);
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

        mSensorManager.registerListener(this,mAccelerometer,SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(this,mMagneticField,SensorManager.SENSOR_DELAY_GAME);

        mSensorManager.registerListener(this,mOrientationSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }
}
