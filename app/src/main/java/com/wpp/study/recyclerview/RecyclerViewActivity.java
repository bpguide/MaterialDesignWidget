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
    private ArrayList<MultitermData> mMultitermData;

    private MyLinearAdapter mLinearLayoutRecycylerAdapter;
    private MyGridAdapter mGridLayoutRecyclerAdapter;
    private MultitermTypeAdapter mMultitermAdapter;

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
        mMultitermData = new ArrayList<>();
        MultitermData data = new MultitermData();
        data.viewType = MultitermTypeAdapter.ITEM1;
        MultitermData.ItemData item = new MultitermData.ItemData();
        item.title = "自然风光";
        item.images = new String[3];
        item.images[0] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1497955274590&di=19cec4d3efcc1565cf8d97f981b4f57c&imgtype=0&src=http%3A%2F%2Fpic2.ooopic.com%2F11%2F34%2F46%2F78b1OOOPIC4e.jpg";
        item.images[1] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1497955403399&di=1522f62ccf24cdfe1c1c68add1250ab9&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F12%2F81%2F81%2F09s58PICtFx.jpg";
        item.images[2] = "http://img.ivsky.com/img/tupian/pre/201704/29/tianye_jingse-006.jpg";
        data.mData = item;
        mMultitermData.add(data);

        data = new MultitermData();
        data.viewType = MultitermTypeAdapter.ITEM2;
        item = new MultitermData.ItemData();
        item.title = "星空景色";
        item.images = new String[1];
        item.images[0] = "http://img.ivsky.com/img/tupian/pre/201704/23/xingkong-007.jpg";
        data.mData = item;
        mMultitermData.add(data);

        data = new MultitermData();
        data.viewType = MultitermTypeAdapter.ITEM2;
        item = new MultitermData.ItemData();
        item.title = "星空景色";
        item.images = new String[1];
        item.images[0] = "http://img.ivsky.com/img/tupian/pre/201704/23/xingkong-001.jpg";
        data.mData = item;
        mMultitermData.add(data);

        data = new MultitermData();
        data.viewType = MultitermTypeAdapter.ITEM1;
        item = new MultitermData.ItemData();
        item.title = "自然风光";
        item.images = new String[3];
        item.images[0] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1497955274590&di=19cec4d3efcc1565cf8d97f981b4f57c&imgtype=0&src=http%3A%2F%2Fpic2.ooopic.com%2F11%2F34%2F46%2F78b1OOOPIC4e.jpg";
        item.images[2] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1497955403399&di=1522f62ccf24cdfe1c1c68add1250ab9&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F12%2F81%2F81%2F09s58PICtFx.jpg";
        item.images[1] = "http://img.ivsky.com/img/tupian/pre/201704/29/tianye_jingse-006.jpg";
        data.mData = item;
        mMultitermData.add(data);

        data = new MultitermData();
        data.viewType = MultitermTypeAdapter.ITEM2;
        item = new MultitermData.ItemData();
        item.title = "星空景色";
        item.images = new String[1];
        item.images[0] = "http://img.ivsky.com/img/tupian/pre/201704/23/xingkong-001.jpg";
        data.mData = item;
        mMultitermData.add(data);
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

        mMultitermAdapter = new MultitermTypeAdapter(this, mMultitermData);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_recyclerview_menu, menu);
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

                    mLinearLayoutRecyclerView.setAdapter(mLinearLayoutRecycylerAdapter);

                    mLinearLayoutItemDecoration.setOrientation(LinearLayoutManager.VERTICAL);//设置垂直方向分割线
                }

                break;
            case R.id.action_horizontal:
                if(currentPage != 1) {
                    currentPage = 1;
                    mLinearLayoutRecyclerView.setVisibility(View.VISIBLE);
                    mGridLayoutRecyclerView.setVisibility(View.GONE);

                    mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);//设置水平方向LayoutManager

                    mLinearLayoutRecyclerView.setAdapter(mLinearLayoutRecycylerAdapter);

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
            case R.id.action_multite_type:
                if(currentPage != 4){
                    currentPage = 4;
                    mLinearLayoutRecyclerView.setVisibility(View.VISIBLE);
                    mGridLayoutRecyclerView.setVisibility(View.GONE);

                    mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//设置垂直方向LayoutManager

                    mLinearLayoutRecyclerView.setAdapter(mMultitermAdapter);

                    mLinearLayoutItemDecoration.setOrientation(LinearLayoutManager.VERTICAL);//设置垂直方向分割线
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
