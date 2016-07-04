package com.kevin.mirror.mainpage.glasses.goodshare;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.google.gson.Gson;
import com.kevin.mirror.DB.DBUtils;
import com.kevin.mirror.R;
import com.kevin.mirror.allkindsglasses.AllKindsGlassesActivity;
import com.kevin.mirror.allkindsglasses.AllKindsGlassesDetailsBeaen;
import com.kevin.mirror.loginandregister.LoginActivity;
import com.kevin.mirror.netutils.netinterface.NetListener;
import com.kevin.mirror.netutils.NetTool;
import com.kevin.mirror.netutils.URLValues;
import com.kevin.mirror.netutils.VolleySingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 16/6/24.
 */
public class GoodShareActivity extends AppCompatActivity {
    private VideoView videoView;
    private ImageView iv1, iv2, iv3, iv4, iv5, ivImg, ivPlay, ivBack;
    private TextView tvBuy;
    private ImageLoader imageLoader = VolleySingleton.getInstance().getImageLoader();
    private String goodId;
    private ProgressBar progressBar, progressBarVv;
    private AllKindsGlassesDetailsBeaen detailsBeaen;
    private String videoUrl, videoImg;
    private ArrayList<String> imgList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodshare);
        goodId = getIntent().getStringExtra("goods_id");

        videoView = (VideoView) findViewById(R.id.vv_goodshare_vv);
        iv1 = (ImageView) findViewById(R.id.iv_goodshare_img1);
        iv2 = (ImageView) findViewById(R.id.iv_goodshare_img2);
        iv3 = (ImageView) findViewById(R.id.iv_goodshare_img3);
        iv4 = (ImageView) findViewById(R.id.iv_goodshare_img4);
        iv5 = (ImageView) findViewById(R.id.iv_goodshare_img5);
        ivImg = (ImageView) findViewById(R.id.iv_goodshare_vv_bac);
        ivPlay = (ImageView) findViewById(R.id.iv_goodshare_vv_play);
        ivBack = (ImageView) findViewById(R.id.iv_goodshare_iv_back);
        tvBuy = (TextView) findViewById(R.id.tv_goodshare_buy);

        progressBar = (ProgressBar) findViewById(R.id.progressbar_goodshare);
        progressBarVv = (ProgressBar) findViewById(R.id.progressbar_goodshare_video);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        NetTool netTool = new NetTool();
        netTool.postGoodList(URLValues.GOODSINFO_URL, "", "2", goodId, new NetListener() {
            @Override
            public void onSuccessed(String result) {
                Gson gson = new Gson();
                detailsBeaen = gson.fromJson(result, AllKindsGlassesDetailsBeaen.class);
                List<AllKindsGlassesDetailsBeaen.DataBean.WearVideoBean> wearVideoBeen =
                        detailsBeaen.getData().getWear_video();

                progressBar.setVisibility(View.GONE);
                progressBarVv.setVisibility(View.GONE);
                ivPlay.setVisibility(View.VISIBLE);

                imgList = new ArrayList<>();
                for (AllKindsGlassesDetailsBeaen.DataBean.WearVideoBean videoBean : wearVideoBeen) {
                    if (videoBean.getType().equals("8")) {
                        videoUrl = videoBean.getData();
                    } else if (videoBean.getType().equals("9")) {
                        videoImg = videoBean.getData();
                    } else {
                        imgList.add(videoBean.getData());
                    }
                }
                videoView.setMediaController(new MediaController(GoodShareActivity.this));
                videoView.setVideoURI(Uri.parse(videoUrl));
                imageLoader.get(videoImg
                        , ImageLoader.getImageListener(ivImg, R.mipmap.img_gray_color, R.mipmap.img_gray_color));

                if (imgList.size() > 4) {
                    imageLoader.get(imgList.get(imgList.size() - 5)
                            , ImageLoader.getImageListener(iv1, R.mipmap.img_gray_color, R.mipmap.img_gray_color));
                    imageLoader.get(imgList.get(imgList.size() - 4)
                            , ImageLoader.getImageListener(iv2, R.mipmap.img_gray_color, R.mipmap.img_gray_color));
                    imageLoader.get(imgList.get(imgList.size() - 3)
                            , ImageLoader.getImageListener(iv3, R.mipmap.img_gray_color, R.mipmap.img_gray_color));
                    imageLoader.get(imgList.get(imgList.size() - 2)
                            , ImageLoader.getImageListener(iv4, R.mipmap.img_gray_color, R.mipmap.img_gray_color));
                    imageLoader.get(imgList.get(imgList.size() - 1)
                            , ImageLoader.getImageListener(iv5, R.mipmap.img_gray_color, R.mipmap.img_gray_color));
                } else if (imgList.size() == 4) {
                    imageLoader.get(imgList.get(imgList.size() - 4)
                            , ImageLoader.getImageListener(iv2, R.mipmap.img_gray_color, R.mipmap.img_gray_color));
                    imageLoader.get(imgList.get(imgList.size() - 3)
                            , ImageLoader.getImageListener(iv3, R.mipmap.img_gray_color, R.mipmap.img_gray_color));
                    imageLoader.get(imgList.get(imgList.size() - 2)
                            , ImageLoader.getImageListener(iv4, R.mipmap.img_gray_color, R.mipmap.img_gray_color));
                    imageLoader.get(imgList.get(imgList.size() - 1)
                            , ImageLoader.getImageListener(iv5, R.mipmap.img_gray_color, R.mipmap.img_gray_color));
                } else if (imgList.size() == 3) {
                    imageLoader.get(imgList.get(imgList.size() - 3)
                            , ImageLoader.getImageListener(iv3, R.mipmap.img_gray_color, R.mipmap.img_gray_color));
                    imageLoader.get(imgList.get(imgList.size() - 2)
                            , ImageLoader.getImageListener(iv4, R.mipmap.img_gray_color, R.mipmap.img_gray_color));
                    imageLoader.get(imgList.get(imgList.size() - 1)
                            , ImageLoader.getImageListener(iv5, R.mipmap.img_gray_color, R.mipmap.img_gray_color));
                } else if (imgList.size() == 2) {
                    imageLoader.get(imgList.get(imgList.size() - 2)
                            , ImageLoader.getImageListener(iv4, R.mipmap.img_gray_color, R.mipmap.img_gray_color));
                    imageLoader.get(imgList.get(imgList.size() - 1)
                            , ImageLoader.getImageListener(iv5, R.mipmap.img_gray_color, R.mipmap.img_gray_color));
                }
                iv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toDetailsPic(imgList.get(imgList.size() - 5), iv1);
                    }
                });
                iv2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toDetailsPic(imgList.get(imgList.size() - 4), iv2);
                    }
                });
                iv3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toDetailsPic(imgList.get(imgList.size() - 3), iv3);
                    }
                });
                iv4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toDetailsPic(imgList.get(imgList.size() - 2), iv4);
                    }
                });
                iv5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toDetailsPic(imgList.get(imgList.size() - 1), iv5);
                    }
                });
                tvBuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences getsp = getSharedPreferences("token", MODE_PRIVATE);
                        String token = getsp.getString("token", "0");
                        if (token.equals("0")) {
                            startActivity(new Intent(GoodShareActivity.this, LoginActivity.class));
                        } else {
                            DBUtils dbUtils = new DBUtils();
                            dbUtils.updateQuery(detailsBeaen.getData().getGoods_id(),
                                    detailsBeaen.getData().getGoods_img(),
                                    detailsBeaen.getData().getGoods_name(),
                                    detailsBeaen.getData().getGoods_price(),
                                    detailsBeaen.getData().getProduct_area(),
                                    detailsBeaen.getData().getBrand());
                            Toast.makeText(GoodShareActivity.this, "已加入购物车", Toast.LENGTH_SHORT).show();
                            //销毁上一级activity
                            AllKindsGlassesActivity.ALLKINDS.finish();
                            finish();
                        }
                    }
                });
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
        //视频播放
        ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.start();
                ivPlay.setVisibility(View.GONE);
                RelativeLayout linearLayout = (RelativeLayout) findViewById(R.id.linearlayout_goodeshare);
                linearLayout.setVisibility(View.GONE);
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }

    //跳转放大动画
    private void toDetailsPic(String url, ImageView iv) {
        Intent intent = new Intent(this, ShareDetailsActivity.class);
        intent.putExtra("url", url);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(
                    this, iv, "sharedView").toBundle());
        } else {
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}

