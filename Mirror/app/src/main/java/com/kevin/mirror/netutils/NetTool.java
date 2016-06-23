package com.kevin.mirror.netutils;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kevin on 16/6/20.
 */
public class NetTool {
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    public NetTool() {
        requestQueue = VolleySingleton.getInstance().getRequestQueue();
        imageLoader = VolleySingleton.getInstance().getImageLoader();
    }

    //输入URl 解析
    public void getUrl(String url, final NetListener netListener) {

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
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

    //获取图片及状态
    public void getImage(String url, final ImageNetListener imageNetListener) {
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imageNetListener.onSuccessed(response);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                imageNetListener.onFailed(error);
            }
        });
        requestQueue.add(imageRequest);
    }

    //post请求
    public void postRequest(String url, final String token, final String devicetype, final String category,
                            final NetListener netListener) {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                netListener.onSuccessed(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                netListener.onFailed(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> body = new HashMap<>();
                body.put("token", token);
                body.put("device_type", devicetype);
                body.put("category_id", category);
                return body;
            }
        };
        requestQueue.add(request);
    }

    //注册,登录解析
    public void postLoginOrRegister(String url, final String phoneNum, final String captcha, final String ps, final NetListener netListener) {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                netListener.onSuccessed(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                netListener.onFailed(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> body = new HashMap<>();
                body.put("phone_number", phoneNum);
                body.put("number", captcha);
                body.put("password", ps);
                return body;
            }
        };
        requestQueue.add(request);
    }

    public void storyPostRequest(String url, final String deviceType, final String storyId, final NetListener netListener) {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                netListener.onSuccessed(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                netListener.onFailed(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> body = new HashMap<>();
                body.put("device_type", deviceType);
                body.put("story_id", storyId);
                return body;
            }
        };
        requestQueue.add(request);
    }

}
