package com.example.admin.mypplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by zq on 2017/2/6.
 */

public class CustomView extends View {

    Paint mPaint = new Paint();

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void init() {

        mPaint.setAntiAlias(true);

        mPaint.setStrokeWidth(10f);

        mPaint.setColor(Color.RED);

        mPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPoint(350, 300, mPaint);
        canvas.drawPoint(300, 300, mPaint);
        canvas.drawPoints(new float[]{300, 300, 444, 434, 300, 300, 444, 434, 300, 300, 444, 434}, mPaint);
        canvas.drawLine(100,500,300,852,mPaint);
        canvas.drawLines(new float[]{300, 300, 444, 434, 300, 300, 444, 434, 300, 300, 444, 434},mPaint);
        canvas.drawRect(100,500,300,852,mPaint);
        RectF rectF=new RectF(100,500,300,852);
        canvas.drawRect(rectF,mPaint);
        canvas.drawOval(rectF,mPaint);

        canvas.drawArc(rectF,20,30,false,mPaint);

        canvas.drawArc(rectF,20,30,true,mPaint);
    }




}
