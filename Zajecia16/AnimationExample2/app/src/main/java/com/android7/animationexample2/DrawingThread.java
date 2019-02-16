package com.android7.animationexample2;

import android.app.Activity;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.TextView;

public class DrawingThread extends Thread {

    Activity mActivity;
    TextView mTextView;

    SurfaceHolder mHolder;

    private boolean isWorking;
    private int number;

    DrawingThread(Activity activity, TextView textView, SurfaceHolder holder){

        mActivity = activity;
        mTextView = textView;

        mHolder = holder;

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

            Canvas canvas = null;

            try{
                canvas = mHolder.lockCanvas();

                if(canvas!=null) {
                    draw(canvas);
                }

            }finally {
                if(canvas!=null){
                    mHolder.unlockCanvasAndPost(canvas);
                }
            }



            number++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void draw(Canvas canvas){

    }
}
