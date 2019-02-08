package com.android7.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CompassView extends View {

    Paint mPaint;
    float mAzimuth = 0;

    Bitmap mCompassBitmap;

    private void init(){
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);

        mCompassBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.compass);
    }

    public CompassView(Context context) {
        super(context);

        init();
    }

    public CompassView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int xcenter = getMeasuredWidth() / 2;
        int ycenter = getMeasuredHeight() / 2;

        float radius = (float) (Math.min(xcenter, ycenter) );

        mPaint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        canvas.save();

        //canvas.translate((getMeasuredWidth()-2*radius)/2,(getMeasuredHeight()-2*radius)/2);

        canvas.translate(xcenter,ycenter);
        canvas.rotate(mAzimuth);

        canvas.translate(-radius,-radius);
        canvas.drawBitmap(mCompassBitmap,null,new Rect(0,0,(int)(2*radius),(int)(2*radius)),null);

        canvas.restore();

        mPaint.setColor(Color.BLUE);

        canvas.translate(xcenter,ycenter);

        mPaint.setColor(Color.GRAY);
        //canvas.drawCircle(xcenter, ycenter, radius, mPaint);

        mPaint.setColor(Color.BLUE);

        /*canvas.drawLine(
                xcenter,
                ycenter,
                xcenter + radius * (float)Math.sin(-mAzimuth / 180 * Math.PI),
                (ycenter - radius * (float)Math.cos(-mAzimuth / 180 * Math.PI)), mPaint);*/

    }

    public void update(float azimuth){
        mAzimuth = azimuth;

        invalidate();
    }
}
