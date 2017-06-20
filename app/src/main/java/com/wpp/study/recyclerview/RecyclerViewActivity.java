package com.wpp.study.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.wpp.study.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecyclerViewActivity extends AppCompatActivity {

    @BindView(R.id.recyclerViewLinearLayout)
    RecyclerView mLinearLayoutRecyclerView;

    @BindView(R.id.recyclerViewGridLayout)
    RecyclerView mGridLayoutRecyclerView;

    Unbinder bind;
    private ItemTouchHelper mHelper;
    private GridLayoutManager mGridLayoutManager;
    private LinearLayoutManager mLinearLayoutManager;
    private ArrayList<String> mData;
    private MyLinearAdapter mLinearLayoutRecycylerAdapter;
    private MyGridAdapter mGridLayoutRecyclerAdapter;
    private DividerLinearViewItemDecoration mLinearLayoutItemDecoration;
    private DividerGridViewItemDecoration mGridLayoutItemDecoration;
    private int currentPage = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        bind = ButterKnife.bind(this);//绑定
        mLinearLayoutManager = new LinearLayoutManager(this);
        mGridLayoutManager = new GridLayoutManager(this, 3);
        mLinearLayoutItemDecoration = new DividerLinearViewItemDecoration(this, LinearLayoutManager.VERTICAL);
        initData();
        initView();

    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 50; i++){
            mData.add("item"+i);
        }
    }

    private void initView() {
        /****** 线性布局 ******/
        mLinearLayoutRecyclerView.setLayoutManager(mLinearLayoutManager);
        mLinearLayoutRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mLinearLayoutRecycylerAdapter = new MyLinearAdapter(this, mData, new MyLinearAdapter.IOnClickListener() {
            @Override
            public void onViewOnClick(int position) {
                Toast.makeText(RecyclerViewActivity.this, "点击:"+position, Toast.LENGTH_SHORT).show();
            }
        });
        mLinearLayoutRecyclerView.addItemDecoration(mLinearLayoutItemDecoration);
        mLinearLayoutRecyclerView.setAdapter(mLinearLayoutRecycylerAdapter);

        /****** 网格布局 ******/
        mGridLayoutItemDecoration = new DividerGridViewItemDecoration(this);

        mGridLayoutRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mGridLayoutRecyclerAdapter = new MyGridAdapter(this, mData, new MyGridAdapter.IOnClickListener() {
            @Override
            public void onViewOnClick(int position) {
                Toast.makeText(RecyclerViewActivity.this, "点击:"+position, Toast.LENGTH_SHORT).show();
            }
        });
        mHelper = new ItemTouchHelper(new MyItemTouchHelpCallBack(mGridLayoutRecyclerAdapter));
        mGridLayoutRecyclerView.setAdapter(mGridLayoutRecyclerAdapter);
        mGridLayoutRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_vertical:
                if(currentPage != 0){
                    currentPage = 0;
                    mLinearLayoutRecyclerView.setVisibility(View.VISIBLE);
                    mGridLayoutRecyclerView.setVisibility(View.GONE);

                    mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//设置垂直方向LayoutManager
                    mLinearLayoutRecyclerView.setLayoutManager(mLinearLayoutManager);

                    mLinearLayoutItemDecoration.setOrientation(LinearLayoutManager.VERTICAL);//设置垂直方向分割线
                }

                break;
            case R.id.action_horizontal:
                if(currentPage != 1) {
                    currentPage = 1;
                    mLinearLayoutRecyclerView.setVisibility(View.VISIBLE);
                    mGridLayoutRecyclerView.setVisibility(View.GONE);

                    mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);//设置水平方向LayoutManager
                    mLinearLayoutRecyclerView.setLayoutManager(mLinearLayoutManager);

                    mLinearLayoutItemDecoration.setOrientation(LinearLayoutManager.HORIZONTAL);//设置水平方向分割线
                }
                break;
            case R.id.action_grid:
                if(currentPage != 2){//显示网格布局RecycleView
                    currentPage = 2;
                    mLinearLayoutRecyclerView.setVisibility(View.GONE);
                    mGridLayoutRecyclerView.setVisibility(View.VISIBLE);

                    mGridLayoutRecyclerView.setLayoutManager(mGridLayoutManager);
                    mGridLayoutRecyclerView.addItemDecoration(mGridLayoutItemDecoration);
                    mHelper.attachToRecyclerView(null);
                }
                break;
            case R.id.action_slide_drag:
                if(currentPage != 3){
                    currentPage = 3;
                    mLinearLayoutRecyclerView.setVisibility(View.GONE);
                    mGridLayoutRecyclerView.setVisibility(View.VISIBLE);
                    mGridLayoutRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                    mGridLayoutRecyclerView.removeItemDecoration(mGridLayoutItemDecoration);
                    mHelper.attachToRecyclerView(mGridLayoutRecyclerView);//设TouchHelper
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

}
