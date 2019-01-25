package com.android7.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class CompassView extends View {

    Paint mPaint;
    float mAzimuth = 0;

    public CompassView(Context context) {
        super(context);

        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        int xcenter = getMeasuredWidth() / 2;
        int ycenter = getMeasuredHeight() / 2;

        float radius = (float) (Math.max(xcenter, ycenter) * 0.7);

        mPaint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mPaint.setColor(Color.GRAY);
        canvas.drawCircle(xcenter, ycenter, radius, mPaint);

        mPaint.setColor(Color.BLUE);

        canvas.drawLine(
                xcenter,
                ycenter,
                xcenter + radius * (float)Math.sin(-mAzimuth / 180 * Math.PI),
                (ycenter - radius * (float)Math.cos(-mAzimuth / 180 * Math.PI)), mPaint);

    }
}
