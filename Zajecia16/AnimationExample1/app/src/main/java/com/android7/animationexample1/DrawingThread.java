package com.android7.animationexample1;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.TextView;

public class DrawingThread extends Thread {
    private boolean isWorking;
    private int number;

    Activity mActivity;

    SurfaceHolder mHolder;

    TextView mTextView;

    Paint mPaint;

    private float mAngle;

    public DrawingThread(Activity activity, TextView textView, SurfaceHolder holder){
        isWorking = true;
        number = 0;

        mTextView = textView;
        mActivity = activity;

        mHolder = holder;

        mTextView.setText("Number:"+number);

        mPaint = new Paint();

        mAngle = 0;
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

                if(canvas!=null)
                    draw(canvas);


            }finally {
                if(canvas!=null)
                    mHolder.unlockCanvasAndPost(canvas);
            }


            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    private void draw(Canvas canvas){
        mPaint.setColor(Color.GRAY);
        mPaint.setTextSize(50);

        canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(),mPaint);

        mPaint.setColor(Color.BLUE);

        canvas.translate(canvas.getWidth()/2,canvas.getHeight()/2);
        canvas.rotate(mAngle);
        mAngle++;

        canvas.drawText("Number: "+number,100,100,mPaint);


    }
}
