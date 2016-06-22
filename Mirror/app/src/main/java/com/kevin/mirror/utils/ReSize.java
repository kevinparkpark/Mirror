package com.kevin.mirror.utils;

import com.kevin.mirror.MyApp;

/**
 * Created by kevin on 16/6/21.
 */
public class ReSize {

    public static int sp2px(float spValue) {
        final float fontScale = MyApp.context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int dp2px(float dpValue) {
        final float scale = MyApp.context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
