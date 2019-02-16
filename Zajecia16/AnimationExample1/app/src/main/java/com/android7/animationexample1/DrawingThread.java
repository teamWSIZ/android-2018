package com.android7.animationexample1;

import android.util.Log;
import android.widget.TextView;

public class DrawingThread extends Thread {
    private boolean isWorking;
    private int number;

    TextView mTextView;

    public DrawingThread(TextView textView){
        isWorking = true;
        number = 0;

        mTextView = textView;

        mTextView.setText("Number:"+number);
    }

    @Override
    public void run() {

        while(isWorking) {
            Log.i("thread", "Number:"+number);
            number++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
