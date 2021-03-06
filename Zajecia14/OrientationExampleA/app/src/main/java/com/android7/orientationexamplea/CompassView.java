package com.android7.orientationexamplea;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class CompassView extends View {

    private Paint mPaint;
    private float mAzimuth = 0;

    private Bitmap mCompassBackground;

    private Bitmap mCompassHandlebar;
    private Bitmap mCompassCoordinates;

    public CompassView(Context context) {
        super(context);

        mPaint = new Paint();
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);

        mCompassBackground = BitmapFactory.decodeResource(getResources(),R.drawable.compass_background);
        mCompassHandlebar = BitmapFactory.decodeResource(getResources(),R.drawable.compass_handlebar_0);
        mCompassCoordinates = BitmapFactory.decodeResource(getResources(),R.drawable.compass_coordinates);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int xcenter = getMeasuredWidth() / 2;
        int ycenter = getMeasuredHeight() / 2;

        int w = getMeasuredWidth();
        int h = getMeasuredHeight();

        int r = (int) (Math.min(xcenter, ycenter) * 0.9);

        mPaint.setColor(Color.WHITE);
        canvas.drawRect(new Rect(0,0,getMeasuredWidth(),getMeasuredHeight()),mPaint);

        canvas.save();

        canvas.translate(xcenter,ycenter);

        canvas.translate(-r,-r);
        canvas.drawBitmap(mCompassBackground,null,new Rect(0,0,2*r,2*r),null);
        canvas.translate(r,r);

        mPaint.setColor(Color.BLUE);
        //canvas.drawCircle(0,0,20,mPaint);

        canvas.rotate(mAzimuth);

        canvas.translate(200,200);

        canvas.drawCircle(0,0,20,mPaint);

        mPaint.setTextSize(100);

        canvas.drawText("tekst",0,0,mPaint);

        canvas.translate(-200,-200);

        canvas.rotate(90);

        canvas.translate(200,200);

        canvas.drawCircle(0,0,20,mPaint);

        canvas.translate(-200,-200);

        canvas.rotate(90);

        canvas.translate(200,200);

        canvas.drawCircle(0,0,20,mPaint);

        canvas.translate(-200,-200);

        canvas.rotate(90);

        canvas.translate(200,200);

        canvas.drawCircle(0,0,20,mPaint);



        canvas.rotate(mAzimuth);

        canvas.translate(-r,-r);


        //canvas.drawBitmap(mCompassHandlebar,null,new Rect(0,0,2*r,2*r),null);
        //canvas.drawBitmap(mCompassCoordinates,null,new Rect(0,0,2*r,2*r),null);

        canvas.restore();

        //canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        /*mPaint.setColor(Color.BLUE);

        canvas.drawLine(
                xcenter,
                ycenter,
                xcenter + radius * (float)Math.sin(-mAzimuth),
                (ycenter - radius * (float)Math.cos(-mAzimuth)), mPaint);*/
    }

    public void update(float azimuth){
        mAzimuth = azimuth;

        invalidate();
    }
}
