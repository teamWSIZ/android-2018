package com.android7.animationexample1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DrawingSurface mSurface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);

        mSurface = findViewById(R.id.drawingSurface);

        DrawingThread thread = new DrawingThread(this,textView,mSurface.getHolder());
        thread.start();
    }
}
