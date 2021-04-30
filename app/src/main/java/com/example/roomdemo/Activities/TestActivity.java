package com.example.roomdemo.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SampleCircle(this));
    }

    private static class SampleCircle extends View {

        public SampleCircle(Context context) {
            super(context);
            setFocusable(true);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            /** Canvas background color */
            canvas.drawColor(Color.TRANSPARENT);
            //canvas.drawColor(Color.CYAN);
            Paint p = new Paint();
            /** border color for Circle*/
            p.setColor(Color.RED);
            p.setARGB(225, 68, 89, 82);
            p.setAntiAlias(true);
           // p.setStyle(Paint.Style.STROKE);
            /**
             * want to set opacity then use
             * p.setAlpha(0x50);
             */
            p.setStrokeWidth(4.5f);
            /** first outer circle */
            canvas.drawCircle(200, 200, 100, p);
           // canvas.drawCircle(200, 200, 100, getInnerPaint());
            // if needed, draw a border for info window
         //   canvas.drawCircle(200, 200, 100, getBorderPaint());

            /** inner circle */
        //    canvas.drawCircle(202, 200, 80, p);

        }
        private Paint innerPaint, borderPaint;

        public Paint getInnerPaint() {
            if (innerPaint == null) {
                innerPaint = new Paint();
                innerPaint.setARGB(225, 68, 89, 82); // gray
                innerPaint.setAntiAlias(true);
            }
            return innerPaint;
        }

        public Paint getBorderPaint() {
            if (borderPaint == null) {
                borderPaint = new Paint();
                borderPaint.setARGB(255, 68, 89, 82);
                borderPaint.setAntiAlias(true);
                borderPaint.setStyle(Paint.Style.STROKE);
                borderPaint.setStrokeWidth(2);
            }
            return borderPaint;
        }
    }
}