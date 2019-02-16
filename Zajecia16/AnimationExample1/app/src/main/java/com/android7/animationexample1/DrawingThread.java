package com.android7.animationexample1;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

public class DrawingThread extends Thread {
    private boolean isWorking;
    private int number;

    Activity mActivity;

    TextView mTextView;

    public DrawingThread(Activity activity, TextView textView){
        isWorking = true;
        number = 0;

        mTextView = textView;
        mActivity = activity;

        mTextView.setText("Number:"+number);
    }

    @Override
    public void run() {

        while(isWorking) {
            Log.i("thread", "Number:"+number);

            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mTextView.setText("Number:"+number);
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
