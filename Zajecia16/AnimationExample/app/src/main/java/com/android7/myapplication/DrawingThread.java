package com.android7.myapplication;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

public class DrawingThread extends Thread {

    Activity mActivity;
    private boolean isWorking;

    private int mNumber = 0;

    TextView mTextView;

    DrawingThread(Activity activity, TextView textView){
        mActivity = activity;
        mTextView = textView;

        isWorking = true;

        mTextView.setText("Number: "+mNumber);
    }

    @Override
    public void run() {
        while(isWorking){
            Log.i("thread","thread: "+mNumber++);

            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mTextView.setText("Number: "+mNumber);
                }
            });

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
