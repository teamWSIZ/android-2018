package com.android7.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class CompassView extends View {

    Paint mPaint;

    public CompassView(Context context) {
        super(context);

        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(1);
        mPaint.setAntiAlias(true);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        float width = getMeasuredWidth();
        float height = getMeasuredHeight();

        canvas.drawRect(0,0,width,height,mPaint);

    }
}
