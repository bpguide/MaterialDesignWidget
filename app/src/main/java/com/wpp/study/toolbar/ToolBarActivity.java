package com.wpp.study.toolbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.wpp.study.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ToolBarActivity extends AppCompatActivity {

    @BindView(R.id.activity_toolbar_layout_toolbar)
    Toolbar mToolBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_layout);
        ButterKnife.bind(this);

//        mToolBar.setTitle("");
        setSupportActionBar(mToolBar);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
