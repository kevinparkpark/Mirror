package com.kevin.mirror.netutils;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.kevin.mirror.MyApp;

/**
 * Created by kevin on 16/5/23.
 */
public class VolleySingleton {
    private static VolleySingleton ourInstance = new VolleySingleton();
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private Gson gson;

    public static VolleySingleton getInstance() {

        return ourInstance;
    }

    private VolleySingleton() {

        requestQueue= Volley.newRequestQueue(MyApp.context);
        imageLoader=new ImageLoader(requestQueue,new MemoryCache());
        gson = new Gson();
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
