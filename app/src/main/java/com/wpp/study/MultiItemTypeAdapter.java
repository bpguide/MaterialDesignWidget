package com.wpp.study;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by wpp.
 *
 * @description
 * @date 2017/6/11
 */
public class MultiItemTypeAdapter extends RecyclerView.Adapter {

    private ArrayList<MyData> mDatas;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    public static final int ITEM1 = 1;
    public static final int ITEM2 = 2;
    public MultiItemTypeAdapter(Context context, ArrayList<MyData> datas){
        mLayoutInflater = LayoutInflater.from(context);
        this.mDatas = datas;
        this.mContext = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == ITEM1){
            return new Item_One(mLayoutInflater.inflate(R.layout.item1, parent, false));
        } else if(viewType == ITEM2){
            return new Item_Two(mLayoutInflater.inflate(R.layout.item2, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof Item_One){
            ((Item_One) holder).title.setText(mDatas.get(position).mData.title);
            Glide.with(mContext).load(mDatas.get(position).mData.images[0]).into(((Item_One) holder).imageView1);
            Glide.with(mContext).load(mDatas.get(position).mData.images[1]).into(((Item_One) holder).imageView2);
            Glide.with(mContext).load(mDatas.get(position).mData.images[2]).into(((Item_One) holder).imageView3);
        } else if (holder instanceof Item_Two){
            ((Item_Two) holder).title.setText(mDatas.get(position).mData.title);
            Glide.with(mContext).load(mDatas.get(position).mData.images[0]).into(((Item_Two) holder).imageView1);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(mDatas != null){
            return mDatas.get(position).viewType;
        }
        return super.getItemViewType(position);
    }

    private class Item_One extends RecyclerView.ViewHolder{
        private ImageView imageView1, imageView2, imageView3;
        private TextView title;
        public Item_One(View itemView) {
            super(itemView);
            imageView1 = (ImageView) itemView.findViewById(R.id.imageview1);
            imageView2 = (ImageView) itemView.findViewById(R.id.imageview1);
            imageView3 = (ImageView) itemView.findViewById(R.id.imageview1);
            title = (TextView) itemView.findViewById(R.id.title);

        }
    }

    private class Item_Two extends RecyclerView.ViewHolder{
        private ImageView imageView1;
        private TextView title;
        public Item_Two(View itemView) {
            super(itemView);
            imageView1 = (ImageView) itemView.findViewById(R.id.imageview1);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}

