package com.wpp.study.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class WaveView extends View {
    private Paint mPaint;
    private int mWaveLenght = 400, mWaveHeight = 50;
    private int mCurrrentY = 0;
    private Path mWavePath, mCirclePath;
    private int mDx, mDy;

    public WaveView(Context context) {
        super(context);
        init();
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(10);

        mWavePath = new Path();
        mCirclePath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        mCirclePath.addCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, Path.Direction.CCW);
        canvas.clipPath(mCirclePath);

        mCurrrentY = getHeight();
        if(mDy <= mCurrrentY + 150){
            mDy += 2;
        }
        mWavePath.reset();
        mWavePath.moveTo(-mWaveLenght + mDx, mCurrrentY - mDy);
        int halfWaveLength = mWaveLenght / 2;
        for (int i = -mWaveLenght; i <= getWidth() + mWaveLenght; i += mWaveLenght) {
            mWavePath.rQuadTo(halfWaveLength / 2, -mWaveHeight, halfWaveLength, 0);
            mWavePath.rQuadTo(halfWaveLength / 2, mWaveHeight, halfWaveLength, 0);
        }

        mWavePath.lineTo(getWidth() + 500, getHeight() + 500);
        mWavePath.lineTo(-500, getHeight() + 500);
        mWavePath.close();

        canvas.drawPath(mWavePath, mPaint);
        canvas.restore();
    }

    public void startAnimation(){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, mWaveLenght);
        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mDx = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.start();
    }
}
