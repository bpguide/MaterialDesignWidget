package com.wpp.study.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;


public class ColorMatrixActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ColorMatrixView view = new ColorMatrixView(this);
        setContentView(view);
    }
}
