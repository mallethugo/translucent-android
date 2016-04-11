package com.mallethugo.translucentdemo;

import android.app.Activity;
import android.graphics.Point;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;

import com.mallethugo.translucent.TranslucentView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Fragment> fragments = new ArrayList<>();

        fragments.add(Fragment.instantiate(this, Fragment1.class.getName()));
        fragments.add(Fragment.instantiate(this, Fragment2.class.getName()));
        fragments.add(Fragment.instantiate(this, Fragment3.class.getName()));
        fragments.add(Fragment.instantiate(this, Fragment4.class.getName()));
        fragments.add(Fragment.instantiate(this, Fragment5.class.getName()));

        TranslucentView translucent = (TranslucentView) findViewById(R.id.main_translucent);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        translucent.setStartPositions(10, Math.round(height / 2));

        ViewPager viewPager = (ViewPager) findViewById(R.id.main_view_pager);
        PagerAdapter pagerAdapter = new MyPagerAdapter(super.getSupportFragmentManager(), fragments);

        viewPager.setAdapter(pagerAdapter);
    }
}
