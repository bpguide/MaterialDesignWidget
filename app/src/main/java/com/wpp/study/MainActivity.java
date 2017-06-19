package com.wpp.study;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by wpp.
 *
 * @description
 * @date 2017/6/19
 */
public class MainActivity extends AppCompatActivity {
    Button recyclerview;
    private Unbinder bind;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind = ButterKnife.bind(this);
    }

    @OnClick(R.id.recyclerview_bt)
    public void startRecycerView(){
        Intent intent = new Intent(this, RecyclerViewActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
