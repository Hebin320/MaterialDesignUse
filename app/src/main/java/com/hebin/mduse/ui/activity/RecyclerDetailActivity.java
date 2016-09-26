package com.hebin.mduse.ui.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.hebin.mduse.R;
import com.hebin.mduse.entity.RecyclerBean;
import com.hebin.mduse.ui.fragment.DetailFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecyclerDetailActivity extends AppCompatActivity {

    @Bind(R.id.ivImage)
    ImageView ivImage;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.sliding_tabs)
    TabLayout slidingTabs;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.activity_recycler_detail)
    CoordinatorLayout activityRecyclerDetail;

    private RecyclerBean recyclerBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        assert toolbar != null;
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        recyclerBean = (RecyclerBean) getIntent().getSerializableExtra("main");
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(recyclerBean.getTitle());

        ImageView ivImage = (ImageView) findViewById(R.id.ivImage);
        ivImage.setBackgroundResource(recyclerBean.getImglarge());
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewpager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.addTab(tabLayout.newTab().setText("测试"));
        tabLayout.addTab(tabLayout.newTab().setText("测试"));
        tabLayout.addTab(tabLayout.newTab().setText("测试"));
        tabLayout.setupWithViewPager(viewpager);
    }

    private void setupViewPager(ViewPager mViewPager) {
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(DetailFragment.newInstance(recyclerBean.getTitle()), "测试");
        adapter.addFragment(DetailFragment.newInstance(recyclerBean.getTitle()), "测试");
        adapter.addFragment(DetailFragment.newInstance(recyclerBean.getTitle()), "测试");
        mViewPager.setAdapter(adapter);
    }

    static class MyPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
