package com.android7.orientationexampleb;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class CompassView extends View {

    private Paint mPaint;
    private float mAzimuth = 0;

    private Bitmap mCompassBitmap;

    private Bitmap mCompassHandlebar;
    private Bitmap mCompassCoordinates;

    public CompassView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public CompassView(Context context) {
        super(context);

        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);

        mCompassBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.compass);

        mCompassHandlebar = BitmapFactory.decodeResource(getResources(),R.drawable.compass_handlebar_0);
        mCompassCoordinates = BitmapFactory.decodeResource(getResources(),R.drawable.compass_coordinates);
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

        int r = (int)(Math.min(width / 2, height / 2) * 0.95f);

        canvas.translate(xcenter,ycenter);

        canvas.translate(-r,-r);

        canvas.drawBitmap(mCompassBitmap,null,new Rect(0,0,2*r,2*r),null);

        canvas.translate(r,r);

        canvas.rotate(mAzimuth);

        canvas.translate(-r,-r);
        canvas.drawBitmap(mCompassHandlebar,null,new Rect(0,0,2*r,2*r),null);

        canvas.drawBitmap(mCompassCoordinates,null,new Rect(0,0,2*r,2*r),null);

    }

    public void update(float azimuth){
        mAzimuth = azimuth;

        invalidate();
    }
}
