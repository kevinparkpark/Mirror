package com.kevin.mirror;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kevin.mirror.DB.DBUtils;
import com.kevin.mirror.base.BaseFragment;
import com.kevin.mirror.loginandregister.LoginActivity;
import com.kevin.mirror.mainpage.MenuFragment;
import com.kevin.mirror.mainpage.MenuOnClickListener;
import com.kevin.mirror.mainpage.allkinds.AllKindsFragment;
import com.kevin.mirror.mainpage.glasses.GlassesFragment;
import com.kevin.mirror.mainpage.MainAdapter;
import com.kevin.mirror.mainpage.shooping.ShoppingFragment;
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
    private DBUtils dbUtils=new DBUtils();
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivLogo = (ImageView) findViewById(R.id.iv_main_logo);
        tvLogin= (TextView) findViewById(R.id.tv_main_login);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

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
        //获取token设置textview
        SharedPreferences getsp = getSharedPreferences("token", MODE_PRIVATE);
        token = getsp.getString("token","0");
        if (!token.equals("0")){
            tvLogin.setText("购物车");
        }
        //login点击事件
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences getsp = getSharedPreferences("token", MODE_PRIVATE);
                token = getsp.getString("token","0");
                if (token.equals("0")){
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }else {
                    viewPager.setCurrentItem(4);
                }
            }
        });
    }
    //回调onstart方法
    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences getsp = getSharedPreferences("token", MODE_PRIVATE);
        token = getsp.getString("token","0");
        if (!token.equals("0")){
            tvLogin.setText("购物车");
        }
    }
    //跳转到menufragment
    @Override
    public void onMenuClickListener(int position) {
        menuFragment = new MenuFragment();
        getSupportFragmentManager().beginTransaction().replace(
                R.id.framelayout_main, menuFragment).commit();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        menuFragment.setArguments(bundle);
    }
    //跳转回activity
    @Override
    public void onMenu2MainClickListener(int position) {
        getSupportFragmentManager().beginTransaction().hide(menuFragment).commit();
        viewPager.setCurrentItem(position);
        tvLogin.setText("登录");
    }
    //logo动画
    private void setAminations() {
        AnimationSet localAnimationSet = new AnimationSet(true);
        new ScaleAnimation(1F, 1.10000002384185791016F, 1F, 1.10000002384185791016F, 1, 0.5F, 1, 0.5F).setDuration(250L);
        ScaleAnimation localScaleAnimation = new ScaleAnimation(1.10000002384185791016F, 0.89999997615814208984F, 1.10000002384185791016F, 0.89999997615814208984F, 1, 0.5F, 1, 0.5F);
        localScaleAnimation.setDuration(250L);
        localAnimationSet.addAnimation(localScaleAnimation);
        ivLogo.startAnimation(localAnimationSet);
    }
    //点击2此退出
    private long exitTime = 0;

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(menuFragment.isVisible()){
            getSupportFragmentManager().beginTransaction().hide(menuFragment).commit();
            return false;
        }
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "真的要退出吗^_^?", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
