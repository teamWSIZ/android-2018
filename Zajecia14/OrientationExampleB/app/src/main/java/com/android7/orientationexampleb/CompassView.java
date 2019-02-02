package com.android7.orientationexampleb;

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
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);

        mCompassBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.compass);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float width = getMeasuredWidth();
        float height = getMeasuredHeight();

        float xcenter = width / 2;
        float ycenter = height / 2;

        mPaint.setColor(Color.WHITE);
        //canvas.drawRect(0, 0, width, height, mPaint);


        mPaint.setColor(Color.GRAY);

        int r = (int)(Math.min(width / 2, height / 2) * 0.8f);

        /*canvas.save();

        canvas.translate(xcenter, ycenter);
        canvas.drawCircle(0, 0, r, mPaint);

        canvas.restore();*/

        canvas.save();

        canvas.translate((width-2*r)/2,(height-2*r)/2);

        canvas.drawBitmap(mCompassBitmap,null,new Rect(0,0,2*r,2*r),null);

        canvas.restore();

        mPaint.setColor(Color.BLUE);

        canvas.drawRect(new Rect(0,0,200,200),mPaint);


        /*canvas.drawLine(xcenter, ycenter, xcenter + radius * (float) Math.cos(mAzimuth),
                ycenter + radius * (float) Math.sin(mAzimuth), mPaint);*/
    }

    public void update(float azimuth){
        mAzimuth = azimuth;

        invalidate();
    }
}
