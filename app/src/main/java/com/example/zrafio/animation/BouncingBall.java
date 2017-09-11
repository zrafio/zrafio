package com.example.zrafio.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by zrafio on 9/11/17.
 */



public class BouncingBall extends AppCompatImageView {

    private Context mContext;
    int x = -1;
    int y = -1;
    private int xVelocity = 10;
    private int yVelocity = 5;
    private Handler h;
    private final int FRAME_RATE = 30;


    public BouncingBall(Context context, AttributeSet attrs)  {
        super(context, attrs);
        mContext = context;
        h = new Handler();
    }

    private Runnable r = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

    protected void onDraw(Canvas c) {
        
        BitmapDrawable ball = (BitmapDrawable) ContextCompat.getDrawable(mContext,R.drawable.ball);
        if (x<0 && y <0) {
            x = this.getWidth()/2;
            y = this.getHeight()/2;
        } else {
            x += xVelocity;
            y += yVelocity;
            if ((x > this.getWidth() - ball.getBitmap().getWidth()) || (x < 0)) {
                xVelocity = xVelocity*-1;
            }
            if ((y > this.getHeight() - ball.getBitmap().getHeight()) || (y < 0)) {
                yVelocity = yVelocity*-1;
            }
        }
        c.drawBitmap(ball.getBitmap(), x, y, null);

        h.postDelayed(r, FRAME_RATE);


    }
}
