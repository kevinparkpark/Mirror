package com.kevin.mirror.netutils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.kevin.mirror.MyApp;
import com.kevin.mirror.netutils.netinterface.ImageNetListener;
import com.kevin.mirror.netutils.netinterface.NetBeanListener;
import com.kevin.mirror.netutils.netinterface.NetListener;

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
        if (!isNetworkAvailable(MyApp.context) && requestQueue.getCache().get(url) != null) {
            String response = new String(requestQueue.getCache().get(url).data);
            netListener.onSuccessed(response);
        } else {
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
        if (!isNetworkAvailable(MyApp.context) && requestQueue.getCache().get(url) != null) {
            String response = new String(requestQueue.getCache().get(url).data);
            netListener.onSuccessed(response);
        } else {

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
                    body.put("version", "1.0.1");
                    return body;
                }
            };
            requestQueue.add(request);
        }

    }

    public void addAddress(String url, final String token, final String addrId, final String username, final String cellphone, final String addrInfo,
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
                body.put("username", username);
                body.put("cellphone", cellphone);
                body.put("addr_info", addrInfo);
                body.put("addr_id", addrId);
                return body;
            }
        };
        requestQueue.add(request);
    }

    public void deleteAddress(String url, final String token, final String addrId, final NetListener netListener) {
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
                body.put("addr_id", addrId);
                return body;
            }
        };
        requestQueue.add(request);
    }
    //接到从fragment传来的信息

    public void postGoodList(String url, final String token, final String devicetype, final String goodsId, final NetListener netListener) {
        if (!isNetworkAvailable(MyApp.context) && requestQueue.getCache().get(url) != null) {
            String response = new String(requestQueue.getCache().get(url).data);
            netListener.onSuccessed(response);
        } else {
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
                    body.put("goods_id", goodsId);
                    body.put("version", "1.0.1");
                    return body;
                }
            };
            requestQueue.add(request);
        }
    }


    //注册,登录解析
    public void postRegister(String url, final String phoneNum, final String captcha, final String ps, final NetListener netListener) {
        if (!isNetworkAvailable(MyApp.context) && requestQueue.getCache().get(url) != null) {
            String response = new String(requestQueue.getCache().get(url).data);
            netListener.onSuccessed(response);
        } else {
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
    }

    public void postLogin(String url, final String phoneNum, final String ps, final NetListener netListener) {
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
                body.put("password", ps);
                return body;
            }
        };
        requestQueue.add(request);
    }

    public void storyPostRequest(String url, final String deviceType, final String storyId, final NetListener netListener) {
        if (!isNetworkAvailable(MyApp.context) && requestQueue.getCache().get(url) != null) {
            String response = new String(requestQueue.getCache().get(url).data);
            netListener.onSuccessed(response);
        } else {
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


    //泛型
    public <T> void postTRequest(final Class<T> clazz, String url, final String token, final String devicetype, final String category
            , final String goodsId, final String phoneNum, final String ps, final String captcha
            , final String storyId, final NetBeanListener netBeanListener) {
        if (!isNetworkAvailable(MyApp.context) && requestQueue.getCache().get(url) != null) {
            String response = new String(requestQueue.getCache().get(url).data);
            netBeanListener.onSuccessed(response);
        } else {
            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Gson gson = new Gson();
                    T bean = gson.fromJson(response, clazz);
                    netBeanListener.onSuccessed(bean);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    netBeanListener.onFailed(error);
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> body = new HashMap<>();
                    body.put("token", token);
                    body.put("device_type", devicetype);
                    body.put("category_id", category);
                    body.put("goods_id", goodsId);
                    body.put("version", "1.0.1");
                    body.put("story_id", storyId);
                    body.put("phone_number", phoneNum);
                    body.put("password", ps);
                    body.put("number", captcha);
                    return body;
                }
            };
            requestQueue.add(request);
        }
    }

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
