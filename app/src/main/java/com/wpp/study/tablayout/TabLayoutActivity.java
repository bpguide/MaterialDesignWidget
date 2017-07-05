package com.wpp.study.tablayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.wpp.study.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabLayoutActivity extends AppCompatActivity {

    @BindView(R.id.activity_tablayout_layout_tablayout)
    TabLayout mTabLayout;

    @BindView(R.id.activity_tablayout_layout_viewpager)
    ViewPager mViewPager;

    private String[] mTitles = {"头条","本地","娱乐","体育","财经","科技","推荐"};//
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout_layout);
        ButterKnife.bind(this);

        mViewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
//        mTabLayout.addTab(mTabLayout.newTab().setText("").setIcon(R.mipmap.ic_launcher_round));

    }

    class MyViewPagerAdapter extends FragmentPagerAdapter{

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            SportsFragment fragment = new SportsFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title", mTitles[position]);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }
}
