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
        canvas.drawRect(0, 0, width, height, mPaint);

        int r = (int)(Math.min(width / 2, height / 2) * 0.8f);

        canvas.translate(xcenter,ycenter);

        canvas.rotate(45);

        canvas.translate(-r,-r);

        canvas.drawBitmap(mCompassBitmap,null,new Rect(0,0,2*r,2*r),null);
    }

    public void update(float azimuth){
        mAzimuth = azimuth;

        invalidate();
    }
}
