package com.wpp.study.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


public class DrawTextView extends View {
    private String TAG = "wpp";
    Paint mPaint;
    int measuredWidth;
    public DrawTextView(Context context) {
        super(context);
        init();
    }

    public DrawTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        measuredWidth = getMeasuredWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.reset();
        mPaint.setColor(Color.RED);

        //绘制中间水平线
        float lineHorizontalY = getHeight() / 2;
        canvas.drawLine(0, lineHorizontalY, getWidth(), lineHorizontalY, mPaint);

        //绘制中间垂直线
        float lineVerticalX = getWidth() / 2;
        canvas.drawLine(lineVerticalX, lineHorizontalY - 200, lineVerticalX, lineHorizontalY + 200, mPaint);

        mPaint.setColor(Color.BLUE);
        mPaint.setTextSize(50);
        mPaint.setTextAlign(Paint.Align.CENTER);

        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;
        float x = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        canvas.drawText("非常规程序猿wupengpeng.com", centerX, centerY + x, mPaint);
    }
}
