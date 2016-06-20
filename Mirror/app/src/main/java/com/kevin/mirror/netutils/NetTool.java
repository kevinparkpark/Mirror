package com.kevin.mirror.netutils;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by kevin on 16/6/20.
 */
public class NetTool {
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    public NetTool() {
        requestQueue=VolleySingleton.getInstance().getRequestQueue();
        imageLoader=VolleySingleton.getInstance().getImageLoader();
    }
    //输入URl 解析
    public void getUrl(final NetListener netListener,String url){

        StringRequest stringRequest=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                netListener.onSuccessed(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                netListener.onFailed(error);
            }
        });
        requestQueue.add(stringRequest);
    }

}
