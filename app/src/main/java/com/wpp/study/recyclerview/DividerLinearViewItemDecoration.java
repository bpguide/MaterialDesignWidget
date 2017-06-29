package com.wpp.study.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wpp.study.R;

public class DividerLinearViewItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDivider;
    private int mOrientation = LinearLayoutManager.VERTICAL;
    public DividerLinearViewItemDecoration(Context context, int orientation){
        mDivider = context.getResources().getDrawable(R.drawable.item_divider, null);
        setOrientation(orientation);
    }

    public void setOrientation(int orientation){
        if(orientation != LinearLayoutManager.VERTICAL && orientation != LinearLayoutManager.HORIZONTAL){
            throw new IllegalArgumentException("参数传递错误");
        }
        this.mOrientation = orientation;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int offset = mDivider.getIntrinsicHeight();
        if(parent.getChildAdapterPosition(view) == parent.getChildCount() - 1){
            offset = 0;
        }
        if(mOrientation == LinearLayoutManager.VERTICAL){
            outRect.set(0, 0, 0, offset);
        } else {
            outRect.set(0, 0, offset, 0);
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if(mOrientation == LinearLayoutManager.VERTICAL){
            drawHorizontal(c, parent);
        } else {
            drawVertical(c, parent);
        }
        super.onDraw(c, parent, state);
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++){
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + params.rightMargin + Math.round(ViewCompat.getTranslationX(child)) + mDivider.getIntrinsicWidth();
            int right = left + mDivider.getIntrinsicWidth();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++){
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + params.bottomMargin + Math.round(ViewCompat.getTranslationY(child)) + mDivider.getMinimumHeight();
            int bottom = top + mDivider.getMinimumHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }
}
