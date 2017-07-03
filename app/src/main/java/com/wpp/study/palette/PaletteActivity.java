package com.wpp.study.palette;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.wpp.study.R;

import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class PaletteActivity extends AppCompatActivity {
    private Unbinder bind;
    @BindViews({R.id.activity_palette_layout_image1, R.id.activity_palette_layout_image2, R.id.activity_palette_layout_image3,
            R.id.activity_palette_layout_image4, R.id.activity_palette_layout_image5, R.id.activity_palette_layout_image6, })
    ImageView []mImages;

    @BindViews({R.id.activity_palette_layout_title1, R.id.activity_palette_layout_title2, R.id.activity_palette_layout_title3,
            R.id.activity_palette_layout_title4, R.id.activity_palette_layout_title5, R.id.activity_palette_layout_title6, })
    TextView []mTitles;
    private String []urls = {"http://oqt303ir6.bkt.clouddn.com/17-6-3/39342074.jpg",
                            "http://oqt303ir6.bkt.clouddn.com/17-6-3/65241765.jpg",
                            "http://oqt303ir6.bkt.clouddn.com/17-6-3/67047786.jpg",
                            "http://oqt303ir6.bkt.clouddn.com/17-6-3/73495962.jpg",
                            "http://oqt303ir6.bkt.clouddn.com/17-6-3/83388043.jpg",
                            "http://oqt303ir6.bkt.clouddn.com/17-6-3/8614957.jpg"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette_layout);
        bind = ButterKnife.bind(this);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int widthPixels = displayMetrics.widthPixels;
        double viewWidth = (widthPixels - 1) / 2.0;

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int)viewWidth, (int)viewWidth);
        for (int i = 0; i < urls.length; i++){
            final int finalI = i;
            mImages[i].setLayoutParams(params);
            Glide.with(this).asBitmap().load(urls[i]).into(new BitmapImageViewTarget(mImages[i]){
                @Override
                public void onResourceReady(Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    super.onResourceReady(resource, transition);

                    Palette.from(resource).generate(new Palette.PaletteAsyncListener() {
                        @Override
                        public void onGenerated(Palette palette) {
//                            palette.getDarkMutedColor();//暗的柔和的颜色
//                            palette.getDarkVibrantColor();//暗的活力的颜色
//                            palette.getLightMutedColor();//亮的柔和的颜色
//                            palette.getLightVibrantColor();//亮的活力颜色
//                            palette.getMutedColor();//柔和的颜色
//                            palette.getVibrantColor();//活力颜色
//                            palette.getDominantColor();//主色调

                            Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
                            Palette.Swatch mutedSwatch = palette.getMutedSwatch();
                            Palette.Swatch lightMutedSwatch = palette.getLightMutedSwatch();
                            Palette.Swatch dominantSwatch = palette.getDominantSwatch();
                            Palette.Swatch darkMutedSwatch = palette.getDarkMutedSwatch();
                            Palette.Swatch darkVibrantSwatch = palette.getDarkVibrantSwatch();
                            Palette.Swatch lightVibrantSwatch = palette.getLightVibrantSwatch();
                            Palette.Swatch targetSwatch;
                            if(darkMutedSwatch != null){
                                targetSwatch = darkMutedSwatch;
                            } else if(lightVibrantSwatch != null){
                                targetSwatch = lightVibrantSwatch;
                            } else {
                                targetSwatch = vibrantSwatch;
                            }
                            mTitles[finalI].setBackgroundColor(setAlphaWithRGB(0.85f, targetSwatch.getRgb()));
                            mTitles[finalI].setTextColor(targetSwatch.getBodyTextColor());
                        }
                    });
                }
            });
        }


    }

    private int setAlphaWithRGB(float percent, int rgb){
        int blue = rgb & 0xFF;
        int green = rgb >> 8 & 0Xff;
        int red = rgb >> 16 & 0xff;
        int alpha = rgb >>> 24;
        return Color.argb((int) (alpha * percent), red, green, blue);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
