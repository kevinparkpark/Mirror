package com.kevin.mirror.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by dllo on 16/7/1.
 */
public class WindowManagerUtils {
    public static int getScreenWidthP(Context context) {
        //获取屏幕宽度
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //创建DisplayMetrics
        //屏幕的一些参数通过该类获取
        DisplayMetrics outMetrics = new DisplayMetrics();
        //调用WindowManager的getDefaultDisplay().getMetrics()方法
        //对这个outMetrics里的各个参数进行赋值
        manager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }
}
