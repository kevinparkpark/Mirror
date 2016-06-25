package com.kevin.mirror.specialtoshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Scroller;

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
public class SpecialActivity extends FragmentActivity implements View.OnClickListener {
    private ImageView closeIv, shareIv, backgroundIv;
    private VerticalViewPager viewPager;
    private ProgressBar progressBar;
    private SpecialDetailsAdapter adapter;
    private SpecialDetailsBean specialDetailsBean;
    private List<String> imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special);

        initView();
        initData();

        closeIv.setOnClickListener(this);
        shareIv.setOnClickListener(this);

        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

    }

    private void initView() {
        closeIv = (ImageView) findViewById(R.id.specialClose);
        shareIv = (ImageView) findViewById(R.id.specialShare);
        viewPager = (VerticalViewPager) findViewById(R.id.specialViewPager);
        progressBar = (ProgressBar) findViewById(R.id.specialProgressBar);
        backgroundIv = (ImageView) findViewById(R.id.specialBackgroundIv);
    }

    private void initData() {
        final ImageLoader loader = VolleySingleton.getInstance().getImageLoader();
        adapter = new SpecialDetailsAdapter(MyApp.context);
        final NetTool netTool = new NetTool();
        specialDetailsBean = new SpecialDetailsBean();
        imageUrl = new ArrayList<>();
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        netTool.storyPostRequest(URLValues.STORYINFO_URL, "2", id, new NetListener() {
            @Override
            public void onSuccessed(String result) {
                Gson gson = new Gson();
                specialDetailsBean = gson.fromJson(result, SpecialDetailsBean.class);
                imageUrl = specialDetailsBean.getData().getStory_data().getImg_array();
                adapter.setSpecialDetailsBean(specialDetailsBean);
                viewPager.setAdapter(adapter);
                loader.get(imageUrl.get(0), ImageLoader.getImageListener(backgroundIv,
                        R.color.colorGray, R.color.colorGray));
                viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        loader.get(imageUrl.get(position), ImageLoader.getImageListener(backgroundIv,
                                R.color.colorGray, R.color.colorGray));
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.specialClose:
                finish();
                break;
            case R.id.specialShare:
                break;
            default:
        }
    }
}
