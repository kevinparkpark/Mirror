package com.kevin.mirror.netutils;

import android.graphics.Bitmap;

import com.android.volley.VolleyError;

/**
 * Created by kevin on 16/6/22.
 */
public interface ImageNetListener {
    void onSuccessed(Bitmap bitmap);
    void onFailed(VolleyError error);
}
