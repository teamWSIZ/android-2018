package com.android7.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawingSurface surface = findViewById(R.id.drawingSurface);

        DrawingThread drawingThread = new DrawingThread(this,(TextView)findViewById(R.id.textView),surface.getHolder());
        drawingThread.start();

    }
}
