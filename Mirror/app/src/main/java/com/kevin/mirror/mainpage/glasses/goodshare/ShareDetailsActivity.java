package com.kevin.mirror.mainpage.glasses.goodshare;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.kevin.mirror.R;
import com.kevin.mirror.netutils.ImageNetListener;
import com.kevin.mirror.netutils.NetTool;
import com.kevin.mirror.netutils.VolleySingleton;

/**
 * Created by kevin on 16/6/25.
 */
public class ShareDetailsActivity extends AppCompatActivity {
    private ImageView imageView;
    private ImageLoader imageLoader = VolleySingleton.getInstance().getImageLoader();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedetails);
        imageView = (ImageView) findViewById(R.id.iv_sharedetails);

        String url = getIntent().getStringExtra("url");
//        imageLoader.get(url, ImageLoader.getImageListener(imageView,
//                R.mipmap.img_gray_color, R.mipmap.img_gray_color));
        NetTool netTool=new NetTool();
        netTool.getImage(url, new ImageNetListener() {
            @Override
            public void onSuccessed(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();
            }
        });

    }

    public void animationIn() {
        AnimationSet localAnimationSet = new AnimationSet(true);
        ScaleAnimation localScaleAnimation = new ScaleAnimation(
                0.8F, 1F, 0.8F, 1F, 1, 0.5F, 1, 0.5F);
        localScaleAnimation.setDuration(500);
        localAnimationSet.addAnimation(localScaleAnimation);
        imageView.startAnimation(localAnimationSet);
    }

    public void animationOut() {
        AnimationSet localAnimationSet = new AnimationSet(true);
        ScaleAnimation localScaleAnimation = new ScaleAnimation(
                1F, 0.8F, 1F, 0.8F, 1, 0.5F, 1, 0.5F);
        localScaleAnimation.setDuration(500);
        localAnimationSet.addAnimation(localScaleAnimation);
        imageView.startAnimation(localAnimationSet);
    }

}
