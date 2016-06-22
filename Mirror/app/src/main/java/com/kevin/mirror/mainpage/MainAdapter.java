package com.kevin.mirror.mainpage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.kevin.mirror.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by kevin on 16/6/21.
 */
public class MainAdapter extends FragmentStatePagerAdapter{
    private ArrayList<BaseFragment> fragments;

    public void setFragments(ArrayList<BaseFragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments==null?0:fragments.size();
    }
}
