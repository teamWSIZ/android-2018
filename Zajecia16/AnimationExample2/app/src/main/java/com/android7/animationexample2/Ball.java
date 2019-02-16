package com.android7.animationexample2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball {
    private float x,y;
    private float Vx,Vy;

    Paint mPaint;

    Ball(){
        mPaint = new Paint();

        mPaint.setColor(Color.CYAN);

        Vx = (float)(10.0f*Math.random());
        Vy = (float)(10.0f*Math.random());

        x = (float)Math.random()*200.0f;
        y = (float)Math.random()*200.0f;
    }

    public void evaluate(float t){
        x = x + Vx*t;
        y = y + Vy*t;

        if(x<0||x>400)
            Vx = -Vx;

        if(y<0||y>400)
            Vy = -Vy;
    }

    public void draw(Canvas canvas){
        canvas.drawCircle(x,y,20 ,mPaint);
    }
}
