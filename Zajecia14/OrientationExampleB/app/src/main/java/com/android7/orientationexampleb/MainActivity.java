package com.android7.orientationexampleb;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mOrientationSensor;

    private Sensor mAccelerometer;
    private Sensor mMagneticFieldSensor;

    private float[] mGravity = new float[3];
    private float[] mMagneticField = new float[3];

    private float[] mRotationMatrix = new float[9];
    private float[] mOrientationVector = new float[3];

    private boolean mNewGravity = false;
    private boolean mNewMagneticField = false;

    private CompassView mCompassView;
    private ImageView mCompassImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //mCompassView = new CompassView(this);

        //setContentView(mCompassView);

        setContentView(R.layout.activity_main);

        mCompassView = findViewById(R.id.compass_view);
        mCompassImage = findViewById(R.id.compass_image);

        mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagneticFieldSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        mOrientationSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        mSensorManager.registerListener(this,mAccelerometer,SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(this, mMagneticFieldSensor,SensorManager.SENSOR_DELAY_GAME);

        mSensorManager.registerListener(this,mOrientationSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        boolean redraw = false;

        switch(sensorEvent.sensor.getType()){
            case Sensor.TYPE_ACCELEROMETER:

                mNewGravity = true;
                System.arraycopy(sensorEvent.values,0,mGravity,0,3);

                break;
            case Sensor.TYPE_MAGNETIC_FIELD:

                mNewMagneticField = true;
                redraw = true;

                System.arraycopy(sensorEvent.values,0,mMagneticField,0,3);
                break;
            case Sensor.TYPE_ORIENTATION:
                Log.i("Compass","Orientation");
        }

        if(mNewGravity&&mNewMagneticField&&redraw){
            SensorManager.getRotationMatrix(mRotationMatrix,null,mGravity,mMagneticField);
            SensorManager.getOrientation(mRotationMatrix,mOrientationVector);
        }

        mCompassView.update(-(float)Math.toDegrees(mOrientationVector[0]));
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
        mSensorManager.registerListener(this, mMagneticFieldSensor,SensorManager.SENSOR_DELAY_GAME);

        mSensorManager.registerListener(this,mOrientationSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }
}
