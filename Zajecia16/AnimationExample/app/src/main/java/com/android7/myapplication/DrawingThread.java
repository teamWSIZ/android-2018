package com.android7.myapplication;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.TextView;

public class DrawingThread extends Thread {

    Activity mActivity;
    private boolean isWorking;

    private int mNumber = 0;

    TextView mTextView;

    SurfaceHolder mHolder;

    Paint mPaint;

    DrawingThread(Activity activity, TextView textView, SurfaceHolder holder){
        mActivity = activity;
        mTextView = textView;

        mHolder = holder;

        isWorking = true;

        mTextView.setText("Number: "+mNumber);

        mPaint = new Paint();
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


            Canvas canvas = null;

            try{
                canvas = mHolder.lockCanvas();

                if(canvas!=null){
                    draw(canvas);
                }

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

    private void draw(Canvas canvas){
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(0,0,200,200,mPaint);
    }
}
