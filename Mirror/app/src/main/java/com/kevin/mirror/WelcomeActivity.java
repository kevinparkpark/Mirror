package com.kevin.mirror;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.google.gson.Gson;
import com.kevin.mirror.netutils.netinterface.NetListener;
import com.kevin.mirror.netutils.NetTool;
import com.kevin.mirror.netutils.URLValues;
import com.kevin.mirror.netutils.VolleySingleton;
import com.kevin.mirror.utils.WelcomeBean;

/**
 * Created by kevin on 16/6/23.
 */
public class WelcomeActivity extends AppCompatActivity{
    private ImageView ivImg;
    private ImageLoader imageLoader= VolleySingleton.getInstance().getImageLoader();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ivImg= (ImageView) findViewById(R.id.iv_welcome);

        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        NetTool netTool=new NetTool();
        netTool.getUrl(URLValues.SPLASH_URL, new NetListener() {
            @Override
            public void onSuccessed(String result) {
                Gson gson=new Gson();
                WelcomeBean bean=gson.fromJson(result,WelcomeBean.class);

                imageLoader.get(bean.getImg(),ImageLoader.getImageListener(ivImg,
                        R.mipmap.img_gray_color,R.mipmap.img_gray_color));
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
//        imageLoader.get("http://image.mirroreye.cn/108041a4a0be25dd37eee04582888eabe381.jpg",
//                new ImageLoader.ImageListener() {
//                    @Override
//                    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
//                        ivImg.setImageBitmap(response.getBitmap());
//                        Log.d("WelcomeActivity","----"+ response.getRequestUrl().toString());
//                    }
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                });

        CountDownTimer timer=new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }
            @Override
            public void onFinish() {
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                finish();
            }
        }.start();

    }
}
