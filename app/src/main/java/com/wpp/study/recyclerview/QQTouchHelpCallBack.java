package com.wpp.study.recyclerview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

/**
 * Created by wpp.
 *
 * @description
 * @date 2017/5/23
 */
public class QQTouchHelpCallBack extends ItemTouchHelper.Callback {

    private String TAG = "wpp";
    private IItemTouchMovedListener mListener;
    private RecyclerView.ViewHolder mTempHolder;
    public QQTouchHelpCallBack(IItemTouchMovedListener listener){
        this.mListener = listener;
    }
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlag = 0;
        int swipFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(dragFlag, swipFlag);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        //((MyRecyclerViewAdapter.BodyViewHolder)viewHolder).flag = true;
        Log.d(TAG, "onSwiped");
        if(mTempHolder == null){
            mTempHolder = viewHolder;
        }
        //mListener.onItemDeleted(viewHolder.getAdapterPosition());
        //viewHolder.itemView.setAlpha(1);
    }


    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
/*        Log.d(TAG, "onChildDraw");
        Log.d(TAG, "DX:"+dX);
        Log.d(TAG, "getScrollX():"+viewHolder.itemView.getScrollX());
        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
            if(Math.abs(dX) <= ((MyRecyclerViewAdapter.BodyViewHolder)viewHolder).delete_view.getWidth()){
                viewHolder.itemView.scrollTo(-(int) dX,0);
            } else {
                viewHolder.itemView.scrollTo(Math.abs(((MyRecyclerViewAdapter.BodyViewHolder)viewHolder).delete_view.getWidth()), 0);
            }
        } else {
//            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }*/

         if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
            viewHolder.itemView.setAlpha(1- Math.abs(dX) / viewHolder.itemView.getWidth());
         }
         super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        viewHolder.itemView.setBackgroundColor(Color.WHITE);
        super.clearView(recyclerView, viewHolder);
    }

    public interface IItemTouchMovedListener {
        boolean onItemMoved(int from, int to);

        boolean onItemDeleted(int position);
    }
}
