package com.kevin.mirror;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevin.mirror.base.BaseFragment;
import com.kevin.mirror.loginandregister.LoginActivity;
import com.kevin.mirror.mainpage.MenuFragment;
import com.kevin.mirror.mainpage.MenuOnClickListener;
import com.kevin.mirror.mainpage.allkinds.AllKindsFragment;
import com.kevin.mirror.mainpage.glasses.GlassesFragment;
import com.kevin.mirror.mainpage.MainAdapter;
import com.kevin.mirror.mainpage.ShoppingFragment;
import com.kevin.mirror.mainpage.special.SpecialFragment;
import com.kevin.mirror.mainpage.sunglasses.SunGlassesFragment;

import java.util.ArrayList;

import fr.castorflex.android.verticalviewpager.VerticalViewPager;

public class MainActivity extends AppCompatActivity implements MenuOnClickListener
        , MenuFragment.Menu2MainOnClickListener {
    private ArrayList<BaseFragment> fragments;
    private MainAdapter adapter;
    private VerticalViewPager viewPager;
    private MenuFragment menuFragment;
    private ImageView ivLogo;
    private TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivLogo = (ImageView) findViewById(R.id.iv_main_logo);
        tvLogin= (TextView) findViewById(R.id.tv_main_login);

        viewPager = (VerticalViewPager) findViewById(R.id.verticalviewpager_main);
        adapter = new MainAdapter(getSupportFragmentManager());
        fragments = new ArrayList<>();
        fragments.add(new AllKindsFragment());
        fragments.add(new GlassesFragment());
        fragments.add(new SunGlassesFragment());
        fragments.add(new SpecialFragment());
        fragments.add(new ShoppingFragment());
        adapter.setFragments(fragments);
        viewPager.setAdapter(adapter);

        ivLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAminations();
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }

    @Override
    public void onMenuClickListener(int position) {
        menuFragment = new MenuFragment();
        getSupportFragmentManager().beginTransaction().replace(
                R.id.framelayout_main, menuFragment).commit();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        menuFragment.setArguments(bundle);
    }

    @Override
    public void onMenu2MainClickListener(int position) {
        getSupportFragmentManager().beginTransaction().hide(menuFragment).commit();
        viewPager.setCurrentItem(position);
    }

    private void setAminations() {
        AnimationSet localAnimationSet = new AnimationSet(true);
        new ScaleAnimation(1F, 1.10000002384185791016F, 1F, 1.10000002384185791016F, 1, 0.5F, 1, 0.5F).setDuration(250L);
        ScaleAnimation localScaleAnimation = new ScaleAnimation(1.10000002384185791016F, 0.89999997615814208984F, 1.10000002384185791016F, 0.89999997615814208984F, 1, 0.5F, 1, 0.5F);
        localScaleAnimation.setDuration(250L);
        localAnimationSet.addAnimation(localScaleAnimation);
        ivLogo.startAnimation(localAnimationSet);
    }
}
