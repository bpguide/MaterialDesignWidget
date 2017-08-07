package com.wpp.study.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wpp.study.R;

public class ShaderView extends View {

    private Paint mPaint;
    private Bitmap mBitmap;
    private int mWidth, mHeight;
    private BitmapShader mBitmapShader;
    private LinearGradient mLinearGradient;
    private SweepGradient mSweepGradient;
    private RadialGradient mRadiaGradient;
    private Matrix mMatrix;
    private String mText = "非常规程序猿";
    public ShaderView(Context context) {
        super(context);
        init();
    }

    public ShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.lufei);
        mPaint = new Paint();
        mWidth = mBitmap.getWidth();
        mHeight = mBitmap.getHeight();
        /**
         * TileMode.CLAMP 拉伸最后一个像素去铺满剩下的地方
         * TileMode.MIRROR 通过镜像翻转铺满剩下的地方。
         * TileMode.REPEAT 重复图片平铺整个画面（电脑设置壁纸）
         */
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);

        mLinearGradient = new LinearGradient(0, 0, 200, 0, Color.RED, Color.CYAN, Shader.TileMode.REPEAT);

        mSweepGradient = new SweepGradient(200, 200, Color.RED, Color.CYAN);

        mRadiaGradient = new RadialGradient(200, 200, 200, Color.RED, Color.CYAN, Shader.TileMode.REPEAT);
        mPaint.setAntiAlias(true);
        mMatrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        int x = Math.min(mWidth, mHeight);
//        float scale = getHeight() / (x * 1.0f);
//        mMatrix.setScale(scale, scale);
//        mBitmapShader.setLocalMatrix(mMatrix);
//
//        mPaint.setShader(mBitmapShader);
//        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getHeight() / 2, mPaint);

//        mPaint.setTextSize(100);
//        mPaint.setStrokeWidth(50);
//        mPaint.setTextAlign(Paint.Align.CENTER);
//        canvas.drawText(mText,0,mText.length(),getWidth() / 2,200,mPaint);

//        mLinearGradient
//        mPaint.setShader(mLinearGradient);
//        canvas.drawRect(0, 0, 200, 200, mPaint);

        mPaint.setShader(mRadiaGradient);
        canvas.drawRect(0, 0, 400, 400, mPaint);
    }
}
