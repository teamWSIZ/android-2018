package com.android7.myapplication;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mOrientationSensor;

    private Sensor mAccelerometer;
    private Sensor mMagneticFieldSensor;

    private CompassView mCompassView;

    private float[] mGravity = new float[3];
    private float[] mMagneticField = new float[3];

    private boolean mNewGravity = false;
    private boolean mNewMagneticField = false;

    private float [] mRotationMatrix = new float[9];

    private float [] mRotationVector = new float[3];

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mCompassView = view.findViewById(R.id.compass_view);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mSensorManager = (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);


        mOrientationSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagneticFieldSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);


        mSensorManager.registerListener(this,mAccelerometer,SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(this, mMagneticFieldSensor,SensorManager.SENSOR_DELAY_GAME);

        //mSensorManager.registerListener(this,mOrientationSensor,SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        switch(sensorEvent.sensor.getType()){
            case Sensor.TYPE_ACCELEROMETER:

                System.arraycopy(sensorEvent.values,0,mGravity,0,3);
                mNewGravity = true;

                break;
            case Sensor.TYPE_MAGNETIC_FIELD:

                System.arraycopy(sensorEvent.values,0,mMagneticField,0,3);
                mNewMagneticField = true;

                break;
        }

        if(mNewGravity&&mNewMagneticField){
            SensorManager.getRotationMatrix(mRotationMatrix,null,mGravity,mMagneticField);
            SensorManager.getOrientation(mRotationMatrix,mRotationVector);

            mCompassView.update(-(float)Math.toDegrees(mRotationVector[0]));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onPause() {
        super.onPause();

        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        mSensorManager.registerListener(this,mOrientationSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }
}
