package com.kevin.mirror.netutils;


import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.kevin.mirror.MyApp;

import java.io.File;

/**
 * Created by kevin on 16/5/23.
 */
public class VolleySingleton {
    private static VolleySingleton ourInstance = new VolleySingleton();
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private Gson gson;
    private static final String DEFAULT_CACHE_DIR="DEFAULT_CACHE_DIR";

    public static VolleySingleton getInstance() {

        return ourInstance;
    }

    private VolleySingleton() {

//        File cacheDir = new File(MyApp.context.getCacheDir(), DEFAULT_CACHE_DIR);
        requestQueue= Volley.newRequestQueue(MyApp.context);
        imageLoader=new ImageLoader(requestQueue,new MemoryCache());
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }
    public Gson getGson(){
        return gson;
    }
}

