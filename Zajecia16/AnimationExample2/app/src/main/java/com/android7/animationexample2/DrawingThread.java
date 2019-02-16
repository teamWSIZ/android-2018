package com.android7.animationexample2;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

public class DrawingThread extends Thread {

    Activity mActivity;
    TextView mTextView;

    private boolean isWorking;
    private int number;

    DrawingThread(Activity activity, TextView textView){

        mActivity = activity;
        mTextView = textView;

        mTextView.setText("Number: "+number);

        isWorking = true;
        number = 0;

    }


    @Override
    public void run() {

        while(isWorking) {

            Log.i("thread", "Number: "+number);

            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mTextView.setText("Number: "+number);
                }
            });

            number++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
