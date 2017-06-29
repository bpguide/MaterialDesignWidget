package com.wpp.study;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wpp.study.recyclerview.RecyclerViewActivity;
import com.wpp.study.side.DrawerLayoutActivity;
import com.wpp.study.side.NavigationActivity;
import com.wpp.study.transition.TransitionActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

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

    @OnClick(R.id.drawerlayout_bt)
    public void startDrawerLayout(){
        Intent intent = new Intent(this, DrawerLayoutActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.navigation_bt)
    public void startNavigation(){
        Intent intent = new Intent(this, NavigationActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.transition_bt)
    public void startTransition(){
        Intent intent = new Intent(this, TransitionActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
