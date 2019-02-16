package com.android7.animationexample1;

import android.app.Activity;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.TextView;

public class DrawingThread extends Thread {
    private boolean isWorking;
    private int number;

    Activity mActivity;

    SurfaceHolder mHolder;

    TextView mTextView;

    public DrawingThread(Activity activity, TextView textView, SurfaceHolder holder){
        isWorking = true;
        number = 0;

        mTextView = textView;
        mActivity = activity;

        mHolder = holder;

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

            Canvas canvas = null;

            try {
                canvas = mHolder.lockCanvas();

                

            }finally {
                if(canvas!=null)
                    mHolder.unlockCanvasAndPost(canvas);
            }


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
