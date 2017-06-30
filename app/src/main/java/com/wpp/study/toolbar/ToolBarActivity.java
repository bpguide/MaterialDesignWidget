package com.wpp.study.toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_toolbar_menu, menu);
        initSearchView(menu.findItem(R.id.app_bar_search));
        return super.onCreateOptionsMenu(menu);
    }

    private void initSearchView(MenuItem item){
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
//        searchView.setIconified(false);
//        searchView.setIconifiedByDefault(false);
        SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete) searchView.findViewById(R.id.search_src_text);
        searchAutoComplete.setHintTextColor(Color.WHITE);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("请输入文字");
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                //关闭搜索按钮时回调
                return false;
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //点击提交按钮时回调
                Toast.makeText(ToolBarActivity.this, "onQueryTextSubmit:"+query, Toast.LENGTH_SHORT).show();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                //文字改变时回调
                return false;
            }
        });
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开搜索视图后回调
            }
        });
    }
}
