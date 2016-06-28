package com.kevin.mirror.netutils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by kevin on 16/6/27.
 * 网络状态
 */
public final class NetWorkStatus {

    //判断网络
    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager manger = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manger.getActiveNetworkInfo();
            //return (info!=null && info.isConnected());
            if (info != null) {
                return info.isConnected();
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
