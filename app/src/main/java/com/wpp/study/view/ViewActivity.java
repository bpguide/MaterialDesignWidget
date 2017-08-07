package com.wpp.study.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.wpp.study.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewActivity extends Activity {

    @BindView(R.id.activity_view_customProgress1)
    CustomProgressBar mCustomProgressBar1;

    @BindView(R.id.activity_view_customProgress2)
    CustomProgressBar mCustomProgressBar2;
    private int progress = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.activity_view_start)
    public void startProgress(){
        progress = 0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progress <= 100){
                    progress = progress + 2;
                    mCustomProgressBar1.setProgress(progress);
                    mCustomProgressBar2.setProgress(progress);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
