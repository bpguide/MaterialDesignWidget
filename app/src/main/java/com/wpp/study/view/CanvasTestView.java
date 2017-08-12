package com.wpp.study.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CanvasTestView extends View {
    private Paint mPaint;
    private float[] mLines = {0, 100, 100, 200, 100, 200, 200, 300, 200, 300, 300, 400};
    public CanvasTestView(Context context) {
        super(context);
        init();
    }

    public CanvasTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CanvasTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawLine(0, 0, 100, 100, mPaint);

        /**
         * pts：绘制直线的端点数组，每条直线占用4个数据。
         * paint：绘制直线所使用的画笔
         */
//        canvas.drawLines(mLines, mPaint);

        /**
         * pts：绘制直线的端点数组，每条直线占用4个数据。
         * offset：跳过的数据个数，这些数据将不参与绘制过程。
         * count：实际参与绘制的数据个数。
         * paint：绘制直线所使用的画笔。
         * offset+count<=mLines.length
         */
//        canvas.drawLines(mLines, 1, 12, mPaint);

//        RectF rectF = new RectF(200, 200, 600, 600);
//        canvas.drawRect(rectF, mPaint);
//        canvas.drawRoundRect(rectF, 50, 10, mPaint);
//
//        canvas.drawCircle(600, 600, 50, mPaint);
//
//        canvas.drawOval(rectF, mPaint);
//        canvas.drawOval(200, 200, 600, 600, mPaint);

//        canvas.drawArc(rectF, -90, 270, false, mPaint);
//        canvas.drawArc(rectF, -90, 270, true, mPaint);

//        Path path = new Path();
//        path.moveTo(100, 100);//落笔
//
//        path.lineTo(200, 100);
//        path.lineTo(200, 200);
//        path.close();
//
//        path.cubicTo(250, 200, 350, 500, 600, 700);//三阶


//        float[] radii = {10, 10, 10, 10, 10, 10,10, 10};
//        path.addRoundRect(rectF, radii, Path.Direction.CW);
//        canvas.drawPath(path, mPaint);
        //region 区域,创建一块矩形区域
//        RectF r = new RectF(100, 100, 200, 200);
//        Path p = new Path();
//        p.addOval(r, Path.Direction.CCW);
//
//        Region region = new Region(100, 100, 200, 200);
//        Region region1 = new Region();
//        region1.setPath(p, region);
//
//
//        Rect rect = new Rect();
//        RegionIterator iterator = new RegionIterator(region1);
//        while (iterator.next(rect)){
//            canvas.drawRect(rect, mPaint);
//        }

        //canvas变换
//        canvas.translate();
//        canvas.scale();
//        canvas.rotate();
//        canvas.skew();
//        canvas.clipPath()

//        canvas.save();
//        canvas.translate(200, 200);
//        mPaint.setColor(Color.CYAN);
//        canvas.drawRect(new RectF(0, 0, 300, 300), mPaint);
//        canvas.drawCircle(300, 150, 150, mPaint);
//
//
//        mPaint.setColor(Color.RED);
//        canvas.clipRect(new RectF(0, 0, 300, 300));//第一个裁剪一个形状相当于A
//        Path mPath = new Path();
//        mPath.addCircle(300, 150, 150, Path.Direction.CCW);
//        canvas.clipPath(mPath, Region.Op.INTERSECT);//第二个裁剪一个形状相当于B
//        canvas.drawRect(new RectF(0, 0, 450, 300), mPaint);
//        canvas.restore();
    }
}
