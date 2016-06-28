package com.kevin.mirror.netutils.netinterface;

import com.android.volley.VolleyError;

/**
 * Created by kevin on 16/6/25.
 */
public interface NetBeanListener {
    <T>void onSuccessed(T bean);
    void onFailed(VolleyError error);
}
