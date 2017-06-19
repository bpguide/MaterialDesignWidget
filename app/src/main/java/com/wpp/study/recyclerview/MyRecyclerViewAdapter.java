package com.wpp.study.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wpp.study.R;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by wpp.
 *
 * @description
 * @date 2017/5/22
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter implements QQTouchHelpCallBack.IItemTouchMovedListener{
    private Context context;
    private LayoutInflater mLayoutInflater;
    private ArrayList<QQMessage> mData;
    private IHolderTouchListener mDragListener;
    public MyRecyclerViewAdapter(Context context, ArrayList<QQMessage> data, IHolderTouchListener dragListener){
        mLayoutInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mDragListener = dragListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BodyViewHolder(mLayoutInflater.inflate(R.layout.listitem, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        QQMessage qqMessage = mData.get(position);
        ((BodyViewHolder)holder).iv_logo.setImageResource(qqMessage.getLogo());
        ((BodyViewHolder)holder).tv_name.setText(qqMessage.getName());
        ((BodyViewHolder)holder).tv_Msg.setText(qqMessage.getLastMsg());
        ((BodyViewHolder)holder).tv_time.setText(qqMessage.getTime());
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public boolean onItemMoved(int from, int to) {
        Collections.swap(mData, from, to);
        notifyItemMoved(from, to);
        return true;
    }

    @Override
    public boolean onItemDeleted(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
        return true;
    }

    class BodyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_logo)
        ImageView iv_logo;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_lastMsg)
        TextView tv_Msg;
        @BindView(R.id.tv_time)
        TextView tv_time;
        @BindView(R.id.delete_item)
        View delete_view;
        boolean flag = false;
        public BodyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            iv_logo.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(event.getAction() == MotionEvent.ACTION_DOWN){
                        //mDragListener.onViewHolderTouch(BodyViewHolder.this);
                    }
                    return false;
                }
            });
        }
    }

    public interface IHolderTouchListener{
        void onViewHolderTouch(RecyclerView.ViewHolder viewHolder);
    }
}
