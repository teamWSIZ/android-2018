package com.android7.animationsandsensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;

    private Sensor mAccelerometer;
    private Sensor mMagneticFieldSensor;

    private float[] mGravity = new float[3];
    private float[] mMagneticField = new float[3];

    private float[] mRotationMatrix = new float[9];
    private float[] mOrientationVector = new float[3];

    private boolean mNewGravity = false;
    private boolean mNewMagneticField = false;

    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        DrawingSurface surface = findViewById(R.id.drawingSurface);
        mTextView = findViewById(R.id.textView);

        DrawingThread thread = new DrawingThread(this, mTextView, surface.getHolder());
        thread.start();

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagneticFieldSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(this, mMagneticFieldSensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        boolean redraw = false;

        switch (sensorEvent.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:

                mNewGravity = true;
                System.arraycopy(sensorEvent.values, 0, mGravity, 0, 3);

                break;
            case Sensor.TYPE_MAGNETIC_FIELD:

                mNewMagneticField = true;
                redraw = true;

                System.arraycopy(sensorEvent.values, 0, mMagneticField, 0, 3);
                break;
        }

        if (mNewGravity && mNewMagneticField && redraw) {
            SensorManager.getRotationMatrix(mRotationMatrix, null, mGravity, mMagneticField);
            SensorManager.getOrientation(mRotationMatrix, mOrientationVector);

            float angleZ = -(float) Math.toDegrees(mOrientationVector[0]);
            float angleX = -(float) Math.toDegrees(mOrientationVector[1]);
            float angleY = -(float) Math.toDegrees(mOrientationVector[2]);

            mTextView.setText("rotation ("+angleZ+", "+angleX+", "+angleY+")");
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

        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(this, mMagneticFieldSensor, SensorManager.SENSOR_DELAY_GAME);
    }
}
