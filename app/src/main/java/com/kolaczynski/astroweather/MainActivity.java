package com.kolaczynski.astroweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.res.Configuration;
import android.media.VolumeShaper;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float yInches = metrics.heightPixels / metrics.ydpi;
        float xInches = metrics.widthPixels / metrics.xdpi;
        double diagonalInches = Math.sqrt(xInches * xInches + yInches * yInches);

        TabLayout tabLayout;
        TabItem tabSun;
        TabItem tabMoon;
        TabItem tabSettings;
        TabItem tabSunMoon;
        TabItem tablet;
        ViewPager viewPager;

        if (diagonalInches>=6.5){
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                tabLayout = findViewById(R.id.tabBarTabletLandscape);
                tablet = findViewById(R.id.tabletLandscape);
                viewPager = findViewById(R.id.viewPagerTabletLandscape);
            }else { //if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                tabLayout = findViewById(R.id.tabBarTablet);
                tablet = findViewById(R.id.tablet);
                viewPager = findViewById(R.id.viewPagerTablet);
            }
        }else {
            // smaller device
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                tabLayout = findViewById(R.id.tabBarLandscape);
                tabSettings = findViewById(R.id.settings);
                tabSunMoon = findViewById(R.id.SunMoonTab);
                viewPager = findViewById(R.id.viewPagerLandscape);
            } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                tabLayout = findViewById(R.id.tabBar);
                tabSun = findViewById(R.id.sun);
                tabMoon = findViewById(R.id.moon);
                tabSettings = findViewById(R.id.settings);
                viewPager = findViewById(R.id.viewPager);
            } else {
                tabLayout = findViewById(R.id.tabBar);
                viewPager = findViewById(R.id.viewPager);
            }

        }
        final ViewPager finalViewPager = viewPager;
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        finalViewPager.setAdapter(pagerAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                finalViewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
        finalViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}