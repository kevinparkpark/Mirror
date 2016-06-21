package com.kevin.mirror;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kevin.mirror.base.BaseFragment;
import com.kevin.mirror.mainpage.AllKindsFragment;
import com.kevin.mirror.mainpage.GlassesFragment;
import com.kevin.mirror.mainpage.MainAdapter;
import com.kevin.mirror.mainpage.ShoppingFragment;
import com.kevin.mirror.mainpage.SpecialFragment;
import com.kevin.mirror.mainpage.SunGlassesFragment;

import java.util.ArrayList;

import fr.castorflex.android.verticalviewpager.VerticalViewPager;

public class MainActivity extends AppCompatActivity {
    private ArrayList<BaseFragment> fragments;
    private MainAdapter adapter;
    private VerticalViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager= (VerticalViewPager) findViewById(R.id.verticalviewpager_main);
        adapter=new MainAdapter(getSupportFragmentManager());
        fragments=new ArrayList<>();
        fragments.add(new AllKindsFragment());
        fragments.add(new GlassesFragment());
        fragments.add(new SunGlassesFragment());
        fragments.add(new SpecialFragment());
        fragments.add(new ShoppingFragment());
        adapter.setFragments(fragments);
        viewPager.setAdapter(adapter);
    }
}
