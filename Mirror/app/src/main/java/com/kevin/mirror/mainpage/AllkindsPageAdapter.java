package com.kevin.mirror.mainpage;

import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * Created by kevin on 16/6/21.
 */
public class AllkindsPageAdapter extends PagerAdapter{
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
