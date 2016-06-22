package com.kevin.mirror;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.kevin.mirror.base.BaseFragment;
import com.kevin.mirror.mainpage.MenuFragment;
import com.kevin.mirror.mainpage.MenuOnClickListener;
import com.kevin.mirror.mainpage.allkinds.AllKindsFragment;
import com.kevin.mirror.mainpage.GlassesFragment;
import com.kevin.mirror.mainpage.MainAdapter;
import com.kevin.mirror.mainpage.ShoppingFragment;
import com.kevin.mirror.mainpage.SpecialFragment;
import com.kevin.mirror.mainpage.sunglasses.SunGlassesFragment;

import java.util.ArrayList;

import fr.castorflex.android.verticalviewpager.VerticalViewPager;

public class MainActivity extends AppCompatActivity implements MenuOnClickListener
        ,MenuFragment.Menu2MainOnClickListener{
    private ArrayList<BaseFragment> fragments;
    private MainAdapter adapter;
    private VerticalViewPager viewPager;
    private MenuFragment menuFragment;

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

    @Override
    public void onMenuClickListener(int position) {
        menuFragment=new MenuFragment();
        Log.d("MainActivity", "onmenuclick");
        getSupportFragmentManager().beginTransaction().replace(
                R.id.framelayout_main,menuFragment).commit();
        Bundle bundle=new Bundle();
        bundle.putInt("position",position);
        menuFragment.setArguments(bundle);
    }

    @Override
    public void onMenu2MainClickListener(int position) {
        Log.d("MainActivity", "position:" + position);
        getSupportFragmentManager().beginTransaction().hide(menuFragment).commit();
        viewPager.setCurrentItem(position);
    }
}
