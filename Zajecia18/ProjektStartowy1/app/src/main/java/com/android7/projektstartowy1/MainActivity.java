package com.android7.projektstartowy1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawingSurface surface = findViewById(R.id.drawingSurface);

        DrawingThread thread = new DrawingThread(this,(TextView)findViewById(R.id.textView),surface.getHolder());
        thread.start();
    }
}
