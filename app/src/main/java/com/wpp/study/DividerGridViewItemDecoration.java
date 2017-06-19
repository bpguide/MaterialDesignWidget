package com.wpp.study;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.State;
import android.util.Log;
import android.view.View;

public class DividerGridViewItemDecoration extends ItemDecoration {

    private Drawable mDivider;


    public DividerGridViewItemDecoration(Context context) {
        mDivider = context.getResources().getDrawable(R.drawable.item_divider, null);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, State state) {
        super.onDraw(c, parent, state);
        drawVertical(c, parent);
        drawHorizontal(c, parent);
    }

    @SuppressLint("NewApi")
    private void drawVertical(Canvas canvas, RecyclerView parent) {
        canvas.save();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++)
        {
            final View child = parent.getChildAt(i);

            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getTop() - params.topMargin;
            final int bottom = child.getBottom() + params.bottomMargin;
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicWidth();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
        }
        canvas.restore();
    }

    @SuppressLint("NewApi")
    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        canvas.save();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++)
        {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getLeft() - params.leftMargin;
            final int right = child.getRight() + params.rightMargin
                    + mDivider.getIntrinsicWidth();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
        }
        canvas.restore();
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        super.getItemOffsets(outRect, view, parent, state);
        // 四个方向的偏移值
        int itemPosition = parent.getChildAdapterPosition(view);
        Log.d("wpp","----:"+itemPosition);
        int right = mDivider.getIntrinsicWidth();
        int bottom = mDivider.getIntrinsicHeight();
        if (isLastColum(itemPosition, parent)) {//是否是最后一列
            right = 0;
        }
        if (isLastRow(itemPosition, parent)) {//是最后一行
            bottom = 0;
        }
        outRect.set(0, 0, right, bottom);
    }

    /**
     * 是否是最后一行
     *
     * @param itemPosition
     * @param parent
     * @return
     */
    private boolean isLastRow(int itemPosition, RecyclerView parent) {
        Log.e("wpp", "isLastRow");
        int spanCount = getSpanCount(parent);
        LayoutManager layoutManager = parent.getLayoutManager();
        //有多少列
        if (layoutManager instanceof GridLayoutManager) {
            int childCount = parent.getAdapter().getItemCount();
            int lastRowCount = childCount % spanCount;
            int rowCount = childCount / spanCount;
            if(lastRowCount != 0){
                rowCount += 1;
            }
            if(itemPosition >= ((rowCount - 1) * spanCount) && itemPosition < childCount){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是最后一列
     *
     * @param itemPosition
     * @param parent
     * @return
     */
    private boolean isLastColum(int itemPosition, RecyclerView parent) {
        LayoutManager layoutManager = parent.getLayoutManager();
        //有多少列
        if (layoutManager instanceof GridLayoutManager) {
            int spanCount = getSpanCount(parent);
            if ((itemPosition + 1) % spanCount == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param parent
     * @return
     */
    private int getSpanCount(RecyclerView parent) {
        LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager lm = (GridLayoutManager) layoutManager;
            int spanCount = lm.getSpanCount();
            return spanCount;
        }
        return 0;
    }

}

