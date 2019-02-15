package com.android7.myapplication;

import android.app.Activity;
import android.util.Log;

public class DrawingThread extends Thread {

    Activity mActivity;
    private boolean isWorking;

    private int mNumber = 0;

    DrawingThread(Activity activity){
        mActivity = activity;

        isWorking = true;
    }

    @Override
    public void run() {
        while(isWorking){
            Log.i("thread","thread: "+mNumber++);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
