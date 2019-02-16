package com.android7.animationexample1;

import android.util.Log;

public class DrawingThread extends Thread {
    private boolean isWorking;
    private int number;

    public DrawingThread(){
        isWorking = true;
        number = 0;
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
