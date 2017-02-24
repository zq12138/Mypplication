package com.example.admin.mypplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by zq on 2017/2/6.
 */

public class PieView extends View {
    // 颜色表 (注意: 此处定义颜色使用的是ARGB，带Alpha通道的)PS
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};

    // 饼状图初始绘制角度
    private float mStartAngle = 0;
    // 数据
    private ArrayList<PieData> mData;
    // 宽高
    private int mWidth, mHeight;

    Paint mPaint = new Paint();

    public PieView(Context context) {
        super(context, null);
    }

    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mData == null) return;
        float currentStartAngle = mStartAngle;
        canvas.translate(mWidth / 2, mHeight / 2);
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);
        RectF mRectF = new RectF(-r, -r, r, r);
        for (int i = 0; i < mData.size(); i++) {
            PieData pieData = mData.get(i);
            mPaint.setColor(pieData.getColor());
            canvas.drawArc(mRectF, currentStartAngle, pieData.getAngle(), true, mPaint);
            currentStartAngle = pieData.getAngle();
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    // 设置起始角度
    public void setStartAngle(int mStartAngle) {
        this.mStartAngle = mStartAngle;
        invalidate();
    }

    public void setData(ArrayList<PieData> mData) {
        this.mData = mData;
        initData(mData);
        invalidate();
    }

    public void initData(ArrayList<PieData> mData) {
        if (mData.size() == 0 || mData == null) return;
        int sumValue = 0;
        for (int i = 0; i < mData.size(); i++) {
            PieData mPieDate = mData.get(i);
            sumValue += mPieDate.getValue();
            int j = i % mColors.length;
            mPieDate.setColor(mColors[j]);
        }

        float sumAngle = 0;

        for (int i = 0; i < mData.size(); i++) {
            PieData mPieDate = mData.get(i);

            float percentage = mPieDate.getValue() / sumAngle;

            float angle = percentage * 360;

            mPieDate.setPercentage(percentage);

            mPieDate.setAngle(angle);

            sumAngle += angle;
        }
    }
}
