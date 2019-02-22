package com.android7.animationsandsensors;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.widget.TextView;

import java.util.ArrayList;

public class DrawingThread extends Thread {

    Activity mActivity;
    TextView mTextView;

    SurfaceHolder mHolder;

    private boolean isWorking;
    private int number;

    private Paint mPaint;

    float mAngle;

    ArrayList<Ball> mBalls;

    DrawingThread(Activity activity, TextView textView, SurfaceHolder holder){

        mActivity = activity;
        mTextView = textView;

        mHolder = holder;

        mPaint = new Paint();

        mTextView.setText("Number: "+number);

        isWorking = true;
        number = 0;

        mAngle = 0;

        mBalls = new ArrayList<Ball>();

        for(int i=0;i<100;i++)
            mBalls.add(new Ball());

    }


    @Override
    public void run() {

        while(isWorking) {

            /*mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mTextView.setText("Number: "+number);
                }
            });*/

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
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void draw(Canvas canvas){
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(),mPaint);

        mPaint.setTextSize(50);

        mPaint.setColor(Color.WHITE);

        canvas.translate(canvas.getWidth()/2,canvas.getHeight()/2);
        canvas.rotate(mAngle);

        mAngle++;

        canvas.drawText("Number: "+number,0,0,mPaint);

        for(Ball ball : mBalls) {
            ball.evaluate(1);
            ball.draw(canvas);
        }
    }
}
