package com.wpp.study.drawerlayout;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends ActionBarActivity {

    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final PeriscopeView periscope = (PeriscopeView) findViewById(R.id.periscope);
        setSupportActionBar(toolbar);
        final View view = findViewById(R.id.imageview);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                periscope.addHeart();
            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        toggle.syncState();
//        drawerLayout.addDrawerListener(toggle);
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                View menu = drawerView;
                View content = drawerLayout.getChildAt(0);
//                menu.setScaleX();
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }
}
