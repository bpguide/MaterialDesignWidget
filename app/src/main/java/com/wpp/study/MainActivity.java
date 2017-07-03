package com.wpp.study;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;

import com.wpp.study.palette.PaletteActivity;
import com.wpp.study.recyclerview.RecyclerViewActivity;
import com.wpp.study.side.DrawerLayoutActivity;
import com.wpp.study.side.NavigationActivity;
import com.wpp.study.toolbar.ToolBarActivity;
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
        getWindow().setExitTransition(new Slide());
        startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
    }

    @OnClick(R.id.drawerlayout_bt)
    public void startDrawerLayout(){
        Intent intent = new Intent(this, DrawerLayoutActivity.class);
        getWindow().setExitTransition(new Fade());
        startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
    }

    @OnClick(R.id.navigation_bt)
    public void startNavigation(){
        Intent intent = new Intent(this, NavigationActivity.class);
        getWindow().setExitTransition(new Explode());
        startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
    }

    @OnClick(R.id.transition_bt)
    public void startTransition(){
        Intent intent = new Intent(this, TransitionActivity.class);
        getWindow().setExitTransition(new Slide());
        startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
    }

    @OnClick(R.id.toolbar_bt)
    public void startToolBar(){
        Intent intent = new Intent(this, ToolBarActivity.class);
        getWindow().setExitTransition(new Fade());
        startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
    }

    @OnClick(R.id.palette)
    public void startPalette(){
        Intent intent = new Intent(this, PaletteActivity.class);
        getWindow().setExitTransition(new Fade());
        startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
