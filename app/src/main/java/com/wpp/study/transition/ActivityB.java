package com.wpp.study.transition;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wpp.study.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ActivityB extends AppCompatActivity {
    private Unbinder unbind;
    @BindView(R.id.activity_item2_layout_cover)
    ImageView cover_iv;

    @BindView(R.id.activity_item2_layout_play)
    ImageView play;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Slide slide = new Slide();
        getWindow().setEnterTransition(slide);
        setContentView(R.layout.activity_b_layout);
        unbind = ButterKnife.bind(this);

        Intent intent = getIntent();
        Glide.with(this).asBitmap().load(intent.getStringExtra("cover")).into(cover_iv);
        Glide.with(this).asBitmap().load(R.mipmap.play_big).into(play);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbind.unbind();
    }
}
