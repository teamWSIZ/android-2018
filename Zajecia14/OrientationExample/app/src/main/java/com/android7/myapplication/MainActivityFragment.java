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

    private CompassView mCompassView;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mCompassView = new CompassView(getContext());

        return mCompassView;

        //return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mSensorManager = (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);
        mOrientationSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        mSensorManager.registerListener(this,mOrientationSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float azimuth = (float)(sensorEvent.values[0]*180/Math.PI);

        Log.i("Compass","Compass:"+azimuth);
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
