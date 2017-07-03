package com.wpp.study.cardview_floatingbutton;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wpp.study.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardViewAdapter extends RecyclerView.Adapter {

    private LayoutInflater mLayoutInflater;
    private ArrayList<MyData> mDatas;
    private Context mContext;
    public CardViewAdapter(Context context, ArrayList<MyData> datas){
        mLayoutInflater = LayoutInflater.from(context);
        mDatas = datas;
        mContext = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyBodyViewHodler(mLayoutInflater.inflate(R.layout.activity_cardview_recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyData myData = mDatas.get(position);
        ((MyBodyViewHodler)holder).name.setText(myData.name);
        ((MyBodyViewHodler)holder).sex.setText(myData.sex);
        Glide.with(mContext).asBitmap().load(myData.head_url).into(((MyBodyViewHodler)holder).head);
        Glide.with(mContext).asBitmap().load(myData.bg_url).into(((MyBodyViewHodler)holder).bg);

    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    class MyBodyViewHodler extends RecyclerView.ViewHolder{

        @BindView(R.id.activity_cardview_recycler_item_name_tv)
        TextView name;
        @BindView(R.id.activity_cardview_recycler_item_sex_tv)
        TextView sex;
        @BindView(R.id.activity_cardview_recycler_item_head_iv)
        ImageView head;

        @BindView(R.id.activity_cardview_recycler_item_bg)
        ImageView bg;
        public MyBodyViewHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
