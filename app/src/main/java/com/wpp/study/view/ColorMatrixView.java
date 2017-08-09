package com.wpp.study.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wpp.study.R;

public class ColorMatrixView extends View{

    private Paint mPaint;
    private Bitmap mBitmap1, mBitmap2, mBitmap3;
    private ColorMatrix mColorMatrix;
    private LightingColorFilter mLightingColorFilter;
    private PorterDuffColorFilter mPorterDuffColorFilter;
    private float[] matrix_fx = new float[]{
            -1, 0, 0, 0, 255,
            0, -1, 0, 0, 255,
            0, 0, -1, 0, 255,
            0, 0, 0, 1, 0,
    };

    private float[] matrix_light = new float[]{
            1.2f, 0, 0, 0, 0,
            0, 1.2f, 0, 0, 0,
            0, 0, 1.2f, 0, 0,
            0, 0, 0, 1.2f, 0,
    };

    private float[] matrix_wb = new float[]{
            0.213f, 0.715f, 0.072f, 0, 0,
            0.213f, 0.715f, 0.072f, 0, 0,
            0.213f, 0.715f, 0.072f, 0, 0,
            0, 0, 0, 1f, 0,
    };

    private float[] matrix_fg = new float[]{
            1 / 2f, 1 / 2f, 1 / 2f, 0, 0,
            1 / 3f, 1 / 3f, 1 / 3f, 0, 0,
            1 / 4f, 1 / 4f, 1 / 4f, 0, 0,
            0, 0, 0, 1f, 0,
    };
    public ColorMatrixView(Context context) {
        super(context);
        init();
    }

    public ColorMatrixView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ColorMatrixView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.pic1);
        mBitmap2 = BitmapFactory.decodeResource(getResources(), R.mipmap.pic2);
        mBitmap3 = BitmapFactory.decodeResource(getResources(), R.mipmap.pic3);

        mColorMatrix = new ColorMatrix();
        mLightingColorFilter = new LightingColorFilter(0x00ff00, 0xff0000);
        mPorterDuffColorFilter = new PorterDuffColorFilter(Color.parseColor("#FFB6C1"), PorterDuff.Mode.SRC_IN);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.reset();
        mPaint.setAntiAlias(true);
        /**模糊遮罩滤镜效果
         * BlurMaskFilter.Blur.INNER
         * BlurMaskFilter.Blur.NORMAL
         * BlurMaskFilter.Blur.OUTER
         * BlurMaskFilter.Blur.SOLID
         */
        mPaint.setMaskFilter(new BlurMaskFilter(20, BlurMaskFilter.Blur.NORMAL));
        canvas.drawBitmap(mBitmap1, 50, 50, mPaint);

        mPaint.setMaskFilter(new BlurMaskFilter(20, BlurMaskFilter.Blur.INNER));
        canvas.drawBitmap(mBitmap1, 400, 50, mPaint);

        mPaint.setMaskFilter(new BlurMaskFilter(20, BlurMaskFilter.Blur.OUTER));
        canvas.drawBitmap(mBitmap1, 50, 350, mPaint);

        mPaint.setMaskFilter(new BlurMaskFilter(20, BlurMaskFilter.Blur.SOLID));
        canvas.drawBitmap(mBitmap1, 400, 350, mPaint);

        /*float []direction = new float[]{ 1, 1, -1};
        float ambient = 1f;
        float specular = 6f;
        float blurRadius = 3.5f;
        mPaint.setMaskFilter(new EmbossMaskFilter(direction, ambient, specular, blurRadius));
        canvas.drawBitmap(mBitmap, 50, 650, mPaint);*/

        mPaint.setMaskFilter(null);
        canvas.drawBitmap(mBitmap2, 50, 650, mPaint);

        mColorMatrix.set(matrix_light);
        mPaint.setColorFilter(new ColorMatrixColorFilter(mColorMatrix));
        canvas.drawBitmap(mBitmap2, 300, 650, mPaint);

        mColorMatrix.set(matrix_fx);
        mPaint.setColorFilter(new ColorMatrixColorFilter(mColorMatrix));
        canvas.drawBitmap(mBitmap2, 550, 650, mPaint);

        mColorMatrix.set(matrix_wb);
        mPaint.setColorFilter(new ColorMatrixColorFilter(mColorMatrix));
        canvas.drawBitmap(mBitmap2, 50, 850, mPaint);

        mColorMatrix.set(matrix_fg);
        mPaint.setColorFilter(new ColorMatrixColorFilter(mColorMatrix));
        canvas.drawBitmap(mBitmap2, 300, 850, mPaint);

        mPaint.setColorFilter(mLightingColorFilter);
        canvas.drawBitmap(mBitmap2, 50, 1050, mPaint);

        mPaint.setColorFilter(null);
        canvas.drawBitmap(mBitmap3, 50, 1250, mPaint);

        mPaint.setColorFilter(mPorterDuffColorFilter);
        canvas.drawBitmap(mBitmap3, 500, 1250, mPaint);
    }
}
