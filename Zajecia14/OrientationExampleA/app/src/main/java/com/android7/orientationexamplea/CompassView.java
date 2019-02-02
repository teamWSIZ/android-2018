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

    private Bitmap mCompassBitmap;

    public CompassView(Context context) {
        super(context);

        mPaint = new Paint();
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);

        mCompassBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.compass);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int xcenter = getMeasuredWidth() / 2;
        int ycenter = getMeasuredHeight() / 2;

        float radius = (float) (Math.min(xcenter, ycenter) * 0.7);

        canvas.drawBitmap(mCompassBitmap,null,new Rect(0,0,400,400),null);

        mPaint.setColor(Color.WHITE);
        //canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mPaint.setColor(Color.GRAY);
        canvas.drawCircle(xcenter, ycenter, radius, mPaint);

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
