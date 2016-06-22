package com.kevin.mirror.netutils;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

/**
 * Created by kevin on 16/6/21.
 */
public class OkHttpSingleton {
    private static OkHttpSingleton ourInstance = new OkHttpSingleton();
    private OkHttpClient okHttpClient;
    private Request request;

    public static OkHttpSingleton getInstance() {

        return ourInstance;
    }

    private OkHttpSingleton() {
        okHttpClient=new OkHttpClient();
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
