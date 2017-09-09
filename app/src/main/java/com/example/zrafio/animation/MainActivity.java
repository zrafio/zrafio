package com.example.zrafio.animation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler();
        Runnable run = new Runnable() {
            @Override
            public void run() {
                // FIXME: 9/8/17
                //Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                Intent intent = new Intent(MainActivity.this,SettingsActivity.class);
                startActivity(intent);
                finish();
            }
        };
        // FIXME: 9/8/17
        //handler.postDelayed(run,3000);
        handler.postDelayed(run,3);
    }
}
