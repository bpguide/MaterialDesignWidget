package com.wpp.study.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

public class LinearGradientTextView extends TextView {
    private TextPaint mPaint;
    private String mText;
    private LinearGradient mLinearGradient;
    private Matrix mMatrix;
    private float mTranslateX;
    private float mDeltaX = 20;
    private float mTextWidth;
    private int mGradientSize;
    public LinearGradientTextView(Context context) {
        super(context);
        init();
    }

    public LinearGradientTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LinearGradientTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        //拿到TextView的画笔
        mPaint = getPaint();
        //拿到TextView的文本
        mText = getText().toString();
        //测量文本宽度
        mTextWidth = mPaint.measureText(mText);
        //三个字符的宽度
        mGradientSize =(int) (3 * mTextWidth / mText.length());
        mLinearGradient = new LinearGradient(-mGradientSize, 0, 0, 0, new int[]{0x22ffffff,0xffffffff,0x22ffffff}, new float[]{0,0.5f,1}, Shader.TileMode.CLAMP);
        mPaint.setShader(mLinearGradient);
        mMatrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //根据mDeltaX去改变mTranslateX的值
        mTranslateX += mDeltaX;
        if(mTranslateX > (mTextWidth + mGradientSize) || mTranslateX < 0){
            mDeltaX = -mDeltaX;
        }
        //平移
        mMatrix.setTranslate(mTranslateX, 0);
        mLinearGradient.setLocalMatrix(mMatrix);
        //刷新
        postInvalidateDelayed(20);
    }
}
