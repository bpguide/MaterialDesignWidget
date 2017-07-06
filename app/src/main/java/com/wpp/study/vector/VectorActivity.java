package com.wpp.study.vector;

import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.wpp.study.R;

public class VectorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vector_layout);
    }

    public void startAnima1(View view){
        if(view != null){
            Animatable drawable = (Animatable) ((ImageView) view).getDrawable();
            drawable.start();
        }
    }
}
