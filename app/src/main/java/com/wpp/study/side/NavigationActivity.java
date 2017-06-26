package com.wpp.study.side;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wpp.study.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class NavigationActivity extends AppCompatActivity {
    private Unbinder unbinder;
    @BindView(R.id.username)
    TextInputLayout username;
    @BindView(R.id.password)
    TextInputLayout password;
    @BindView(R.id.activity_navigationlayout_navigationView)
    NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigationlayout);
        unbinder = ButterKnife.bind(this);

        navigationView.setItemIconTintList(null);
        View headerView = navigationView.getHeaderView(0);
        ImageView head = ButterKnife.findById(headerView, R.id.navigation_head_layout_head_iv);
        TextView name = ButterKnife.findById(headerView, R.id.navigation_head_layout_name_tv);
        name.setText("我的名字");
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_camera:
                        Snackbar.make(navigationView, "相机", Snackbar.LENGTH_SHORT).show();
                        break;
                    case R.id.action_help:
                        Snackbar.make(navigationView, "帮助", Snackbar.LENGTH_SHORT).show();
                        break;
                    case R.id.action_gallary:
                        Snackbar.make(navigationView, "相册", Snackbar.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    @OnClick(R.id.login)
    public void login(View v){
        String userName = username.getEditText().getText().toString();
        String passWord = password.getEditText().getText().toString();
        if("".equals(userName)){
            username.setErrorEnabled(true);
            username.setError("用户名不能为空");
        } else {
            username.setErrorEnabled(false);
            username.setError(null);
        }

        if(passWord.length() < 6){
            password.setErrorEnabled(true);
            password.setError("密码不能小于6位");
        } else {
            password.setErrorEnabled(false);
            password.setError(null);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
