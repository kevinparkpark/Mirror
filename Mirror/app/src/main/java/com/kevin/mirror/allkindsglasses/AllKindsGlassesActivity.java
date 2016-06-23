package com.kevin.mirror.allkindsglasses;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.kevin.mirror.R;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by dllo on 16/6/22.
 */
public class AllKindsGlassesActivity extends AutoLayoutActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_allkindsglasses);

        //开启工程所选择的哪页
        replaceFragment(R.id.fragment_replace,new AllKindsGlassesFragment());

    }

    public void replaceFragment(int id, Fragment fragment){
        //碎片管理器
        FragmentManager manager = getSupportFragmentManager();
        //fragmentTransaction 碎片是武器起连接作用
        FragmentTransaction transaction =manager.beginTransaction();
        transaction.replace(id,fragment);
        transaction.commit();
    }
}
