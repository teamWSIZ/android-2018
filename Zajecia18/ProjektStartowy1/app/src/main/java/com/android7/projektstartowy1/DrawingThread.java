package com.android7.projektstartowy1;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.widget.TextView;

public class DrawingThread extends Thread {

    Activity mActivity;
    TextView mTextView;

    SurfaceHolder mHolder;

    private boolean isWorking;
    private int number;

    private Paint mPaint;

    DrawingThread(Activity activity, TextView textView, SurfaceHolder holder){

        mActivity = activity;
        mTextView = textView;

        mHolder = holder;

        mPaint = new Paint();

        mTextView.setText("Number: "+number);

        isWorking = true;
        number = 0;
    }


    @Override
    public void run() {

        while(isWorking) {

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
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void draw(Canvas canvas){
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(),mPaint);

        String s0,s1,s2,s3,s4;

        s0="Napis 1";
        s1="Napis 2";
        s2="Napis 3";
        s3="Napis 4";
        s4="Napis 5";

        mPaint.setTextSize(50);

        Rect bounds = new Rect();
        mPaint.getTextBounds(s0,0,s0.length(),bounds);

        mPaint.setColor(Color.WHITE);

        float y = 0+20;

        y=bounds.height();

        canvas.drawText(s0+"  Text width: "+bounds.width()+" Text height:"+bounds.height(),0,y,mPaint);


        //canvas.drawText("Text width: "+bounds.width()+" Text height:"+bounds.height(),0,bounds.height(),mPaint);
    }
}
