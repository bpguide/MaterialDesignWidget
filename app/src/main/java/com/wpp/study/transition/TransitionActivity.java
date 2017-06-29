package com.wpp.study.transition;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wpp.study.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TransitionActivity extends AppCompatActivity {

    @BindView(R.id.activity_transition_layout_cover)
    ImageView mCover;
    @BindView(R.id.activity_transition_layout_play)
    ImageView mPlay;
    @BindView(R.id.activity_transition_layout_way)
    TextView mWay;
    private int mCurrentWay = 4;
    private Unbinder unbind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_layout);
        unbind = ButterKnife.bind(this);
        mWay.setText("makeSceneTransitionAnimation");
        Glide.with(this).asBitmap().load("http://oqt303ir6.bkt.clouddn.com/17-5-24/51898875.jpg").into(mCover);
        Glide.with(this).asBitmap().load(R.mipmap.play_big).into(mPlay);

    }

    @OnClick(R.id.activity_transition_layout_fade)
    public void startActivityWithFade(View view){
        Fade fade = new Fade();
//        fade.setMode();//设置模式
//        fade.setDuration(800);
//        fade.setInterpolator()
//        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(TransitionActivity.this);
        Intent intent = new Intent(TransitionActivity.this, ActivityB.class);
        intent.putExtra("cover", "http://oqt303ir6.bkt.clouddn.com/17-5-24/35325554.jpg");
        startActivity(intent, activityOptionsCompat.toBundle());
    }

    @OnClick(R.id.activity_transition_layout_slide)
    public void startActivityWithSlide(View view){
        Slide slide = new Slide();
        slide.setDuration(800);
//        getWindow().setEnterTransition(slide);
        getWindow().setExitTransition(slide);
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(TransitionActivity.this);
        Intent intent = new Intent(TransitionActivity.this, ActivityB.class);
        intent.putExtra("cover", "http://oqt303ir6.bkt.clouddn.com/17-5-24/35325554.jpg");
        startActivity(intent, activityOptionsCompat.toBundle());
    }

    @OnClick(R.id.activity_transition_layout_explode)
    public void startActivityWithExplode(View view){
        Explode explode = new Explode();
        explode.setDuration(800);
//        getWindow().setEnterTransition(explode);
        getWindow().setExitTransition(explode);

        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(TransitionActivity.this);
        Intent intent = new Intent(TransitionActivity.this, ActivityB.class);
        intent.putExtra("cover", "http://oqt303ir6.bkt.clouddn.com/17-5-24/35325554.jpg");
        startActivity(intent, activityOptionsCompat.toBundle());
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.activity_transition_layout_cover)
    public void startSecondActivity(View v){
        switch (mCurrentWay){
            case 1:
                /**
                 * 第一个参数：以哪个view为锚点
                 * 第二个参数：初始x值
                 * 第三个参数：初始y值
                 * 第四五个参数：被启动的activity初始从多宽多高开始动画
                 */
                ActivityOptionsCompat options1 = ActivityOptionsCompat.makeClipRevealAnimation(v, v.getWidth() / 2, v.getHeight() / 2, 20, 20);
                Intent intent1 = new Intent(TransitionActivity.this, ActivityB.class);
                intent1.putExtra("cover", "http://oqt303ir6.bkt.clouddn.com/17-5-24/35325554.jpg");
                startActivity(intent1, options1.toBundle());
                break;
            case 2:
                /**
                 * 普通动画
                 */
                ActivityOptionsCompat options2 = ActivityOptionsCompat.makeCustomAnimation(TransitionActivity.this, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                Intent intent2 = new Intent(TransitionActivity.this, ActivityB.class);
                intent2.putExtra("cover", "http://oqt303ir6.bkt.clouddn.com/17-5-24/35325554.jpg");
                startActivity(intent2, options2.toBundle());
                break;
            case 3:
                ActivityOptionsCompat options3 = ActivityOptionsCompat.makeScaleUpAnimation(v, v.getWidth() / 2, v.getHeight() / 2, -20, -20);
                Intent intent3 = new Intent(TransitionActivity.this, ActivityB.class);
                intent3.putExtra("cover", "http://oqt303ir6.bkt.clouddn.com/17-5-24/35325554.jpg");
                startActivity(intent3, options3.toBundle());
                break;
            case 4:
                Pair<View, String> cover = Pair.create((View)mCover, "cover");
                Pair<View, String> play = Pair.create((View)mPlay, "play");
                /**
                 * 共享元素方式：
                 * 可以传多个Pair，包装View，也可以传单个View
                 */
                ActivityOptionsCompat options4 = ActivityOptionsCompat.makeSceneTransitionAnimation(TransitionActivity.this, cover, play);
                Intent intent4 = new Intent(TransitionActivity.this, ActivityB.class);
                intent4.putExtra("cover", "http://oqt303ir6.bkt.clouddn.com/17-5-24/51898875.jpg");
                startActivity(intent4, options4.toBundle());
                break;
            case 5:
                ActivityOptionsCompat options5 = ActivityOptionsCompat.makeThumbnailScaleUpAnimation(v, BitmapFactory.decodeResource(getResources(), R.mipmap.b), v.getWidth() / 2, v.getHeight() / 2);
                Intent intent5 = new Intent(TransitionActivity.this, ActivityB.class);
                intent5.putExtra("cover", "http://oqt303ir6.bkt.clouddn.com/17-5-24/35325554.jpg");
                startActivity(intent5, options5.toBundle());
                break;
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.makeClipRevealAnimation:
                mWay.setText("makeClipRevealAnimation");
                mCurrentWay = 1;
                break;
            case R.id.makeCustomAnimation:
                mWay.setText("makeCustomAnimation");
                mCurrentWay = 2;
                break;
            case R.id.makeScaleUpAnimation:
                mWay.setText("makeScaleUpAnimation");
                mCurrentWay = 3;
                break;
            case R.id.makeSceneTransitionAnimation:
                mWay.setText("makeSceneTransitionAnimation");
                mCurrentWay = 4;
                break;
            case R.id.makeThumbnailScaleUpAnimation:
                mWay.setText("makeThumbnailScaleUpAnimation");
                mCurrentWay = 5;
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbind.unbind();
    }
}
