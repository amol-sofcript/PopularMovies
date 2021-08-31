package com.amol.bharatagri;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.amol.bharatagri.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;


public class MainNavigationActivity extends AppCompatActivity {

    public static final String API_KEY = "e511b6f3e12896ca722f83492fbf610b";

    ViewPager mViewPager;
    ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar mToolbar = findViewById(R.id.toolbar);
       // setSupportActionBar(mToolbar);

        setViewPager();

    }

    private void setViewPager() {

        ViewPager mViewPager = findViewById(R.id.pager);
        ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mViewPagerAdapter);

        TabLayout mTabLayout = findViewById(R.id.tab);
        mTabLayout.setupWithViewPager(mViewPager);

    }

}