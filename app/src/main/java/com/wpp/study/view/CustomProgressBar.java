package com.wpp.study.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wpp.study.R;

import static android.R.attr.max;

public class CustomProgressBar extends View {
    private int mMax;
    private int mTextColor;
    private int mRoundColor;
    private int mRoundProgressColor;
    private float mTextSIze;
    private float mRoundWidth;
    private boolean mIsShowText;
    private int mStyle;
    private int mProgress;
    private Paint mPaint;
    public static final int STROKE = 0;
    public static final int FILL = 1;
    private RectF mOval;
    public CustomProgressBar(Context context) {
        super(context);
        init(context, null);
    }

    public CustomProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        if(attrs != null){
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomProgressBar);

            mMax = typedArray.getInteger(R.styleable.CustomProgressBar_max, 100);
            mTextColor = typedArray.getColor(R.styleable.CustomProgressBar_textColor, Color.BLACK);
            mRoundColor = typedArray.getColor(R.styleable.CustomProgressBar_roundColor, Color.RED);
            mRoundProgressColor = typedArray.getColor(R.styleable.CustomProgressBar_roundProgressColor, Color.BLUE);
            mTextSIze = typedArray.getDimension(R.styleable.CustomProgressBar_textSize, 15);
            mRoundWidth = typedArray.getDimension(R.styleable.CustomProgressBar_roundWidth, 10);
            mIsShowText = typedArray.getBoolean(R. styleable.CustomProgressBar_textShow, true);
            mStyle = typedArray.getInt(R.styleable.CustomProgressBar_style, 0);

            typedArray.recycle();
        }
        mPaint = new Paint();
        mOval = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画默认的圆环
        int center = getWidth() / 2;
        mPaint.setColor(mRoundColor);
        mPaint.setStyle(Paint.Style.STROKE);//空心
        mPaint.setStrokeWidth(mRoundWidth);
        mPaint.setAntiAlias(true);
        float radius = center - mRoundWidth / 2;
        canvas.drawCircle(center, center, radius, mPaint);

        int percent = (int)(mProgress / (float)mMax * 100);

        //画百分比文字
        if( mIsShowText && percent != 0 && mStyle == STROKE){
            mPaint.setColor(mTextColor);
            mPaint.setStrokeWidth(0);
            mPaint.setTextSize(mTextSIze);
            mPaint.setTypeface(Typeface.DEFAULT_BOLD);
            float y = center - (mPaint.descent() + mPaint.ascent()) / 2f;
            mPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(percent + "%", center, y, mPaint);
        }

        //画圆弧
        mPaint.setColor(mRoundProgressColor);
        mPaint.setStrokeWidth(mRoundWidth);
        mOval.left = center - radius;
        mOval.top = center - radius;
        mOval.right = center + radius;
        mOval.bottom = center + radius;
        switch (mStyle){
            case STROKE:
                mPaint.setStyle(Paint.Style.STROKE);
                canvas.drawArc(mOval, 0, 360 * mProgress / mMax, false, mPaint);
                break;
            case FILL:
                mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                canvas.drawArc(mOval, 0, 360 * mProgress / mMax, true, mPaint);
                break;
        }
    }

    public synchronized int getMax(){
        return mMax;
    }

    public synchronized void setMax(int max){
        if(max < 0){
            throw new IllegalArgumentException("max不能小于0");
        }
        this.mMax = max;
    }

    public synchronized int getProgress(){
        return mProgress;
    }

    public synchronized void setProgress(int progress){
        if(progress < 0){
            throw new IllegalArgumentException("progress不能小于0");
        }

        if(progress > mMax){
            progress = mMax;
        }
        if(progress <= max){
            this.mProgress = progress;
            postInvalidate();
        }
    }

    public int getTextColor() {
        return mTextColor;
    }

    public void setTextColor(int textColor) {
        mTextColor = textColor;
    }

    public int getRoundColor() {
        return mRoundColor;
    }

    public void setRoundColor(int roundColor) {
        mRoundColor = roundColor;
    }

    public int getRoundProgressColor() {
        return mRoundProgressColor;
    }

    public void setRoundProgressColor(int roundProgressColor) {
        mRoundProgressColor = roundProgressColor;
    }

    public float getTextSIze() {
        return mTextSIze;
    }

    public void setTextSIze(float textSIze) {
        mTextSIze = textSIze;
    }

    public float getRoundWidth() {
        return mRoundWidth;
    }

    public void setRoundWidth(float roundWidth) {
        mRoundWidth = roundWidth;
    }

    public boolean isShowText() {
        return mIsShowText;
    }

    public void setShowText(boolean showText) {
        mIsShowText = showText;
    }

    public int getStyle() {
        return mStyle;
    }

    public void setStyle(int style) {
        mStyle = style;
    }
}
