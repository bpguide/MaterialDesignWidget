package com.wpp.study;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

/**
 * Created by wpp.
 *
 * @description
 * @date 2017/5/22
 */
public class MyItemTouchHelpCallBack extends ItemTouchHelper.Callback {

    private String TAG = "wpp";
    private IItemTouchMovedListener mListener;

    public MyItemTouchHelpCallBack(IItemTouchMovedListener listener) {
        this.mListener = listener;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(dragFlag, swipFlag);
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        Log.d(TAG, "onMove");
        if (viewHolder.getItemViewType() != target.getItemViewType()) {
            return false;
        }
        return mListener.onItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Log.d(TAG, "onSwiped");
        mListener.onItemDeleted(viewHolder.getAdapterPosition());
        viewHolder.itemView.setAlpha(1);
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        Log.d(TAG, "onSelectedChanged");
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder.itemView.setBackgroundColor(Color.parseColor("#FEC158"));
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

/*    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        Log.d(TAG, "onChildDrawOver");
        super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }*/

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        Log.d(TAG, "onChildDraw");
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            viewHolder.itemView.setAlpha(1 - Math.abs(dX) / viewHolder.itemView.getWidth());
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);


    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        Log.d(TAG, "clearView");
        viewHolder.itemView.setBackgroundColor(Color.WHITE);
        super.clearView(recyclerView, viewHolder);
    }

    public interface IItemTouchMovedListener {
        boolean onItemMoved(int from, int to);

        boolean onItemDeleted(int position);
    }
}
