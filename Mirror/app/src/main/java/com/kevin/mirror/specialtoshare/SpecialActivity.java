package com.kevin.mirror.specialtoshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.google.gson.Gson;
import com.kevin.mirror.MyApp;
import com.kevin.mirror.R;
import com.kevin.mirror.netutils.NetListener;
import com.kevin.mirror.netutils.NetTool;
import com.kevin.mirror.netutils.URLValues;
import com.kevin.mirror.netutils.VolleySingleton;

import java.util.ArrayList;
import java.util.List;

import fr.castorflex.android.verticalviewpager.VerticalViewPager;

/**
 * Created by dllo on 16/6/21.
 */
public class SpecialActivity extends FragmentActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private ImageView closeIv, shareIv;
    private VerticalViewPager viewPager;
    private ProgressBar progressBar;
    private SpecialAdapter adapter;
    private SpecialBean specialBean;
    private List<String> imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special);

        initView();
        initData();

        closeIv.setOnClickListener(this);
        shareIv.setOnClickListener(this);
        viewPager.setOnPageChangeListener(this);
        viewPager.setAdapter(adapter);
    }

    private void initView() {
        closeIv = (ImageView) findViewById(R.id.close);
        shareIv = (ImageView) findViewById(R.id.share);
        viewPager = (VerticalViewPager) findViewById(R.id.specialViewPager);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    private void initData() {
        NetTool netTool = new NetTool();
        specialBean = new SpecialBean();
        imageUrl = new ArrayList<>();
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        netTool.storyPostRequest(URLValues.STORYINFO_URL, "2", id, new NetListener() {
            @Override
            public void onSuccessed(String result) {
                Gson gson = new Gson();
                specialBean = gson.fromJson(result, SpecialBean.class);
                imageUrl = specialBean.getData().getStory_data().getImg_array();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailed(VolleyError error) {
                progressBar.setVisibility(View.VISIBLE);
            }

        });
        adapter = new SpecialAdapter(this, specialBean);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close:
                finish();
                break;
            case R.id.share:
                break;
            default:
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ImageView imageView = new ImageView(MyApp.context);
        ImageLoader loader = VolleySingleton.getInstance().getImageLoader();
        loader.get(imageUrl.get(position), ImageLoader.getImageListener(imageView, R.mipmap.ic_launcher, R.mipmap.ic_launcher));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
