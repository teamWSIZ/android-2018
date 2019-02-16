package com.android7.animationexample2;

import android.util.Log;
import android.widget.TextView;

public class DrawingThread extends Thread {

    private boolean isWorking;
    private int number;

    TextView mTextView;

    DrawingThread(TextView textView){
        mTextView = textView;

        mTextView.setText("Number: "+number);

        isWorking = true;
        number = 0;

    }


    @Override
    public void run() {

        while(isWorking) {
            Log.i("thread", "Number: "+number);
            number++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
