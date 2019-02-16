package com.android7.animationexample2;

import android.util.Log;

public class DrawingThread extends Thread {
    @Override
    public void run() {
        Log.i("thread","thread");
    }
}
