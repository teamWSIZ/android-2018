package com.android7.orientationexampleb;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class CompassView extends View {

    private Paint mPaint;
    private float mAzimuth = 0;

    public CompassView(Context context) {
        super(context);

        mPaint = new Paint();
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);
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

        canvas.translate(xcenter, ycenter);

        mPaint.setColor(Color.GRAY);

        float radius = Math.min(width / 2, height / 2) * 0.8f;

        canvas.drawCircle(0, 0, radius, mPaint);

        mPaint.setColor(Color.BLUE);

        canvas.translate(-xcenter, -ycenter);

        canvas.drawLine(xcenter, ycenter, xcenter + radius * (float) Math.cos(mAzimuth),
                ycenter + radius * (float) Math.sin(mAzimuth), mPaint);
    }

    public void update(float azimuth){
        mAzimuth = azimuth;

        invalidate();
    }
}
