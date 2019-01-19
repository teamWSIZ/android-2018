package com.android7.accelerometerexamplea;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager mSensorManager;
    Sensor mAccelerometer;

    TextView mAxTextView;
    TextView mAyTextView;
    TextView mAzTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mAxTextView = findViewById(R.id.ax_value);
        mAyTextView = findViewById(R.id.ay_value);
        mAzTextView = findViewById(R.id.az_value);

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

            mAxTextView.setText("Ax:"+Ax);
            mAyTextView.setText("Ay:"+Ay);
            mAzTextView.setText("Az:"+Az);

            double a = Math.sqrt(Ax*Ax+Ay*Ay+Az*Az);

            Log.i("Accelerometer", "Przyspieszenie:"+a);

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

        mSensorManager.registerListener(this,mAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
    }
}
