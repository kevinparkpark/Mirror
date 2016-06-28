package com.kevin.mirror.netutils.netinterface;

import com.android.volley.VolleyError;

/**
 * Created by kevin on 16/6/20.
 */
public interface NetListener {
    void onSuccessed(String result);
    void onFailed(VolleyError error);
}
