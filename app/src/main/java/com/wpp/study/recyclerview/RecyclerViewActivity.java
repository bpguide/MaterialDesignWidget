package com.wpp.study.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.wpp.study.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecyclerViewActivity extends AppCompatActivity implements MyRecyclerViewAdapter.IHolderTouchListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder bind;
    private ItemTouchHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        bind = ButterKnife.bind(this);//绑定
        LinearLayoutManager manager = new LinearLayoutManager(this);
//        GridLayoutManager manager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(manager);
//        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 51; i++){
            data.add("item"+i);
        }
        MySimpleAdapter adapter = new MySimpleAdapter(this, data, new MySimpleAdapter.IOnClickListener() {
            @Override
            public void onViewOnClick(int position) {
                Toast.makeText(RecyclerViewActivity.this, "点击:"+position, Toast.LENGTH_SHORT).show();
            }
       });
        recyclerView.addItemDecoration(new MyItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ItemTouchHelper helper = new ItemTouchHelper(new MyItemTouchHelpCallBack(adapter));
        helper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);
//        recyclerView.addItemDecoration(new MyItemDecoration(this, LinearLayoutManager.VERTICAL));


        /*MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, DataUtils.init(), this);
        recyclerView.setAdapter(myRecyclerViewAdapter);

        helper = new ItemTouchHelper(new QQTouchHelpCallBack(myRecyclerViewAdapter));
//        helper.attachToRecyclerView(recyclerView);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == NumberPicker.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){

                }
            }
        });
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            float lastX = 0;
            float lastY = 0;
            View childView = null;
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//                View childView = rv.findChildViewUnder(e.getX(), e.getY());
//                if(childView != null){
//                    if(childView.getScrollX() > 0){
//                        childView.scrollTo(0, 0);
//                        return true;
//                    }
//                }
                switch (e.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        lastX = e.getX();
                        lastY = e.getY();
                        childView = rv.findChildViewUnder(lastX, lastY);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e("wpp", "ACTION_MOVE");
                        float dx = e.getX() - lastX;
                        Log.e("wpp", "e.getX():"+e.getX());
                        Log.e("wpp", "lastX:"+lastX);
                        Log.e("wpp", "dx:"+dx);
                        if(Math.abs(dx) > 10)
                            return true;
                        break;
                    case MotionEvent.ACTION_UP:
                        lastX = 0;
                        lastY = 0;
                        break;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                switch (e.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        float dx = e.getX() - lastX;
                        float dy = e.getY() - lastY;
                        if(childView != null){
                            childView.scrollTo(-(int)dx, 0);
                        }
                        break;
                }
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }


    @Override
    public void onViewHolderTouch(RecyclerView.ViewHolder viewHolder) {
        helper.startDrag(viewHolder);
    }
}
